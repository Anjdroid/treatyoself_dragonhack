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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class Spending extends AppCompatActivity {
    DBHelper db;
    String click = "1";
    int hel = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);
        db = new DBHelper(getApplicationContext());
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width1 = size.x;

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.exp2);
        List<Racun> racuncki = db.getAllRacuni();
        for(Racun r: racuncki) {
            Log.d("ime", Integer.toString(r.getId()));
            RelativeLayout rl2 = new RelativeLayout(this);
            rl2.setBackgroundColor(0xFFFF5E95);
            rl2.setId(r.getId());
            float hel2 = (float) ((Integer.parseInt(click)-1)*130);
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
            click = Integer.toString(rl2.getId());
            rl2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Spending.this, Edit.class);
                    intent.putExtra("id", click);
                    startActivity(intent);
                }
            });
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
    }
}
