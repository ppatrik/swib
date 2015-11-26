
package sk.upjs.ics.swib.logic;

import java.math.BigDecimal;
import java.util.List;
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.exceptions.NieJeMozneSplacat;
import sk.upjs.ics.swib.factory.DaoFactory;


public class OfiCalculator implements Calculator{
 
    DatabazovyBonusDao databazovyBonusDao = DaoFactory.INSTANCE.databazovyBonusDao();
    DatabazovyKlientDao databazovyKlientDao = DaoFactory.INSTANCE.databazovyKlientDao();
    
    
    @Override
    public void uplatniBonus(BigDecimal nasobic, Bonus bonus) {
        nasobic = nasobic.add(bonus.getKolkoJeBonus());
    }

    @Override
    public void uplatniBonusy(BigDecimal nasobic, Uver uver) {
        List<Bonus> bonusy = databazovyBonusDao.dajVsetky(uver);
        for (int i = 0; i < bonusy.size(); i++) {
            uplatniBonus(nasobic, bonusy.get(i));
            
        }
              }

    @Override
    public BigDecimal mesacnaUrokovaSadzba(Klient klient, BigDecimal sumaNaPozicanie, int dobaVMesiacoch, Uver uver) throws NieJeMozneSplacat {
       BigDecimal maxKlientoveMesacneSplatky = databazovyKlientDao.mozeNaMesiacMaximalneSplacat(klient);
       BigDecimal nasobic = BigDecimal.ONE;
       uplatniBonusy(nasobic, uver);
       sumaNaPozicanie = sumaNaPozicanie.multiply(nasobic);
       BigDecimal decimalDobaVMesiacoch = new BigDecimal(dobaVMesiacoch);
       sumaNaPozicanie = sumaNaPozicanie.divide(decimalDobaVMesiacoch);
       if (sumaNaPozicanie.compareTo(maxKlientoveMesacneSplatky) <= 0){
           return sumaNaPozicanie;
       } else{
           throw new NieJeMozneSplacat();
       }
         
    }
    
}


