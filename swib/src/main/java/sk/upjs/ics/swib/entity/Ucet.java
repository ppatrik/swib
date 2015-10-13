package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;

public class Ucet {
    private long id;
    private long klientId;
    private long name;
    private BigDecimal zostatok;
    private String cisloUctu;
    private boolean spor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getKlientId() {
        return klientId;
    }

    public void setKlientId(long klientId) {
        this.klientId = klientId;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public BigDecimal getZostatok() {
        return zostatok;
    }

    public void setZostatok(BigDecimal zostatok) {
        this.zostatok = zostatok;
    }

    public String getCisloUctu() {
        return cisloUctu;
    }

    public void setCisloUctu(String cisloUctu) {
        this.cisloUctu = cisloUctu;
    }

    public boolean isSpor() {
        return spor;
    }

    public void setSpor(boolean spor) {
        this.spor = spor;
    }
}
