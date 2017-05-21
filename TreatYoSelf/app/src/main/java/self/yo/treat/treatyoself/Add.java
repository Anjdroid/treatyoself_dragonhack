package self.yo.treat.treatyoself;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class Add extends AppCompatActivity {
    DBHelper db;
    String datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = new DBHelper(getApplicationContext());

        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.payment_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter3);

        DatePicker date = (DatePicker) findViewById(R.id.datePicker);
        int day1 = date.getDayOfMonth();
        int month1 = date.getMonth() + 1;
        int year1 = date.getYear();

        datum = Integer.toString(day1)+"."+Integer.toString(month1)+"."+Integer.toString(year1);

        //save button
        Button addButton = (Button) findViewById(R.id.save2);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ime = ((EditText) findViewById(R.id.editText32)).getText().toString();
                float kolicina = Float.valueOf(((EditText) findViewById(R.id.editText42)).getText().toString());
                Spinner spin = (Spinner) findViewById(R.id.spinner2);
                int kat = spin.getSelectedItemPosition();
                Spinner spin2 = (Spinner) findViewById(R.id.spinner3);
                String nacin = spin.getSelectedItem().toString();
                Racun r = new Racun(db.getAllRacuni().size()+1, ime, datum, kat, null, kolicina, nacin);
                long racun_id = db.createRacun(r);
                Varcevanje vv = db.getVarcevanje(1);
                Prihranek p = db.getPrihranek(1);
                float d = p.getDenar();
                float od = p.getPrihranek();
                vv.setVrednost(vv.getVrednost() + (kolicina/od)*d);
                long v2 = db.updateVarcevanje(vv);
                Log.e(nacin, nacin);
                Log.e(ime, ime);

                Intent intent = new Intent(Add.this, Spending.class);
                startActivity(intent);
            }
        });

    }
}
