package sk.upjs.ics.swib.logic;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class KonstantyPreKlienta {

    private final BigDecimal sumaNaPozicanie;
    private final BigDecimal priemernyMesacnyPrijem;
    private final BigDecimal priemernyMesacnyZostatok;
    private final BigDecimal mozeNaMesiacMaximalneSplacat;
    private final int dobaVMesiacoch;
    private final int pocetDeti;

    // tieto hodnoty veriem podla uveru
    private final BigDecimal bonusNaDieta;
    private final BigDecimal bonusNaManzelku;

    // pri pridavani bonusov
    private final Set<Integer> vyskytPorovnavaca = new HashSet<>();

    public Set<Integer> getVyskytPorovnavaca() {
        return vyskytPorovnavaca;
    }

    public BigDecimal getBonusNaDieta() {
        return bonusNaDieta;
    }

    public BigDecimal getBonusNaManzelku() {
        return bonusNaManzelku;
    }

    public KonstantyPreKlienta(
            BigDecimal sumaNaPozicanie,
            BigDecimal priemernyMesacnyPrijem,
            BigDecimal mozeNaMesiacMaximalneSplacat,
            int dobaVMesiacoch,
            int pocetDeti,
            BigDecimal bonusNaDieta,
            BigDecimal bonusNaManzelku,
            BigDecimal priemernyMesacnyZostatok) {
        this.sumaNaPozicanie = sumaNaPozicanie;
        this.priemernyMesacnyPrijem = priemernyMesacnyPrijem;
        this.mozeNaMesiacMaximalneSplacat = mozeNaMesiacMaximalneSplacat;
        this.dobaVMesiacoch = dobaVMesiacoch;
        this.pocetDeti = pocetDeti;
        this.bonusNaDieta = bonusNaDieta;
        this.bonusNaManzelku = bonusNaManzelku;
        this.priemernyMesacnyZostatok = priemernyMesacnyZostatok;
    }

    public BigDecimal getPriemernyMesacnyZostatok() {
        return priemernyMesacnyZostatok;
    }

    public BigDecimal getSumaNaPozicanie() {
        return sumaNaPozicanie;
    }

    public BigDecimal getPriemernyMesacnyPrijem() {
        return priemernyMesacnyPrijem;
    }

    public BigDecimal getMozeNaMesiacMaximalneSplacat() {
        return mozeNaMesiacMaximalneSplacat;
    }

    public int getDobaVMesiacoch() {
        return dobaVMesiacoch;
    }

    public int getPocetDeti() {
        return pocetDeti;
    }

}
