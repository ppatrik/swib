package sk.upjs.ics.swib.gui;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.swib.dao.DatabazovyPohybDao;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class PohybTableModel extends AbstractTableModel{
    
    private Ucet ucet;
    private List<Pohyb> pohyby;
    private final DatabazovyPohybDao databazovyPohybDao = DaoFactory.INSTANCE.databazovyPohybDao();
    
    private static final int COLUMN_NUMBER = 2;
    private static final String[] COLUMN_TITLE = {"Dátum", "Suma"};
    private static final Class[] COLUMN_TYPES = {
        String.class,
        BigDecimal.class,
    };

    public PohybTableModel(Ucet ucet) {
        this.ucet = ucet;
        this.pohyby = this.databazovyPohybDao.dajVsetky(ucet);
    }        

    @Override
    public int getRowCount() {
        return pohyby.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pohyb pohyb = pohyby.get(rowIndex);
        switch (columnIndex) {
            case 0 :
                //return pohyb.getDatum().getTime().getDay()+"."+pohyb.getDatum().getTime().getMonth()+"."+pohyb.getDatum().getTime().getYear();
                return pohyb.getDatum().getTime().toGMTString();
            case 1 :
                return pohyb.getSuma();
            default :
                return null;
        }
    }
    
    public void refres(){
        pohyby = databazovyPohybDao.dajVsetky(ucet);
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column){
        return COLUMN_TITLE[column];
    }
    
    public Pohyb getPohyb(int index){
        return pohyby.get(index);
    }
    
}
