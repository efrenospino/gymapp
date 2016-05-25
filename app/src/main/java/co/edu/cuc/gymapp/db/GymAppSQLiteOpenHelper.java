package co.edu.cuc.gymapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GymAppSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gyampp.db";
    private static final int VERSION = 1;
    private String trainerUpSQL = "CREATE TABLE Entrenadores (nombre TEXT, apellido TEXT, identificacion TEXT, fecha_nacimiento TEXT, peso INT, altura INT)";
    private String costumerUpSQL = "CREATE TABLE Clientes (nombre TEXT, apellido TEXT, identificacion TEXT, fecha_nacimiento TEXT, peso INT, altura INT)";

    public GymAppSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(trainerUpSQL);
        db.execSQL(costumerUpSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Entrenadores");
        db.execSQL(trainerUpSQL);
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(costumerUpSQL);
    }
}
