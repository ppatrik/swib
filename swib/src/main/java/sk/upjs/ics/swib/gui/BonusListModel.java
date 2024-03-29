package sk.upjs.ics.swib.gui;

import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author Johnny
 */
public class BonusListModel extends AbstractListModel {

    private final DatabazovyBonusDao databazovyBonusDao = DaoFactory.INSTANCE.databazovyBonusDao();
    private final BonusComparator bonusComparator = new BonusComparator();
    List<Bonus> zoznamBonusov = null;
    Uver uver;

    /**
     * konstruktor
     * @param uver 
     */
    public BonusListModel(Uver uver) {
        this.uver = uver;
        zoznamBonusov = databazovyBonusDao.dajVsetky(uver);        
        Collections.sort(zoznamBonusov, bonusComparator);
    }

    @Override
    public int getSize() {
        return zoznamBonusov.size();
    }

    /**
     * vypis pre comboBox
     * @param index
     * @return 
     */
    @Override
    public String getElementAt(int index) {
        return zoznamBonusov.get(index).getOrderNumber() + ". " + zoznamBonusov.get(index).getNazov() + "(" + zoznamBonusov.get(index).getId() + ")";
    }

    public Bonus getBonus(int index) {
        return zoznamBonusov.get(index);
    }

    public void pirdajBonus(Bonus bonus) {
        databazovyBonusDao.pridaj(bonus);
        zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
    }   
    
    /**
     * vymeni vybrany bonus s bonusom, ktory je v poradi nad nim
     * @param bonus 
     */
    void hore(Bonus bonus) {
        int poradie = bonus.getOrderNumber();        
        for (Bonus b : zoznamBonusov) {
            if (b.getOrderNumber() == poradie-1){
                b.setOrderNumber(poradie);
                bonus.setOrderNumber(poradie-1);
                databazovyBonusDao.uprav(bonus);
                databazovyBonusDao.uprav(b);
                break;
            }
        }      
        refresh();
    }

    /**
     * vymeni vybrany bonus s bonus o jedna pod nim;
     * @param bonus 
     */
    void dole(Bonus bonus) {
        int poradie = bonus.getOrderNumber();        
        for (Bonus b : zoznamBonusov) {
            if (b.getOrderNumber() == poradie+1){
                b.setOrderNumber(poradie);
                bonus.setOrderNumber(poradie+1);
                databazovyBonusDao.uprav(bonus);
                databazovyBonusDao.uprav(b);
                break;
            }
        }
        refresh();
    }
    
    private void refresh(){
        zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
        Collections.sort(zoznamBonusov, bonusComparator);
        fireContentsChanged(uver, 0, zoznamBonusov.size());
    }
}
