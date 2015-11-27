package sk.upjs.ics.swib.gui;

import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class BonusListModel extends AbstractListModel {

    private final JdbcTemplate jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
    private final DatabazovyBonusDao databazovyBonusDao = new DatabazovyBonusDao(jdbcTemplate);
    private final BonusComparator bonusComparator = new BonusComparator();
    List<Bonus> zoznamBonusov = null;
    Uver uver;

    public BonusListModel(Uver uver) {
        this.uver = uver;
        zoznamBonusov = databazovyBonusDao.dajVsetky(uver);        
        Collections.sort(zoznamBonusov, bonusComparator);
    }

    @Override
    public int getSize() {
        return zoznamBonusov.size();
    }

    @Override
    public String getElementAt(int index) {
        return zoznamBonusov.get(index).getOrderNumber() + "." + zoznamBonusov.get(index).getNazov() + "(" + zoznamBonusov.get(index).getId() + ")";
    }

    public Bonus getBonus(int index) {
        return zoznamBonusov.get(index);
    }

    public void pirdajBonus(Bonus bonus) {
        databazovyBonusDao.pridaj(bonus);
        zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
    }
}
