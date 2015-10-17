package sk.upjs.ics.swib.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sk.upjs.ics.swib.entity.Klient;

public class GenKlientov {

	private List<String> muzskeMena;
	private List<String> zenskeMena;
	private List<String> priezviska;
	private Set<String> rodneCisla;
	private Set<String> cislaPreukazov;

	public GenKlientov() {
		nacitajMena();
		nacitajPriezviska();
		rodneCisla = new HashSet<String>();
		cislaPreukazov = new HashSet<String>();
	}

	public List<Klient> generujKlientov() {
		List<Klient> klienti = new ArrayList<Klient>();
		for (int i = 0; i < muzskeMena.size(); i++) {
			int random = (int) (Math.random() * priezviska.size());
			DatNarARodCislo datNarARodCislo = generujDatNarARodCislo(true);
			Klient klient = new Klient();
			klient.setId(i + 1);
			klient.setMeno(muzskeMena.get(i));
			klient.setPriezvisko(priezviska.get(random));
			klient.setDatumNarodenia(datNarARodCislo.getDatumNarodenia());
			klient.setRodneCislo(datNarARodCislo.getRodneCislo());
			klient.setCisloPreukazu(generujCisloPreukazu());
			// TODO priradenie adresy
			klienti.add(klient);
		}
		for (int i = 0; i < zenskeMena.size(); i++) {
			int random = (int) (Math.random() * priezviska.size());
			DatNarARodCislo datNarARodCislo = generujDatNarARodCislo(false);
			Klient klient = new Klient();
			klient.setId(i + 1 + muzskeMena.size());
			klient.setMeno(zenskeMena.get(i));
			klient.setPriezvisko(priezviska.get(random) + "ova");
			klient.setDatumNarodenia(datNarARodCislo.getDatumNarodenia());
			klient.setRodneCislo(datNarARodCislo.getRodneCislo());
			klient.setCisloPreukazu(generujCisloPreukazu());
			// TODO priradenie adresy
			klienti.add(klient);
		}

		return klienti;
	}

	private String generujCisloPreukazu() {
		StringBuilder cisloPreukazu = new StringBuilder();
		for (int j = 0; j < 10; j++) {
			cisloPreukazu.append(generujNahodnyZnak());
		}
		if (cislaPreukazov.contains(cisloPreukazu.toString())) {
			return generujCisloPreukazu();
		} else {
			cislaPreukazov.add(cisloPreukazu.toString());
			return cisloPreukazu.toString();
		}
	}

	private String generujNahodnyZnak() {
		int random = (int) (Math.random() * 2);
		if (random == 0) {
			int randomNumber = (int) (Math.random() * 10);
			return Integer.toString(randomNumber);
		} else {
			int randomL = (int) (Math.random() * 26);
			char randomLetter = (char) (65 + randomL);
			return randomLetter + ""; // pretypovanie
		}
	}

	private DatNarARodCislo generujDatNarARodCislo(boolean muz) {
		GregorianCalendar datNar;
		String rodCis;
		do {
			datNar = new GregorianCalendar();
			rodCis = new String();
			int rokyOdp = (int) (Math.random() * 45) + 18;
			int mesiaceOdp = (int) (Math.random() * 12);
			int dniOdp = (int) (Math.random() * 30);
			datNar.add(GregorianCalendar.YEAR, -rokyOdp);
			datNar.add(GregorianCalendar.MONTH, -mesiaceOdp);
			datNar.add(GregorianCalendar.DATE, -dniOdp);

			int poradCislo = (int) (Math.random() * 10000);
			String rokString = Integer.toString(datNar.get(GregorianCalendar.YEAR)).substring(2);
			int mesiac = (muz) ? datNar.get(GregorianCalendar.MONTH) + 1 : datNar.get(GregorianCalendar.MONTH) + 51;
			String mesiacString = (mesiac < 10) ? "0" + mesiac : Integer.toString(mesiac);
			int den = datNar.get(GregorianCalendar.DATE);
			String denString = (den < 10) ? "0" + den : Integer.toString(den);
			rodCis = rokString + mesiacString + denString + poradCislo;
		} while (rodneCisla.contains(rodCis));
		rodneCisla.add(rodCis);
		DatNarARodCislo datNarARodCislo = new DatNarARodCislo(datNar, rodCis);
		return datNarARodCislo;
	}

	private void nacitajMena() {
		muzskeMena = new ArrayList<String>();
		zenskeMena = new ArrayList<String>();
		BufferedReader bf = null;
		String[] names;
		try {
			bf = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("src/test/resources/textfiles/muzskeMena.txt"))));
			String line;
			while ((line = bf.readLine()) != null) {
				names = line.split(",");
				for (String meno : names) {
					muzskeMena.add(meno.trim());
				}
			}
			bf = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("src/test/resources/textfiles/zenskeMena.txt"))));
			while ((line = bf.readLine()) != null) {
				names = line.split(",");
				for (String meno : names) {
					zenskeMena.add(meno.trim());
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void nacitajPriezviska() {
		priezviska = new ArrayList<String>();
		BufferedReader bf = null;
		String[] names;
		try {
			bf = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File("src/test/resources/textfiles/priezviska.txt"))));
			String line;
			while ((line = bf.readLine()) != null) {
				names = line.split(",");
				for (String meno : names) {
					priezviska.add(meno.trim());
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static class DatNarARodCislo {
		Calendar datumNarodenia;
		String rodneCislo;

		public Calendar getDatumNarodenia() {
			return datumNarodenia;
		}

		public String getRodneCislo() {
			return rodneCislo;
		}

		public DatNarARodCislo(Calendar datNar, String rodCis) {
			this.datumNarodenia = datNar;
			this.rodneCislo = rodCis;
		}
	}

}
