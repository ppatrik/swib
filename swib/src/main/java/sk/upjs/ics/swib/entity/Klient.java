package sk.upjs.ics.swib.entity;

import java.util.Calendar;

public class Klient {
    private int id;
    private String meno;
    private String priezvisko;
    private int cisloKarty; //C_karty (db)
    private Calendar datumNarodenia; //Dat_nar (db)
    private String rodneCislo; //Rodne_cislo (db)
    private String cisloPreukazu; //C_preukazu (db)

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCisloKarty() {
        return cisloKarty;
    }

    public void setCisloKarty(int cisloKarty) {
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
