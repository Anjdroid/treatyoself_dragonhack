package self.yo.treat.treatyoself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Name extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        db = new DBHelper(getApplicationContext());

        //save button
        Button addButton = (Button) findViewById(R.id.save_qr);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ime = ((EditText) findViewById(R.id.editText_qr)).getText().toString();
                float kolicina = (float) 12.0;
                String datum = "21.5.2017";
                String nacin = "cash";
                int kat = 1;
                Racun r = new Racun(db.getAllRacuni().size()+1, ime, datum, kat, null, kolicina, nacin);
                long racun_id = db.createRacun(r);
                Varcevanje vv = db.getVarcevanje(1);
                Prihranek p = db.getPrihranek(1);
                float d = p.getDenar();
                float od = p.getPrihranek();
                vv.setVrednost(vv.getVrednost() + (kolicina/od)*d);
                long v2 = db.updateVarcevanje(vv);

                Intent intent = new Intent(Name.this, Spending.class);
                startActivity(intent);
            }
        });
    }
}
