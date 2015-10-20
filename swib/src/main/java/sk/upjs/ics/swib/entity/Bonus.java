package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;

public class Bonus {
    private long id;
    private long uverId;
    private String nazov;
    private BigDecimal jeVacsiAko;
    private BigDecimal kolkoJeBonus;
    private long multiplikatorId;
    private long porovnavacId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUverId() {
        return uverId;
    }

    public void setUverId(long uverId) {
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

    public long getMultiplikatorId() {
        return multiplikatorId;
    }

    public void setMultiplikatorId(long multiplikatorId) {
        this.multiplikatorId = multiplikatorId;
    }

    public long getPorovnavacId() {
        return porovnavacId;
    }

    public void setPorovnavacId(long porovnavacId) {
        this.porovnavacId = porovnavacId;
    }
}
