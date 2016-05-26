package co.edu.cuc.gymapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import co.edu.cuc.gymapp.db.GymAppSQLiteOpenHelper;

public class Horario {

    private int mId;
    private int mHoraInicio;
    private int mHoraFin;

    public Horario() {
    }

    public Horario(int id, int horaInicio, int horaFin) {
        mId = id;
        mHoraInicio = horaInicio;
        mHoraFin = horaFin;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getHoraInicio() {
        return mHoraInicio;
    }

    public void setHoraInicio(int horaInicio) {
        mHoraInicio = horaInicio;
    }

    public int getHoraFin() {
        return mHoraFin;
    }

    public void setHoraFin(int horaFin) {
        mHoraFin = horaFin;
    }

    public void guardar(Context contexto){

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "INSERT INTO Horarios values("+this.mHoraInicio+","+this.mHoraFin+")";
        db.execSQL(sql);

        db.close();
    }

    public void editar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "UPDATE Horarios SET hora_inicio="+this.mHoraInicio+", hora_fin="+this.mHoraFin+" WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }

    public void eliminar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db  = aux.getWritableDatabase();

        String sql = "DELETE FROM Horarios WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }
}
