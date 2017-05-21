package self.yo.treat.treatyoself;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    Varcevanje v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // racunanje varcevanja
        db = new DBHelper(getApplicationContext());
// racunanje varcevanja
        float percent = (float) 0.1;
        Prihranek p1 = new Prihranek(percent, 1);
        long p2 = db.createPrihranek(p1);
        Prihranek p = db.getPrihranek(1);
        float d = p.getDenar();
        float od = p.getPrihranek();
        Log.d("denar", String.valueOf(String.valueOf(d)));
        Log.d("od", String.valueOf(od));

        List<Racun> r = db.getAllRacuni();
        float sum = 0;
        for(Racun ra: r) {
            sum+=ra.getPlacilo();
        }
        Log.d("SUM", String.valueOf(sum));
        float varcar = (sum / od) * d;
        Log.d("VARCAR", String.valueOf(varcar));

        Varcevanje v1 = new Varcevanje(1, 0);
        long v3 = db.createVarcevanje(v1);
        v2 = db.getVarcevanje(1);
        Log.d("VARCEVANJE", String.valueOf(v2.getVrednost()));

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Spending.class);
                intent.putExtra("sum", String.valueOf(v2.getVrednost()));
                startActivity(intent);
            }
        }, 3000L);
    }

    public void setValuesKategorijaIzdajatelj() {

        db = new DBHelper(getApplicationContext());

        // kategorije : food, home, transport, clothing, health, savings, education, cosmetics, fun
        Kategorija k = new Kategorija(0,"food");
        Kategorija k2 = new Kategorija(1,"home");
        Kategorija k3 = new Kategorija(2,"transport");
        Kategorija k4 = new Kategorija(3,"clothing");
        Kategorija k5 = new Kategorija(4,"health");
        Kategorija k6 = new Kategorija(5,"savings");
        Kategorija k7 = new Kategorija(6,"education");
        Kategorija k8 = new Kategorija(7,"cosmetics");
        Kategorija k9 = new Kategorija(8,"fun");

        // izdajatelj : food, home, transport, clothing, health, savings, education, cosmetics, fun
        Izdajatelj i = new Izdajatelj(0,"food",0);
        Izdajatelj i2 = new Izdajatelj(1,"home",1);
        Izdajatelj i3 = new Izdajatelj(2,"transport",2);
        Izdajatelj i4 = new Izdajatelj(3,"clothing",3);
        Izdajatelj i5 = new Izdajatelj(4,"health",4);
        Izdajatelj i6 = new Izdajatelj(5,"savings",5);
        Izdajatelj i7 = new Izdajatelj(6,"education",6);
        Izdajatelj i8 = new Izdajatelj(7,"cosmetics",7);
        Izdajatelj i9 = new Izdajatelj(8,"fun",8);

        long k_id = db.createKategorija(k);
        long k2_id = db.createKategorija(k2);
        long k3_id = db.createKategorija(k3);
        long k4_id = db.createKategorija(k4);
        long k5_id = db.createKategorija(k5);
        long k6_id = db.createKategorija(k6);
        long k7_id = db.createKategorija(k7);
        long k8_id = db.createKategorija(k8);
        long k9_id = db.createKategorija(k9);

        long i_id = db.createIzdajatelj(i);
        long i2_id = db.createIzdajatelj(i2);
        long i3_id = db.createIzdajatelj(i3);
        long i4_id = db.createIzdajatelj(i4);
        long i5_id = db.createIzdajatelj(i5);
        long i6_id = db.createIzdajatelj(i6);
        long i7_id = db.createIzdajatelj(i7);
        long i8_id = db.createIzdajatelj(i8);
        long i9_id = db.createIzdajatelj(i9);
    }
}
