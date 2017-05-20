package self.yo.treat.treatyoself;

/**
 * Created by CaptainBat on 20. 05. 2017.
 */

public class Prihranek {

    int id;
    float denar;
    float prihranek;

    // constructors
    public Prihranek() {

    }

    public Prihranek(float denar, float prihranek) {
        this.denar = denar;
        this.prihranek = prihranek;
    }

    public Prihranek(int id, float denar, float prihranek) {
        this.id = id;
        this.denar = denar;
        this.prihranek =  prihranek;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setDenar(float denar) {
        this.denar = denar;
    }

    public void setPrihranek(float prihranek){
        this.prihranek = prihranek;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public float getDenar() {
        return this.denar;
    }

    public float getPrihranek() {return this.prihranek;}

}
