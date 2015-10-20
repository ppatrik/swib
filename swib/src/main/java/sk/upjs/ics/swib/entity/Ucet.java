package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;

public class Ucet {
    private int id;
    private int klientId;
    private int name; //Nazov (db)
    private BigDecimal zostatok;
    private String cisloUctu;
    private boolean spor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKlientId() {
        return klientId;
    }

    public void setKlientId(int klientId) {
        this.klientId = klientId;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
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
