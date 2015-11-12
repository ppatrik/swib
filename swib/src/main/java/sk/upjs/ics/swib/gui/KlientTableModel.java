/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.swib.gui;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.guiDAOTest.zoznamKlientov;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Uživateľ
 */
public class KlientTableModel extends AbstractTableModel {  
    
    private final JdbcTemplate jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
    private final DatabazovyKlientDao databazovyKlientDao = new DatabazovyKlientDao(jdbcTemplate);
    private final DatabazovyUcetDao databazovyUcetDao = new DatabazovyUcetDao(jdbcTemplate);
    
    private List<Klient> zoznamK = databazovyKlientDao.dajVsetkych();
    
    private static final int COLUMN_NUMBER = 2;
    private static final String[] COLUMN_TITLE = {"Celé meno", "ID"};
    private static final Class[] COLUMN_TYPES = {
        String.class,
        Integer.class
    };

    @Override
    public int getRowCount() {
        return zoznamK.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klient vybranyKlient = zoznamK.get(rowIndex);
        if (columnIndex == 0) {
            return vybranyKlient.getMeno()+" "+vybranyKlient.getPriezvisko();
        }
        if (columnIndex == 1) {
            return vybranyKlient.getId();
        }
        return null;
    }            
    
    public void refresh(){
        zoznamK = databazovyKlientDao.dajVsetkych();
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_TITLE[column];
    }
    
    public Klient getKlient(int index) {
        return zoznamK.get(index);
    }

    void zmaz(Klient vybranyKlient) {       
        for (Ucet ucet : databazovyUcetDao.dajVsetky(vybranyKlient)){
            databazovyUcetDao.odstran(ucet);
        }
        databazovyKlientDao.odstran(vybranyKlient);
        refresh();
    }
}
