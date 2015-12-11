package sk.upjs.ics.swib.gui;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import sk.upjs.ics.swib.dao.MultiplikatorDao;
import sk.upjs.ics.swib.entity.Multiplikator;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
class MultiplicatorComboBoxModel extends AbstractListModel implements ComboBoxModel{    
    
    private final MultiplikatorDao databazovyMultiplikatorDao = DaoFactory.INSTANCE.multiplikatorDao();
    private final List<Multiplikator> zoznamMultiplikatorov = databazovyMultiplikatorDao.dajVsetky();
    private Object vybranyMultiplikator;

    @Override
    public int getSize() {
        return zoznamMultiplikatorov.size();
    }

    @Override
    public String getElementAt(int index) {
        return zoznamMultiplikatorov.get(index).getNazov();
    }

    @Override
    public void setSelectedItem(Object anItem) {
        vybranyMultiplikator = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return vybranyMultiplikator;
    }
    
    public Multiplikator getMultiplikator(int index){
        return zoznamMultiplikatorov.get(index);
    }
}
