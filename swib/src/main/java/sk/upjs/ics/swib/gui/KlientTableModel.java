/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.swib.gui;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.guiDAOTest.zoznamKlientov;

/**
 *
 * @author Uživateľ
 */
public class KlientTableModel extends AbstractTableModel {   
    
    private List<Klient> zoznamK = zoznamKlientov.getList();
    
    private static final int COLUMN_NUMBER = 2;
    private static final String[] COLUMN_TITLE = {"Celé meno", "ID"};
    private static final Class[] COLUMN_TYPES = {
        String.class,
        Integer.class
    };

    public int getRowCount() {
        return zoznamK.size();
    }

    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

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
        zoznamK = zoznamKlientov.getList();
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_TITLE[column];
    }
    
    public Klient getKlient(int index) {
        return zoznamK.get(index);
    }
}
