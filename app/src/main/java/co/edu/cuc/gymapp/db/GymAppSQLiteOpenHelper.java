package co.edu.cuc.gymapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GymAppSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gyampp.db";
    private static final int VERSION = 2;
    private String mEntrenadorSQL = "CREATE TABLE Entrenadores (nombre TEXT, apellido TEXT, identificacion TEXT, fecha_nacimiento TEXT, peso INT, altura INT)";
    private String mClientesSQL = "CREATE TABLE Clientes (nombre TEXT, apellido TEXT, identificacion TEXT, fecha_nacimiento TEXT, peso INT, altura INT)";
    private String mHorarioSQL = "CREATE TABLE Horarios (hora_inicio INT, hora_fin INT)";
    private String mSesionSQL = "CREATE TABLE Sesiones (cliente_id INT, entrenador_id INT, horario_id INT)";


    public GymAppSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mEntrenadorSQL);
        db.execSQL(mClientesSQL);
        db.execSQL(mHorarioSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Entrenadores");
        db.execSQL(mEntrenadorSQL);
        db.execSQL("DROP TABLE IF EXISTS Clientes");
        db.execSQL(mClientesSQL);
        db.execSQL("DROP TABLE IF EXISTS Horarios");
        db.execSQL(mHorarioSQL);
        db.execSQL("DROP TABLE IF EXISTS Sesiones");
        db.execSQL(mSesionSQL);
    }
}
