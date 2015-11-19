package sk.upjs.ics.swib.generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;

import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

public class GenUctPohyb {

    private Set<Integer> menaUctov;
    private Set<Integer> cislaUctov;
    private Random random;
    private int pohybId = 1;

    public static void main(String[] args) {
        GenUctPohyb gup = new GenUctPohyb();
        List<UctyKlienta> ucty = gup.genUctyKlienta();
        System.setProperty("testovaciRezim", "true");
        JdbcTemplate jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        DatabazovyKlientDao klientDao = new DatabazovyKlientDao(jdbcTemplate);
        DatabazovyUcetDao ucetDao = new DatabazovyUcetDao(jdbcTemplate);
        for (UctyKlienta u : ucty) {
            Klient klient = u.getKlient();
            klientDao.pridaj(klient);
            for (Entry<Ucet, Set<Pohyb>> entrySet : u.getUcetAPohyby().entrySet()) {
                Ucet ucet = entrySet.getKey();
                ucetDao.pridaj(ucet);
                Set<Pohyb> value = entrySet.getValue();
                for (Pohyb pohyb : value) {
                    TestUtils.pridajPohyb(pohyb, jdbcTemplate);
                }
            }
        }
    }

    public List<UctyKlienta> genUctyKlienta() {
        menaUctov = new HashSet<Integer>();
        cislaUctov = new HashSet<Integer>();
        random = new Random();
        List<UctyKlienta> uctyKlienta = new ArrayList<UctyKlienta>();
        GenKlientov genKlientov = new GenKlientov();
        List<Klient> klienti = genKlientov.generujKlientov();
        for (int i = 0; i < klienti.size(); i++) {
            Map<Ucet, Set<Pohyb>> ucetAPohyby = generujUcetAPohyby(klienti.get(i));
            UctyKlienta ucetKlienta = new UctyKlienta(klienti.get(i), ucetAPohyby);
            uctyKlienta.add(ucetKlienta);
        }
        return uctyKlienta;
    }

    private Map<Ucet, Set<Pohyb>> generujUcetAPohyby(Klient klient) {
        int randomIntName;
        do {
            randomIntName = random.nextInt();
            if (randomIntName < 0) {
                randomIntName = -randomIntName;
            }
        } while (menaUctov.contains(randomIntName));
        menaUctov.add(randomIntName);

        int randomIntCisUc;
        do {
            // vygeneruje aspon 10 miestne cislo
            randomIntCisUc = ((int) (Math.random() * 1147483640)) + 1000000000;
        } while (cislaUctov.contains(randomIntCisUc));
        cislaUctov.add(randomIntCisUc);

        int financnePostavenie = (int) (Math.random() * 4);

        Ucet ucet = new Ucet();
        ucet.setId(klient.getId());
        ucet.setKlientId(klient.getId());
        ucet.setName(randomIntName);
        ucet.setSpor(((int) (Math.random() * 2)) == 1);
        ucet.setCisloUctu(randomIntCisUc + "");
        ucet.setZostatok(TestUtils.generujZostatokPreFP(financnePostavenie, random));

        Set<Pohyb> pohyby = generujPohybyPreUcet(ucet, financnePostavenie);
        Map<Ucet, Set<Pohyb>> ucetAPohyby = new HashMap<Ucet, Set<Pohyb>>();
        ucetAPohyby.put(ucet, pohyby);
        return ucetAPohyby;
    }

    private Set<Pohyb> generujPohybyPreUcet(Ucet ucet, int financnePostavenie) {
        BigDecimal plat = TestUtils.generujPlat(financnePostavenie);
        GregorianCalendar dnes = new GregorianCalendar();
        GregorianCalendar iterate = new GregorianCalendar();
        // nastavim sa na rok dozadu prveho v mesiaci a vygenerujem prijmy za
        // predch 3 mesiace
        iterate.add(GregorianCalendar.MONTH, -12);
        BigDecimal zostatok = ucet.getZostatok();
        Set<Pohyb> pohyby = new LinkedHashSet<Pohyb>();
        while (iterate.before(dnes)) {
            BigDecimal suma = TestUtils.generujSumu(financnePostavenie, iterate.get(GregorianCalendar.DATE), plat, zostatok);
            pohybId++;
            int pohybIdCopy = pohybId;
            Pohyb pohyb = new Pohyb();
            pohyb.setId(pohybIdCopy);
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(iterate.getTime());
            pohyb.setDatum(cal);
            pohyb.setKamIBAN(TestUtils.IBAN);
            pohyb.setUcetId(ucet.getId());
            pohyb.setSpracovane(true);
            pohyb.setSuma(suma);

            zostatok = zostatok.add(suma);
            pohyby.add(pohyb);
            iterate.add(GregorianCalendar.DATE, 3);
        }
        ucet.setZostatok(zostatok);
        return pohyby;
    }

    public static class UctyKlienta {

        private Klient klient;
        private Map<Ucet, Set<Pohyb>> ucetAPohyby;

        public Klient getKlient() {
            return klient;
        }

        public void setKlient(Klient klient) {
            this.klient = klient;
        }

        public Map<Ucet, Set<Pohyb>> getUcetAPohyby() {
            return ucetAPohyby;
        }

        public void setUcetAPohyby(Map<Ucet, Set<Pohyb>> ucetAPohyby) {
            this.ucetAPohyby = ucetAPohyby;
        }

        public UctyKlienta(Klient klient, Map<Ucet, Set<Pohyb>> ucetAPohyby) {
            this.klient = klient;
            this.ucetAPohyby = ucetAPohyby;
        }
    }

}
