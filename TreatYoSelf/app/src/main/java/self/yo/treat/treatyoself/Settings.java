package self.yo.treat.treatyoself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        db = new DBHelper(getApplicationContext());
        Prihranek p1 = db.getPrihranek(1);
        EditText et = (EditText) findViewById(R.id.editText1);
        et.setText(String.valueOf(p1.getDenar()));
        EditText et2 = (EditText) findViewById(R.id.editText2);
        et2.setText(String.valueOf(p1.getPrihranek()));

        //save button
        Button addButton = (Button) findViewById(R.id.save_set);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float tok = Float.valueOf(((EditText) findViewById(R.id.editText1)).getText().toString());
                float od = Float.valueOf(((EditText) findViewById(R.id.editText2)).getText().toString());
                Prihranek p = db.getPrihranek(1);
                p.setDenar(tok);
                p.setPrihranek(od);
                db.updatePrihranek(p);

                Intent intent = new Intent(Settings.this, Spending.class);
                startActivity(intent);
            }
        });
    }
}
