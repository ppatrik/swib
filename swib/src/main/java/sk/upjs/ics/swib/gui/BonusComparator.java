package sk.upjs.ics.swib.gui;

import java.util.Comparator;
import sk.upjs.ics.swib.entity.Bonus;

/**
 *
 * @author Johnny
 * ale nechce sa mi uz
 */
public class BonusComparator implements Comparator<Bonus> {

    @Override
    public int compare(Bonus o1, Bonus o2) {
        return o1.getOrderNumber() - o2.getOrderNumber();
    }

}
