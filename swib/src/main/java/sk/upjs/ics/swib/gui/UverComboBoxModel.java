package sk.upjs.ics.swib.gui;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import sk.upjs.ics.swib.dao.UverDao;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class UverComboBoxModel extends AbstractListModel implements ComboBoxModel{
    
    private final UverDao databazovyUverDao = DaoFactory.INSTANCE.uverDao();
    private List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();
    private Object vybranyObjekt;
    
    @Override
    public int getSize() {
        return zoznamUverov.size();
    }

    @Override
    public String getElementAt(int index) {
        return zoznamUverov.get(index).getNazov();
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

    void pridajUver(Uver uver) {
        databazovyUverDao.pridaj(uver);
        refresh();
    }

    private void refresh() {
        zoznamUverov = databazovyUverDao.dajVsetky();
        fireContentsChanged(this, 0, zoznamUverov.size());
    }

    void uprav(Uver uver) {
        databazovyUverDao.uprav(uver);
        refresh();
    }
}
