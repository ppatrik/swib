package sk.upjs.ics.swib.gui;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import sk.upjs.ics.swib.dao.PorovnavacDao;
import sk.upjs.ics.swib.entity.Porovnavac;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
class PorovnavacComboBoxModel extends AbstractListModel implements ComboBoxModel{
    
    private final PorovnavacDao databazovyPorovnavacDao = DaoFactory.INSTANCE.porovnavacDao();
    private List<Porovnavac> zoznamPorovnavacov = databazovyPorovnavacDao.dajVsetky();
    private Object vybranyPorovnavac;

    @Override
    public int getSize() {
        return zoznamPorovnavacov.size();
    }

    @Override
    public String getElementAt(int index) {
        return zoznamPorovnavacov.get(index).getNazov()+"("+zoznamPorovnavacov.get(index).getId()+")";
    }

    @Override
    public void setSelectedItem(Object anItem) {
        vybranyPorovnavac = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return vybranyPorovnavac;
    }
    
    public Porovnavac getPorovnavac(int index){
        return zoznamPorovnavacov.get(index);
    }
 
    public void vypis(){
        for (Porovnavac p : zoznamPorovnavacov) {
            System.out.println(p.getNazov()+" "+p.getId());
        }
        System.out.println("==============================");
    }
}
