package sk.upjs.ics.swib.generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;

public class GenUctPohyb {

	private Set<Long> menaUctov;
	private Set<Integer> cislaUctov;
	private Random random;
	private long pohybId = 1;

        public static void main(String[] args) {
            // ukazka vygenerovania klientov s uctami a pohybmi a vypisanie informacie o nahodnom z nich
        GenUctPohyb gup = new GenUctPohyb();
        List<UctyKlienta> ucty = gup.genUctyKlienta();
        int random =(int) (Math.random()*ucty.size());
        Klient klient = ucty.get(random).getKlient();
        System.out.println(klient.getMeno() + " "+ klient.getPriezvisko());
            for (Entry<Ucet, Set<Pohyb>> entrySet : ucty.get(random).getUcetAPohyby().entrySet()) {
                Ucet key = entrySet.getKey();
                Set<Pohyb> value = entrySet.getValue();
                for(Pohyb pohyb: value){
                    System.out.println(pohyb.getSuma() + " "+ pohyb.getDatum().getTime());            
                }
            }
    }

	public List<UctyKlienta> genUctyKlienta() {
		menaUctov = new HashSet<Long>();
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
		long randomLong;
		do {
			randomLong = random.nextLong();
			if (randomLong < 0)
				randomLong = -randomLong;
		} while (menaUctov.contains(randomLong));
		menaUctov.add(randomLong);

		int randomInt;
		do {
			// vygeneruje aspon 10 miestne cislo
			randomInt = ((int) (Math.random() * 1147483640)) + 1000000000;
		} while (cislaUctov.contains(randomInt));
		cislaUctov.add(randomInt);

		int financnePostavenie = (int) (Math.random() * 4);

		Ucet ucet = new Ucet();
		ucet.setId(klient.getId());
		ucet.setKlientId(klient.getId());
		ucet.setName(randomLong);
		ucet.setSpor(((int) (Math.random() * 2)) == 1);
		ucet.setCisloUctu(randomInt + "");
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
		// predch rok
		iterate.add(GregorianCalendar.YEAR, -1);
		BigDecimal zostatok = ucet.getZostatok();
		Set<Pohyb> pohyby = new HashSet<Pohyb>();
		while (notToday(iterate, dnes)) {
			BigDecimal suma = TestUtils.generujSumu(financnePostavenie, iterate, plat, zostatok);
			pohybId++;
			long pohybIdCopy = pohybId;
			Pohyb pohyb = new Pohyb();
			pohyb.setId(pohybIdCopy);
			pohyb.setDatum(iterate);
			pohyb.setKamIBAN(TestUtils.IBAN);
			pohyb.setUcetId(ucet.getId());
			pohyb.setSpracovane(true);
			pohyb.setSuma(suma);

			zostatok = zostatok.add(suma);
			pohyby.add(pohyb);
			iterate.add(GregorianCalendar.DATE, 1);
		}
		ucet.setZostatok(zostatok);
		return pohyby;
	}

	private boolean notToday(GregorianCalendar iterate, GregorianCalendar dnes) {
		return !(iterate.get(GregorianCalendar.YEAR) == dnes.get(GregorianCalendar.YEAR)
				&& iterate.get(GregorianCalendar.MONTH) == dnes.get(GregorianCalendar.MONTH)
				&& iterate.get(GregorianCalendar.DATE) == dnes.get(GregorianCalendar.DATE));
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