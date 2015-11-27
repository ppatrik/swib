package sk.upjs.ics.swib.generator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.factory.DaoFactory;

public class TestUtils {

    public static final int KLIENT_MILIONAR = 3;
    public static final int KLIENT_BOHAC = 2;
    public static final int KLIENT_NORMAL = 1;
    public static final int KLIENT_CHUDOBNY = 0;

    // moj IBAN mozte mi tam posielat peniaze kludne :)))
    public static final String IBAN = "SK5509000000005046421006";

    public static BigDecimal generujZostatokPreFP(int financnePostavenie, Random random) {
        int rangeMin = 0;
        int rangeMax = 0;
        switch (financnePostavenie) {
            case KLIENT_MILIONAR:
                rangeMin = 100000;
                rangeMax = 10000000;
                break;
            case KLIENT_BOHAC:
                rangeMin = 3000;
                rangeMax = 10000;
                break;
            case KLIENT_NORMAL:
                rangeMin = 1000;
                rangeMax = 3000;
                break;
            case KLIENT_CHUDOBNY:
                rangeMin = 0;
                rangeMax = 1000;
                break;
            default:
                break;
        }
        double randomValue = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
        return new BigDecimal(randomValue).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal generujPlat(int financnePostavenie) {
        int rangeMin = 0;
        int rangeMax = 0;
        switch (financnePostavenie) {
            case KLIENT_MILIONAR:
                rangeMin = 10000;
                rangeMax = 1000000;
                break;
            case KLIENT_BOHAC:
                rangeMin = 2500;
                rangeMax = 20000;
                break;
            case KLIENT_NORMAL:
                rangeMin = 800;
                rangeMax = 2000;
                break;
            case KLIENT_CHUDOBNY:
                rangeMin = 300;
                rangeMax = 600;
                break;
            default:
                break;
        }
        int plat = (int) (Math.random() * (rangeMax - rangeMin)) + rangeMin;
        return new BigDecimal(plat);
    }

    public static BigDecimal generujSumu(int financnePostavenie, int date, BigDecimal plat,
            BigDecimal zostatok) {
        if (date / 3 == 4) {
            return plat;
        }
        int minDivisor = (financnePostavenie == KLIENT_BOHAC || financnePostavenie == KLIENT_MILIONAR) ? 10 : 15;
        BigDecimal divisor = new BigDecimal((int) (Math.random() * 10) + minDivisor);
        int random = (int) (Math.random() * 50) + 1;
        boolean vydaj = false;
        int randomPrijemVydaj = (int) (Math.random() * 10) + 1;
        switch (financnePostavenie) {
            case KLIENT_MILIONAR:
                vydaj = (randomPrijemVydaj <= 5);
                break;
            case KLIENT_BOHAC:
                vydaj = (randomPrijemVydaj <= 2);
                break;
            case KLIENT_NORMAL:
            case KLIENT_CHUDOBNY:
                vydaj = true;
                break;
            default:
                break;
        }
        BigDecimal suma = new BigDecimal(plat.doubleValue());
        if (random == 50) {
            suma = suma.divide(new BigDecimal(4), 2, RoundingMode.HALF_UP);
            suma = suma.negate();
        } else {
            suma = suma.divide(divisor, 2, RoundingMode.HALF_UP);
            if (vydaj) {
                suma = suma.negate();
            }
        }
        return suma;
    }

    // pohyby sa nepridavaju cez dao, preto si ho pridam vlastnorucne
    static void pridajPohyb(Pohyb pohyb, JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO Pohyby(UcetID, KamIBAN, Suma, Datum, Spracovane) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, pohyb.getUcetId(), pohyb.getKamIBAN(), pohyb.getSuma(), pohyb.getDatum(), (pohyb.isSpracovane()) ? 1 : 0);
    }

    // pre tabulky, kde netreba joinovat
    public static int pocetZDB(String table) {
        System.setProperty("testovaciRezim", "true");
        String sql = "SELECT COUNT(*) FROM " + table;
        Integer id = (Integer) DaoFactory.INSTANCE.jdbcTemplate().queryForObject(sql, Integer.class);
        return id.intValue();
    }

    public static int pocetUctov(Klient klient) {
        String sql = "SELECT COUNT(*) FROM Ucet u JOIN Klient k on u.KlientID = k.ID WHERE k.ID = " + klient.getId();
        Integer id = (Integer) DaoFactory.INSTANCE.jdbcTemplate().queryForObject(sql, Integer.class);
        return id.intValue();
    }

    public static int pocetPohybov(Ucet ucet) {
        String sql = "SELECT COUNT(*) FROM Pohyby p JOIN Ucet u on p.UcetID = u.ID WHERE u.ID =" + ucet.getId();
        Integer id = (Integer) DaoFactory.INSTANCE.jdbcTemplate().queryForObject(sql, Integer.class);
        return id.intValue();
    }

    public static int pocetBonusov(Uver uver) {
        String sql = "SELECT COUNT(*) FROM Bonusy b JOIN Uvery u on b.UveryId = b.ID WHERE u.ID = " + uver.getId();
        Integer id = (Integer) DaoFactory.INSTANCE.jdbcTemplate().queryForObject(sql, Integer.class);
        return id.intValue();
    }

    public static BigDecimal maxSplatky(Klient klient) {
        Map<String, BigDecimal> mesiaceZostatky = new HashMap<String, BigDecimal>();
        for (Ucet ucet : DaoFactory.INSTANCE.databazovyUcetDao().dajVsetky(klient)) {
            for (Pohyb pohyb : DaoFactory.INSTANCE.databazovyPohybDao().dajVsetky(ucet)) {
                String key = pohyb.getDatum().get(Calendar.MONTH) + "" + pohyb.getDatum().get(Calendar.YEAR);
                if (mesiaceZostatky.containsKey(key)) {
                    BigDecimal suma = mesiaceZostatky.get(key);
                    suma = suma.add(pohyb.getSuma());
                    mesiaceZostatky.put(key, suma);
                } else {
                    mesiaceZostatky.put(key, pohyb.getSuma());
                }
            }
        }
        BigDecimal priemer = new BigDecimal(BigInteger.ZERO);
        for (BigDecimal suma : mesiaceZostatky.values()) {
            priemer = priemer.add(suma);
        }
        priemer = priemer.divide(new BigDecimal(mesiaceZostatky.size()), 4, RoundingMode.DOWN);
        return priemer;
    }

}
