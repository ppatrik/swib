package sk.upjs.ics.swib.gui;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import sk.upjs.ics.swib.dao.DatabazovyMultiplikatorDao;
import sk.upjs.ics.swib.entity.Multiplikator;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
class MultiplicatorComboBoxModel extends AbstractListModel implements ComboBoxModel{    
    
    private final DatabazovyMultiplikatorDao databazovyMultiplikatorDao = DaoFactory.INSTANCE.databazovyMultiplikatorDao();
    private final List<Multiplikator> zoznamMultiplikatorov = databazovyMultiplikatorDao.dajVsetky();
    private Object vybranyMultiplikator;

    @Override
    public int getSize() {
        return zoznamMultiplikatorov.size();
    }

    @Override
    public String getElementAt(int index) {
        return zoznamMultiplikatorov.get(index).getNazov()+"("+zoznamMultiplikatorov.get(index).getId()+")";
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
    
    public void vypis(){
        for (Multiplikator m : zoznamMultiplikatorov) {
            System.out.println(m.getNazov()+" "+m.getId());
        }
        System.out.println("==============================");
    }
}
