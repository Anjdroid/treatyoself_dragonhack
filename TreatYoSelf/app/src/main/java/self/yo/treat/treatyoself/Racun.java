package self.yo.treat.treatyoself;

import java.util.Date;

/**
 * Created by CaptainBat on 20. 05. 2017.
 */

public class Racun {

    int id;
    String datum;
    String ime;
    int izdajatelj;
    String naslov;
    float placilo;
    String nacinPlacila;

    // constructors
    public Racun() {

    }

    public Racun(String ime) {
        this.ime = ime;
    }

    public Racun(int id, String ime, String datum, int izdajatelj, String naslov, float placilo, String nacinPlacila) {
        this.id = id;
        this.ime = ime;
        this.datum = datum;
        this.izdajatelj = izdajatelj;
        this.naslov = naslov;
        this.placilo = placilo;
        this.nacinPlacila = nacinPlacila;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setIzdajatelj(int izdajatelj) {
        this.izdajatelj = izdajatelj;
    }

    public void setDatumm(String datum) {
        this.datum = datum;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public void setNacinPlacila(String nacinPlacila) {
        this.nacinPlacila = nacinPlacila;
    }

    public void setPlacilo(float placilo) {
        this.placilo = placilo;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getIme() {
        return this.ime;
    }

    public int getIzdajatelj() {
        return this.izdajatelj;
    }

    public String getDatum() {
        return this.datum;
    }

    public String getNaslov() {
        return this.naslov;
    }

    public float getPlacilo() {
        return this.placilo;
    }

    public String getNacinPlacila() {
        return this.nacinPlacila;
    }

}
