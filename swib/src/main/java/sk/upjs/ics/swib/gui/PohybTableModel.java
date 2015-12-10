package sk.upjs.ics.swib.gui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.swib.dao.PohybDao;
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
    private final PohybDao databazovyPohybDao = DaoFactory.INSTANCE.pohybDao();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    
    private static final int COLUMN_NUMBER = 2;
    private static final String[] COLUMN_TITLE = {"Dátum", "Suma"};
    private static final Class[] COLUMN_TYPES = {
        String.class,
        BigDecimal.class,
    };

    public PohybTableModel(Ucet ucet) {
        this.ucet = ucet;
        this.pohyby = this.databazovyPohybDao.dajVsetky(ucet);
        Collections.sort(pohyby, (p1, p2) -> p1.getDatum().compareTo(p2.getDatum()));
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
                return formatter.format(pohyb.getDatum().getTime());
            case 1 :
                return pohyb.getSuma().setScale(2, RoundingMode.DOWN);
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
