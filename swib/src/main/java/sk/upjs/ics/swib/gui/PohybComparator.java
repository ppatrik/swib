package sk.upjs.ics.swib.gui;

import java.util.Comparator;
import sk.upjs.ics.swib.entity.Pohyb;

/**
 *
 * @author Uživateľ
 */
public class PohybComparator implements Comparator<Pohyb>{

    @Override
    public int compare(Pohyb o1, Pohyb o2) {
        return (int)(o2.getDatum().getTimeInMillis() - o1.getDatum().getTimeInMillis());
    }
    
}
