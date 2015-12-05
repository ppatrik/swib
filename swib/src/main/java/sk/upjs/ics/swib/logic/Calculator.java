package sk.upjs.ics.swib.logic;

import java.math.BigDecimal;
import java.util.List;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.exceptions.NieJeMozneSplacat;

public interface Calculator {

    /**
     *
     * @param nasobic cislo, ktore sa meni vzhladom k tomu, ako ho upravili
     * bonusy
     * @param bonus bonus, ktore by sa mohli uplatnit, ak bude splnena
     * @param konstanty konstanty, ktore predstavuju vsetky info na vypocitanie
     * uroku
     * @return upraveny nasobic podla bonusu (ak bola splnena podmienka, inak
     * vracia to iste, co bolo v premennej nasobic)
     */
    BigDecimal uplatniBonus(BigDecimal nasobic,
            Bonus bonus,
            KonstantyPreKlienta konstanty);

    /**
     *
     * @param nasobic cislo, ktore sa meni vzhladom k tomu, ako ho upravili
     * bonusy
     * @param bonusy bonusy, ktore by sa mohli uplatnit, ak bude splnena
     * podmienka
     * @param konstanty konstanty, ktore predstavuju vsetky info na vypocitanie
     * uroku
     * @return upraveny nasobic podla bonusov
     */
    BigDecimal uplatniBonusy(BigDecimal nasobic,
            List<Bonus> bonusy,
            KonstantyPreKlienta konstanty);

    /**
     *
     * @param konstanty konstanty, ktore predstavuju vsetky info na vypocitanie
     * uroku
     * @param bonusy bonusy, ktore by sa mohli uplatnit, ak bude splnena
     * podmienka
     * @return vrati mesacnu urokovu sadzbu na dany uver
     * @throws NieJeMozneSplacat vyhodi vinimku, ak uzivatel nemoze splacat
     */
    public BigDecimal mesacnaUrokovaSadzba(
            KonstantyPreKlienta konstanty,
            List<Bonus> bonusy)
            throws NieJeMozneSplacat;
}
