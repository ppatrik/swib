package sk.upjs.ics.swib.entity;

import java.math.BigDecimal;

public class Uver {
    private int id;
    private String nazov;
    private BigDecimal bonusNaManzelku;
    private BigDecimal bonusNaDieta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public BigDecimal getBonusNaManzelku() {
        return bonusNaManzelku;
    }

    public void setBonusNaManzelku(BigDecimal bonusNaManzelku) {
        this.bonusNaManzelku = bonusNaManzelku;
    }

    public BigDecimal getBonusNaDieta() {
        return bonusNaDieta;
    }

    public void setBonusNaDieta(BigDecimal bonusNaDieta) {
        this.bonusNaDieta = bonusNaDieta;
    }
    
    
}
