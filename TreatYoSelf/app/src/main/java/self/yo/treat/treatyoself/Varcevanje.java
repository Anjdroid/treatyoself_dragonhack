package self.yo.treat.treatyoself;

/**
 * Created by CaptainBat on 21. 05. 2017.
 */
public class Varcevanje {

    int id;
    float vrednost;

    // constructors
    public Varcevanje() {

    }

    public Varcevanje(float vrednost) {
        this.vrednost = vrednost;
    }

    public Varcevanje(int id, float vrednost) {
        this.id = id;
        this.vrednost = vrednost;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setVrednost(float vrednost) {
        this.vrednost = vrednost;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public float getVrednost() {
        return this.vrednost;
    }

}
