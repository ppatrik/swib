package sk.upjs.ics.swib.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.GregorianCalendar;
import java.util.Random;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Pohyb;
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
                rangeMin = 1000000;
                rangeMax = 1000000000;
                break;
            case KLIENT_BOHAC:
                rangeMin = 50000;
                rangeMax = 5000000;
                break;
            case KLIENT_NORMAL:
                rangeMin = 1000;
                rangeMax = 300000;
                break;
            case KLIENT_CHUDOBNY:
                rangeMin = 0;
                rangeMax = 2500;
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
                rangeMin = 100000;
                rangeMax = 1000000;
                break;
            case KLIENT_BOHAC:
                rangeMin = 10000;
                rangeMax = 100000;
                break;
            case KLIENT_NORMAL:
                rangeMin = 1000;
                rangeMax = 5000;
                break;
            case KLIENT_CHUDOBNY:
                rangeMin = 300;
                rangeMax = 1000;
                break;
            default:
                break;
        }
        int plat = (int) (Math.random() * (rangeMax - rangeMin)) + rangeMin;
        return new BigDecimal(plat);
    }

    public static BigDecimal generujSumu(int financnePostavenie, GregorianCalendar iterate, BigDecimal plat,
            BigDecimal zostatok) {
        if (iterate.get(GregorianCalendar.DATE) == 15 || iterate.get(GregorianCalendar.DATE) == 14) {
            return plat;
        }

        int minDivisor = (financnePostavenie == KLIENT_BOHAC ||financnePostavenie == KLIENT_MILIONAR) ? 30 : 45;
        BigDecimal divisor = new BigDecimal((int) (Math.random() * 60) + minDivisor);
        int random = (int) (Math.random() * 50) + 1;
        boolean vydaj = false;
        int randomPrijemVydaj = (int) (Math.random() * 4) + 1;
        switch (financnePostavenie) {
            case KLIENT_MILIONAR:
            case KLIENT_BOHAC:
                vydaj = (randomPrijemVydaj == 1 || randomPrijemVydaj == 2);
                break;
            case KLIENT_NORMAL:
            case KLIENT_CHUDOBNY:
                vydaj = true;
                break;
            default:
                break;
        }
        BigDecimal suma = new BigDecimal(zostatok.doubleValue());
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

}
