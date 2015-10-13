package sk.upjs.ics.swib.entity;

import java.util.Calendar;

public class Klient {
    private long id;
    private String meno;
    private String priezvisko;
    private long adresaId;
    private long cisloKarty;
    private Calendar datumNarodenia;
    private String rodneCislo;
    private String cisloPreukazu;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public long getAdresaId() {
        return adresaId;
    }

    public void setAdresaId(long adresaId) {
        this.adresaId = adresaId;
    }

    public long getCisloKarty() {
        return cisloKarty;
    }

    public void setCisloKarty(long cisloKarty) {
        this.cisloKarty = cisloKarty;
    }

    public Calendar getDatumNarodenia() {
        return datumNarodenia;
    }

    public void setDatumNarodenia(Calendar datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    public String getRodneCislo() {
        return rodneCislo;
    }

    public void setRodneCislo(String rodneCislo) {
        this.rodneCislo = rodneCislo;
    }

    public String getCisloPreukazu() {
        return cisloPreukazu;
    }

    public void setCisloPreukazu(String cisloPreukazu) {
        this.cisloPreukazu = cisloPreukazu;
    }
}
