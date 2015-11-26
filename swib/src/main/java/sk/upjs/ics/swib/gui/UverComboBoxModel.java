package sk.upjs.ics.swib.gui;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyUverDao;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class UverComboBoxModel extends AbstractListModel implements ComboBoxModel{
    private JdbcTemplate jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
    private DatabazovyUverDao databazovyUverDao = new DatabazovyUverDao(jdbcTemplate);
    private List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();
    private Object vybranyObjekt;
    
    @Override
    public int getSize() {
        return zoznamUverov.size();
    }

    @Override
    public String getElementAt(int index) {
        return zoznamUverov.get(index).getNazov()+"("+zoznamUverov.get(index).getId()+")";
    }

    @Override
    public void setSelectedItem(Object anItem) {
        vybranyObjekt = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return vybranyObjekt;
    }
    
    public Uver getUver(int index){
        return zoznamUverov.get(index);
    }
}
