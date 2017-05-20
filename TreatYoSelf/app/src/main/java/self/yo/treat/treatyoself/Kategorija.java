package self.yo.treat.treatyoself;

/**
 * Created by CaptainBat on 20. 05. 2017.
 */

public class Kategorija {

    int id;
    String ime;

    // constructors
    public Kategorija() {

    }

    public Kategorija(String ime) {
        this.ime = ime;
    }

    public Kategorija(int id, String ime) {
        this.id = id;
        this.ime = ime;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getIme() {
        return this.ime;
    }

}
