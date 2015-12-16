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

    // pri pridavani bonusov
    private final Set<Integer> vyskytPorovnavaca = new HashSet<>();

    public Set<Integer> getVyskytPorovnavaca() {
        return vyskytPorovnavaca;
    }

    public KonstantyPreKlienta(
            BigDecimal sumaNaPozicanie,
            BigDecimal priemernyMesacnyPrijem,
            BigDecimal mozeNaMesiacMaximalneSplacat,
            int dobaVMesiacoch,
            BigDecimal priemernyMesacnyZostatok) {
        this.sumaNaPozicanie = sumaNaPozicanie;
        this.priemernyMesacnyPrijem = priemernyMesacnyPrijem;
        this.mozeNaMesiacMaximalneSplacat = mozeNaMesiacMaximalneSplacat;
        this.dobaVMesiacoch = dobaVMesiacoch;
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
}
