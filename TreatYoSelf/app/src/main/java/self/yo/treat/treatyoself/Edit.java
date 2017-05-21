package self.yo.treat.treatyoself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    DBHelper db;
    int id;
    String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        db = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        Racun r = db.getRacun(Integer.parseInt(id1));
        EditText imee = (EditText) findViewById(R.id.editText3);
        imee.setText(r.getIme());
        EditText koll = (EditText) findViewById(R.id.editText4);
        koll.setText(String.valueOf(r.getPlacilo()));
        Spinner spin1 = (Spinner) findViewById(R.id.spinner);
        spin1.setSelection(r.getIzdajatelj());

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //save button
        Button addButton = (Button) findViewById(R.id.save33);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ime = ((EditText) findViewById(R.id.editText3)).getText().toString();
                float kolicina = Float.valueOf(((EditText) findViewById(R.id.editText4)).getText().toString());
                Spinner spin = (Spinner) findViewById(R.id.spinner);
                int kat = spin.getSelectedItemPosition();
                id = Integer.parseInt(id1);
                Racun r = db.getRacun(id);
                r.setIme(ime);
                r.setPlacilo(kolicina);
                r.setIzdajatelj(kat);
                db.updateRacun(r);
                Racun r2 = db.getRacun(id);
                String lala = r.getIme();
                Log.e("ime", lala);

                Intent intent = new Intent(Edit.this, Spending.class);
                startActivity(intent);
            }
        });
    }
}
