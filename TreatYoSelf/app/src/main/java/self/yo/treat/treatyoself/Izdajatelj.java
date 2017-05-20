package self.yo.treat.treatyoself;

/**
 * Created by CaptainBat on 20. 05. 2017.
 */

public class Izdajatelj {

    int id;
    String ime;
    int kategorija;

    // constructors
    public Izdajatelj() {

    }

    public Izdajatelj(String ime) {
        this.ime = ime;
    }

    public Izdajatelj(int id, String ime, int kategorija) {
        this.id = id;
        this.ime = ime;
        this.kategorija = kategorija;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setKategorija(int kategorija) {
        this.kategorija = kategorija;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getIme() {
        return this.ime;
    }

    public int getKategorija() {
        return this.kategorija;
    }

}
