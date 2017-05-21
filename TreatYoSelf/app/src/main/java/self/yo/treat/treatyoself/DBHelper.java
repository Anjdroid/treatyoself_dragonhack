package self.yo.treat.treatyoself;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by CaptainBat on 20. 05. 2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "treatyoselfbase";

    // Table Names
    private static final String TABLE_RACUN = "racun";
    private static final String TABLE_KATEGORIJA = "kategorija";
    private static final String TABLE_IZDAJATELJ = "izdajatelj";
    private static final String TABLE_PRIHRANEK = "prihranek";
    private static final String TABLE_VARCEVANJE = "varcevanje";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_IME = "ime";

    // RACUN columns
    private static final String KEY_DATUM = "datum";
    private static final String KEY_IZDAJATELJ = "izdajatelj";
    private static final String KEY_NASLOV = "naslov";
    private static final String KEY_PLACILO = "placilo";
    private static final String KEY_NACIN_PLACILA = "nacinPlacila";

    //IZDAJATELJ columns
    private static final String KEY_KATEGORIJA = "kategorija";

    // PRIHRANEK columns
    private static final String KEY_DENAR = "denar";
    private static final String KEY_PRIHRANEK = "prihranek";

    // VARCEVANJE
    private static final String KEY_VREDNOST = "vrednost";

    private static final String CREATE_TABLE_VARCEVANJE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_VARCEVANJE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VREDNOST
            + " REAL" + ")";

    // Table Create Statements
