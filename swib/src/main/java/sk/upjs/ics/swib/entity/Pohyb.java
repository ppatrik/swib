package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;
import java.util.Calendar;

public class Pohyb {
    private long id;
    private long ucetId;
    private String kamIBAN;
    private BigDecimal suma;
    private Calendar datum;
    private boolean spracovane;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUcetId() {
        return ucetId;
    }

    public void setUcetId(long ucetId) {
        this.ucetId = ucetId;
    }

    public String getKamIBAN() {
        return kamIBAN;
    }

    public void setKamIBAN(String kamIBAN) {
        this.kamIBAN = kamIBAN;
    }

    public BigDecimal getSuma() {
        return suma;
    }

    public void setSuma(BigDecimal suma) {
        this.suma = suma;
    }

    public Calendar getDatum() {
        return datum;
    }

    public void setDatum(Calendar datum) {
        this.datum = datum;
    }

    public boolean isSpracovane() {
        return spracovane;
    }

    public void setSpracovane(boolean spracovane) {
        this.spracovane = spracovane;
    }
}
