package sk.upjs.ics.swib.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import sk.upjs.ics.swib.dao.MultiplikatorDao;
import sk.upjs.ics.swib.dao.PorovnavacDao;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.exceptions.NieJeMozneSplacat;
import sk.upjs.ics.swib.factory.DaoFactory;

public class OfiCalculator implements Calculator {

    MultiplikatorDao multiplikatorDao = DaoFactory.INSTANCE.multiplikatorDao();
    PorovnavacDao porovnavacDao = DaoFactory.INSTANCE.porovnavacDao();

    @Override
    public BigDecimal uplatniBonus(
            BigDecimal nasobic,
            Bonus bonus,
            KonstantyPreKlienta konstanty) {
        BigDecimal klientovaRealnaHodnota = null;
        String porovnavac = porovnavacDao.dajNazov(bonus.getPorovnavacId());
        String operator = multiplikatorDao.dajNazov(bonus.getMultiplikatorId());

        switch (porovnavac) {
            case "PMP":
                klientovaRealnaHodnota = konstanty.getPriemernyMesacnyPrijem();
                break;
            case "Suma":
                klientovaRealnaHodnota = konstanty.getSumaNaPozicanie();
                break;
            case "PMZ":
                klientovaRealnaHodnota = konstanty.getPriemernyMesacnyZostatok();
                break;
        }

        if (klientovaRealnaHodnota == null) {
            return nasobic;
        }
        if (klientovaRealnaHodnota.compareTo(bonus.getJeVacsiAko()) == 1) {
            if (konstanty.getVyskytPorovnavaca().add(bonus.getPorovnavacId()) == true) {
                switch (operator) {
                    case "+":
                        return nasobic.add(bonus.getKolkoJeBonus());
                    case "-":
                        return nasobic.subtract(bonus.getKolkoJeBonus());
                    case "*":
                        return nasobic.multiply(bonus.getKolkoJeBonus());
                }
            } else {
                return nasobic;
            }
        }
        return nasobic;
    }

    @Override
    public BigDecimal uplatniBonusy(
            BigDecimal nasobic,
            List<Bonus> bonusy,
            KonstantyPreKlienta konstanty) {
        for (int i = 0; i < bonusy.size(); i++) {
            nasobic = uplatniBonus(nasobic, bonusy.get(i), konstanty);
        }

        return nasobic;
    }

    @Override
    public BigDecimal mesacnaUrokovaSadzba(
            KonstantyPreKlienta konstanty,
            List<Bonus> bonusy)
            throws NieJeMozneSplacat {
        BigDecimal nasobic = BigDecimal.ONE;
        BigDecimal mesacnaUrokovaSadzba = null;

        konstanty.getSumaNaPozicanie();
        nasobic = uplatniBonusy(nasobic, bonusy, konstanty);
        mesacnaUrokovaSadzba = konstanty.getSumaNaPozicanie()
                .multiply(nasobic)
                .divide(new BigDecimal("" + konstanty.getDobaVMesiacoch()),4, RoundingMode.DOWN);
        System.out.println(mesacnaUrokovaSadzba);
        System.out.println(konstanty.getMozeNaMesiacMaximalneSplacat());
        if (mesacnaUrokovaSadzba.compareTo(konstanty.getMozeNaMesiacMaximalneSplacat()) <= 0) {
            return mesacnaUrokovaSadzba;
        } else {
            throw new NieJeMozneSplacat();
        }
    }
}
