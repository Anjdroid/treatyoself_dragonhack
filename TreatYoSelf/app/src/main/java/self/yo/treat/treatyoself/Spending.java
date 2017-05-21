package self.yo.treat.treatyoself;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class Spending extends AppCompatActivity {
    DBHelper db;
    int click = 1;
    int hel = 30;

    public class MyListener implements View.OnClickListener {
        int id;
        public MyListener(int id){
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Spending.this, Edit.class);
            intent.putExtra("id", Integer.toString(this.id));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);
        db = new DBHelper(getApplicationContext());
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width1 = size.x;
        Varcevanje v2 = db.getVarcevanje(1);
        String lala = String.valueOf(v2.getVrednost());

        TextView tww = (TextView) findViewById(R.id.txt2);
        tww.setText(lala+" €");

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.exp2);
        List<Racun> racuncki = db.getAllRacuni();
        for(Racun r: racuncki) {
            Log.d("ime", Integer.toString(r.getId()));
            RelativeLayout rl2 = new RelativeLayout(this);
            rl2.setBackgroundColor(0xFFFF5E95);
            rl2.setId(r.getId());
            float hel2 = (float) ((click-1)*130);
            rl2.setPadding(30, 30, 30, 30);
            rl2.setTranslationY(hel2);
            TextView tw = new TextView(this);
            tw.setText(r.getIme());
            tw.setWidth(width1);
            tw.setTypeface(null, Typeface.BOLD);
            tw.setGravity(Gravity.LEFT);
            tw.setTextColor(Color.parseColor("#FFFFFF"));
            TextView tw2 = new TextView(this);
            tw2.setText(String.valueOf(r.getPlacilo())+" €");
            tw2.setWidth(width1);
            tw2.setGravity(Gravity.RIGHT);
            tw2.setTypeface(null, Typeface.BOLD);
            tw2.setTextColor(Color.parseColor("#FFFFFF"));
            rl2.addView(tw);
            rl2.addView(tw2);
            rl.addView(rl2);
            click++;
            rl2.setOnClickListener(new MyListener(rl2.getId()));
        }

        //add button
        Button addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spending.this, Add.class);
                startActivity(intent);
            }
        });

        //edit expense
        /*RelativeLayout expense = (RelativeLayout) findViewById(R.id.exp1);
        expense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spending.this, Edit.class);
                intent.putExtra("id", "lala");
                startActivity(intent);
            }
        });*/

        //scan button
        Button scanButton = (Button) findViewById(R.id.scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spending.this, QR_Scanner.class);
                startActivity(intent);
            }
        });

        //settings button
        ImageButton setButton = (ImageButton) findViewById(R.id.set);
        setButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Spending.this, Settings.class);
                startActivity(intent);
            }
        });

        Button saveupButton = (Button) findViewById(R.id.saveup);
        saveupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                String datum = Integer.toString(c.get(Calendar.DAY_OF_MONTH))+"."+Integer.toString(c.get(Calendar.MONTH))+"."+Integer.toString(c.get(Calendar.YEAR));
                Varcevanje vv = db.getVarcevanje(1);
                float kolicina = vv.getVrednost();
                vv.setVrednost(0);
                long v2 = db.updateVarcevanje(vv);
                Racun r = new Racun(db.getAllRacuni().size()+1, "Save-up", datum, 5, null, kolicina, "cash");
                long racun_id = db.createRacun(r);
                Intent intent = new Intent(Spending.this, Spending.class);
                startActivity(intent);
            }
        });
    }
}
