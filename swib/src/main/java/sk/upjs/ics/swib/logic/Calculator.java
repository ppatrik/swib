package sk.upjs.ics.swib.logic;

import java.math.BigDecimal;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.exceptions.NieJeMozneSplacat;

public interface Calculator {

    /**
     * Metoda, ktora zmeni sumu podla vydajov rodinnych prislusnikov klienta
     *
     * @param mesacnyVynos povodna hodnota pohla metody
     * mozeNaMesiacMaximalneSplacat(Klient klient) v KlientDao
     */
//    void uplatniPrijmyAVydajeRodinnychPrislusnikov(
//            BigDecimal mesacnyVynos);
    /* 
     TODO: budu tu este vstupovat aj rodinny prislusnici, 
     ako ich zareprezentovat?
     Metoda sa potom zavola medzi krokom 1. a 2. v metode mesacnaUrokovaSadzba
     */
    /**
     * Zmeni hodnotu nasobic ak bola splnena podmienka na uplatnenie daneho
     * bonusu
     *
     * @param nasobic cislo, ktore sa meni vzhladom k tomu, ako ho upravil bonus
     * @param bonus bonus, ktory prave aplikujem na hodnotu nasobic
     */
    void uplatniBonus(BigDecimal nasobic, Bonus bonus);

    /**
     * Vyberie si vsetky bonusy pre dany uver a nad kazdym zavola metodu
     * uplatniBonus(BigDecimal nasobic, Bonus bonus)
     *
     * @param nasobic cislo, ktore sa meni vzhladom k tomu, ako ho upravia
     * bonusy
     * @param uver uver podla ktoreho vyberame bonusy
     */
    void uplatniBonusy(BigDecimal nasobic, Uver uver);

    /**
     * Metoda, ktora vypocita na zaklade udajoch o klientovi, ci pre neho je
     * alebo nie je mozne splacat dany uver
     *
     * Pseudokod:
     *
     * 1. ulozime do premennej, kolko moze maximalne klient splacat na mesiac
     * (metoda mozeNaMesiacMaximalneSplacat(Klient klient) v KlientDao)
     *
     * 2. vytvorim premennu nasobic rovnu jednej, ktorou na konci vynasobim sumu
     * aka bude splatena
     *
     * 3. zavolam metodu uplatniBonusy(BigDecimal nasobic, Uver uver), ktora mi
     * zmeni hodnotu nasobic
     *
     * 4. vynasobim sumu, ktoru chceme pozicat nasobicom a ziskame realne
     * splacanu sumu klientom a zistime na zaklade poctu mesiacov, aka je to
     * suma na mesiac
     *
     * 5. porovname, ci moze podla zistenej hodnoty z 1. bodu, ci klient je
     * schopny splacat dany uver. Ak ano, vratime hodnotu, ktoru sme vypocitali
     * v bode 4. ak nie, vyhodime vynimku NieJeMozneSplacat
     *
     * @param klient klient, pre ktoreho uver pocitame
     * @param sumaNaPozicanie suma, ktoru si klient chce poziciat
     * @param dobaVMesiacoch doba, kolko mesiacov chce klient pozicku splacat
     * @param uver typ uveru, aky si klient vybral
     * @param klientovaRealnaHodnota zadana hodnota od klienta
     * @param pocetDeti pocet deti
     * @return vrati vypocitanu mesacnu urokovu sadzbu
     * @throws NieJeMozneSplacat vyhodi vynimku ak pre daneho klienta nie je
     * mozne splacat na zaklade zvolencyh paramatrov zvoleny uver
     */
    public BigDecimal mesacnaUrokovaSadzba(
            Klient klient,
            BigDecimal sumaNaPozicanie,
            int dobaVMesiacoch,
            Uver uver,
            BigDecimal klientovaRealnaHodnota,
            int pocetDeti)
            throws NieJeMozneSplacat;
}
