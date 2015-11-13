package sk.upjs.ics.swib.gui;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class UctyTableModel extends AbstractTableModel{
    
    private final Klient klient; //neviem ci mi to vlastne treba
    private final JdbcTemplate jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
    private final DatabazovyUcetDao databazovyUcetDao = new DatabazovyUcetDao(jdbcTemplate);    
    private List<Ucet> ucty;
    
    private static final int COLUMN_NUMBER = 3;
    private static final String[] COLUMN_TITLE = {"Číslo účtu", "Názov účtu", "Zostatok"};
    private static final Class[] COLUMN_TYPES = {
        String.class,
        Integer.class,
        BigDecimal.class
    };

    public UctyTableModel(Klient klient) {
        this.klient = klient;
        this.ucty = databazovyUcetDao.dajVsetky(klient);
    }        

    @Override
    public int getRowCount() {
        return this.ucty.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ucet ucet = ucty.get(rowIndex);
        switch (columnIndex) {
            case 0 :
                return ucet.getCisloUctu();
            case 1 :
                return ucet.getName();
            case 2 :
                return ucet.getZostatok();
            default :
                return null;
        }        
    }
    
    public void refres(){
        ucty = databazovyUcetDao.dajVsetky(klient);
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column){
        return COLUMN_TITLE[column];
    }
    
    public Ucet getUcet(int index){
        return ucty.get(index);
    }
}
