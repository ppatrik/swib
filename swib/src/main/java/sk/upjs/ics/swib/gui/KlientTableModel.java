package sk.upjs.ics.swib.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.dao.KlientDao;
import sk.upjs.ics.swib.dao.UcetDao;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class KlientTableModel extends AbstractTableModel {

    private final KlientDao databazovyKlientDao = DaoFactory.INSTANCE.klientDao();
    private final UcetDao databazovyUcetDao = DaoFactory.INSTANCE.ucetDao();

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
            return vybranyKlient.getMeno() + " " + vybranyKlient.getPriezvisko();
        }
        if (columnIndex == 1) {
            return vybranyKlient.getId();
        }
        return null;
    }

    public void refresh() {
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
        databazovyUcetDao.dajVsetky(vybranyKlient).stream().forEach((ucet) -> {
            databazovyUcetDao.odstran(ucet);
        });
        databazovyKlientDao.odstran(vybranyKlient);
        refresh();
    }

    void hladaj(String hladanyText) {
        zoznamK = databazovyKlientDao.dajVsetkych();
        if ("".equals(hladanyText)){
            refresh();
            return;
        }
        List<Klient> vysledok = new ArrayList<>();
        for (Klient k : zoznamK) {
            if (k.getMeno().toLowerCase().contains(hladanyText.toLowerCase()) || k.getPriezvisko().toLowerCase().contains(hladanyText.toLowerCase())) {
                vysledok.add(k);
            }
        }
        zoznamK = vysledok;
        fireTableDataChanged();
    }
}
