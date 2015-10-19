package sk.upjs.ics.swib.exceptions;

public class NieJeMozneSplacat extends RuntimeException {

    private final String sprava = "Nie je možné splácať zadanú sumu!";

    public String getSprava() {
        return sprava;
    }
}
