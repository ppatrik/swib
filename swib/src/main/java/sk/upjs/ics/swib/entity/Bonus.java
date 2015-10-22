package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;

public class Bonus {
    private int id;
    private int uverId;
    private String nazov;
    private BigDecimal jeVacsiAko;
    private BigDecimal kolkoJeBonus;
    private int multiplikatorId;
    private int porovnavacId;
    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUverId() {
        return uverId;
    }

    public void setUverId(int uverId) {
        this.uverId = uverId;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public BigDecimal getJeVacsiAko() {
        return jeVacsiAko;
    }

    public void setJeVacsiAko(BigDecimal jeVacsiAko) {
        this.jeVacsiAko = jeVacsiAko;
    }

    public BigDecimal getKolkoJeBonus() {
        return kolkoJeBonus;
    }

    public void setKolkoJeBonus(BigDecimal kolkoJeBonus) {
        this.kolkoJeBonus = kolkoJeBonus;
    }

    public int getMultiplikatorId() {
        return multiplikatorId;
    }

    public void setMultiplikatorId(int multiplikatorId) {
        this.multiplikatorId = multiplikatorId;
    }

    public int getPorovnavacId() {
        return porovnavacId;
    }

    public void setPorovnavacId(int porovnavacId) {
        this.porovnavacId = porovnavacId;
    }
}
