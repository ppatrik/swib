package sk.upjs.ics.swib.logic;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyMultiplikatorDao;
import sk.upjs.ics.swib.dao.DatabazovyPorovnavacDao;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Multiplikator;
import sk.upjs.ics.swib.entity.Porovnavac;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.exceptions.NieJeMozneSplacat;
import sk.upjs.ics.swib.factory.DaoFactory;

public class OfiCalculator implements Calculator {

    DatabazovyBonusDao databazovyBonusDao = DaoFactory.INSTANCE.databazovyBonusDao();
    DatabazovyKlientDao databazovyKlientDao = DaoFactory.INSTANCE.databazovyKlientDao();
    DatabazovyMultiplikatorDao databazovyMultiplikatorDao = DaoFactory.INSTANCE.databazovyMultiplikatorDao();
    DatabazovyPorovnavacDao databazovyPorovnavacDao = DaoFactory.INSTANCE.databazovyPorovnavacDao();
    
    private BigDecimal klientovaRealnaHodnota;
    private List<Multiplikator> poleMultiplikatorov = databazovyMultiplikatorDao.dajVsetky();
    private List<Porovnavac> polePorovnavacov = databazovyPorovnavacDao.dajVsetky();
    private Set<Integer> vyskytPorovnavaca;
    private BigDecimal hodnotaSumyNaPozicanie;
    private Klient danyKlient;

    @Override
    public String prehladajPorovnavace(Bonus bonus) {
        String porovnavac = "";
        for (int i = 0; i < polePorovnavacov.size(); i++) {
            if (bonus.getPorovnavacId() == polePorovnavacov.get(i).getId()) {
                porovnavac = polePorovnavacov.get(i).getNazov();
            }
        }
        return porovnavac;
    }

    @Override
    public String prehladajMultiplikatori(Bonus bonus) {
        String operator = "";
        for (int i = 0; i < poleMultiplikatorov.size(); i++) {
            if (bonus.getMultiplikatorId() == poleMultiplikatorov.get(i).getId()) {
                operator = poleMultiplikatorov.get(i).getNazov();
            }
        }
        return operator;
    }

    @Override
    public void uplatniBonus(BigDecimal nasobic, Bonus bonus) {
        String porovnavac = prehladajPorovnavace(bonus);
        String operator = prehladajMultiplikatori(bonus);
        if (vyskytPorovnavaca.add(bonus.getPorovnavacId()) == true) {
            switch (porovnavac) {
            case "PMP": klientovaRealnaHodnota = databazovyKlientDao.priemernyMesacnyPrijem(danyKlient);
                              break;
                case "Suma":
                    klientovaRealnaHodnota = hodnotaSumyNaPozicanie;
                    break;
       //     case "PMZ": klientovaRealnaHodnota = ;
                //              break;
            }

            if (this.klientovaRealnaHodnota.compareTo(bonus.getJeVacsiAko()) == 1) {
                switch (operator) {
                    case "+":
                        nasobic = nasobic.add(bonus.getKolkoJeBonus());
                        break;
                    case "-":
                        nasobic = nasobic.subtract(bonus.getKolkoJeBonus());
                        break;
                    case "*":
                        nasobic = nasobic.multiply(bonus.getKolkoJeBonus());
                        break;
                }
            }
        }
    }

    @Override
    public void uplatniBonusy(BigDecimal nasobic, Uver uver) {
        List<Bonus> bonusy = databazovyBonusDao.dajVsetky(uver);
        for (int i = 0; i < bonusy.size(); i++) {
            uplatniBonus(nasobic, bonusy.get(i));

        }
    }

    @Override
    public BigDecimal mesacnaUrokovaSadzba(Klient klient, BigDecimal sumaNaPozicanie, int dobaVMesiacoch, Uver uver, int pocetDeti) throws NieJeMozneSplacat {
        vyskytPorovnavaca = new HashSet<>();
        danyKlient = klient;
        this.hodnotaSumyNaPozicanie = sumaNaPozicanie;
        BigDecimal maxKlientoveMesacneSplatky = databazovyKlientDao.mozeNaMesiacMaximalneSplacat(klient);
        BigDecimal nasobic = BigDecimal.ONE;
        uplatniBonusy(nasobic, uver);
        sumaNaPozicanie = sumaNaPozicanie.multiply(nasobic);
        BigDecimal decimalDobaVMesiacoch = new BigDecimal(dobaVMesiacoch);
        sumaNaPozicanie = sumaNaPozicanie.divide(decimalDobaVMesiacoch);
        maxKlientoveMesacneSplatky = maxKlientoveMesacneSplatky.subtract(uver.getBonusNaManzelku());
        maxKlientoveMesacneSplatky = maxKlientoveMesacneSplatky.subtract((new BigDecimal(pocetDeti)).multiply(uver.getBonusNaDieta()));
        if (sumaNaPozicanie.compareTo(maxKlientoveMesacneSplatky) <= 0) {
            return sumaNaPozicanie;
        } else {
            throw new NieJeMozneSplacat();
        }

    }

    public void setKlientovaRealnaHodnota(BigDecimal klientovaRealnaHodnota) {
        this.klientovaRealnaHodnota = klientovaRealnaHodnota;
    }

    public BigDecimal getKlientovaRealnaHodnota() {
        return klientovaRealnaHodnota;
    }
}
