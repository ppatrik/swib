package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;
import java.util.Calendar;

public class Pohyb {
    private int id;
    private int ucetId;
    private String kamIBAN;
    private BigDecimal suma;
    private Calendar datum;
    private boolean spracovane;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUcetId() {
        return ucetId;
    }

    public void setUcetId(int ucetId) {
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