// RACUN table create statement
    private static final String CREATE_TABLE_RACUN = "CREATE TABLE IF NOT EXISTS "
            + TABLE_RACUN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IME
            + " TEXT," + KEY_DATUM + " TEXT," + KEY_IZDAJATELJ
            + " INTEGER," + KEY_NASLOV + " TEXT," + KEY_PLACILO + " REAL,"
            + KEY_NACIN_PLACILA + " TEXT" + ")";

    // KATEGORIJA table create statement
    private static final String CREATE_TABLE_KATEGORIJA = "CREATE TABLE IF NOT EXISTS " + TABLE_KATEGORIJA
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_IME + " TEXT" + ")";

    // IZDAJATELJ table create statement
    private static final String CREATE_TABLE_IZDAJATELJ = "CREATE TABLE IF NOT EXISTS "
            + TABLE_IZDAJATELJ + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_IME + " TEXT," + KEY_KATEGORIJA + " INTEGER" + ")";

    // PRIHRANEK
    private static final String CREATE_TABLE_PRIHRANEK = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PRIHRANEK + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_DENAR + " REAL," + KEY_PRIHRANEK + " REAL" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_RACUN);
        db.execSQL(CREATE_TABLE_KATEGORIJA);
        db.execSQL(CREATE_TABLE_IZDAJATELJ);
        db.execSQL(CREATE_TABLE_PRIHRANEK);
        db.execSQL(CREATE_TABLE_VARCEVANJE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RACUN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IZDAJATELJ);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIHRANEK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KATEGORIJA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VARCEVANJE);

        // create new tables
        onCreate(db);
    }

    /*
 * Creating RACUN
 */
    public long createRacun(Racun racun) {
        SQLiteDatabase db = this.getWritableDatabase();

        ////////// ime in datum zamenjano
        ContentValues values = new ContentValues();
        values.put(KEY_IME, racun.getIme());
        values.put(KEY_DATUM, racun.getDatum());

        // poisci id izdajatelja
        values.put(KEY_IZDAJATELJ, racun.getIzdajatelj());
        values.put(KEY_NASLOV, racun.getNaslov());
        values.put(KEY_PLACILO, racun.getPlacilo());
        values.put(KEY_NACIN_PLACILA, racun.getNacinPlacila());

        // insert row
        long racun_id = db.insert(TABLE_RACUN, null, values);

        return racun_id;
    }

    /*
 * getting all racuni
 * */
    public List<Racun> getAllRacuni() {
        List<Racun> racuni = new ArrayList<Racun>();
        String selectQuery = "SELECT  * FROM " + TABLE_RACUN;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Racun r = new Racun();
                r.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                r.setIme((c.getString(c.getColumnIndex(KEY_IME))));
                r.setDatum(c.getString(c.getColumnIndex(KEY_DATUM)));
                r.setIzdajatelj(c.getInt((c.getColumnIndex(KEY_IZDAJATELJ))));
                r.setNaslov((c.getString(c.getColumnIndex(KEY_NASLOV))));
                r.setPlacilo(c.getFloat(c.getColumnIndex(KEY_PLACILO)));
                r.setNacinPlacila(c.getString((c.getColumnIndex(KEY_NACIN_PLACILA))));

                // adding to racun
                racuni.add(r);
            } while (c.moveToNext());
        }

        return racuni;
    }

    // get 1 racun
    public Racun getRacun(long racun_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RACUN + " WHERE "
                + KEY_ID + " = " + racun_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Racun r = new Racun();
        r.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        r.setIme((c.getString(c.getColumnIndex(KEY_IME))));
        r.setDatum(c.getString(c.getColumnIndex(KEY_DATUM)));
        r.setIzdajatelj((c.getInt(c.getColumnIndex(KEY_IZDAJATELJ))));
        r.setNaslov(c.getString(c.getColumnIndex(KEY_NASLOV)));
        r.setPlacilo((c.getFloat(c.getColumnIndex(KEY_PLACILO))));
        r.setNacinPlacila(c.getString(c.getColumnIndex(KEY_NACIN_PLACILA)));

        return r;
    }

    // posodobi racun
    public int updateRacun(Racun r) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IME, r.getIme());
        values.put(KEY_PLACILO, r.getPlacilo());
        values.put(KEY_IZDAJATELJ, r.getIzdajatelj());
        // TODO glede na kategorijo je potrebno posodobiti izdajatelja

        // updating row
        return db.update(TABLE_RACUN, values, KEY_ID + " = ?",
                new String[] { String.valueOf(r.getId()) });
    }

    // update prihranek
    public long updatePrihranek(Prihranek prihranek) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DENAR, prihranek.getDenar());
        values.put(KEY_PRIHRANEK, prihranek.getPrihranek());

        // updating row
        return db.update(TABLE_PRIHRANEK, values, KEY_ID + " = ?",
                new String[] { String.valueOf(prihranek.getId()) });
    }

    // create prihranek
    public long createPrihranek(Prihranek prihranek) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DENAR, prihranek.getDenar());
        values.put(KEY_PRIHRANEK, prihranek.getPrihranek());

        // insert row
        long prihranek_id = db.insert(TABLE_PRIHRANEK, null, values);

        return prihranek_id;
    }

    // get prihranek
    public Prihranek getPrihranek(long prihranek_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PRIHRANEK + " WHERE "
                + KEY_ID + " = " + prihranek_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Prihranek p = new Prihranek();
        p.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        p.setDenar((c.getFloat(c.getColumnIndex(KEY_DENAR))));
        p.setPrihranek(c.getFloat(c.getColumnIndex(KEY_PRIHRANEK)));

        return p;
    }

    public  void deletePrihranek(long prihranek_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRIHRANEK, KEY_ID + " = ?",
                new String[] { String.valueOf(prihranek_id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    ////////////////
    // create kategorija
    public long createKategorija(Kategorija kategorija) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, kategorija.getId());
        values.put(KEY_IME, kategorija.getIme());

        // insert row
        long kategorija_id = db.insert(TABLE_KATEGORIJA, null, values);

        return kategorija_id;
    }

    // create izdajatelj
    public long createIzdajatelj(Izdajatelj izdajatelj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, izdajatelj.getId());
        values.put(KEY_IME, izdajatelj.getIme());
        values.put(KEY_KATEGORIJA, izdajatelj.getKategorija());

        // insert row
        long izdajatelj_id = db.insert(TABLE_IZDAJATELJ, null, values);

        return izdajatelj_id;
    }
    ////////////////////////

    public long createVarcevanje(Varcevanje varcevanje) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VREDNOST, varcevanje.getVrednost());

        // insert row
        long varcevanje_id = db.insert(TABLE_VARCEVANJE, null, values);

        return varcevanje_id;
    }

    public int updateVarcevanje(Varcevanje v) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VREDNOST, v.getVrednost());

        // updating row
        return db.update(TABLE_VARCEVANJE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(v.getId()) });
    }

    public Varcevanje getVarcevanje(int varcevanje_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_VARCEVANJE + " WHERE "
                + KEY_ID + " = " + varcevanje_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Varcevanje p = new Varcevanje();
        p.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        p.setVrednost((c.getFloat(c.getColumnIndex(KEY_VREDNOST))));

        return p;
    }
}