package co.edu.cuc.gymapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import co.edu.cuc.gymapp.db.GymAppSQLiteOpenHelper;

public class Sesion {

    private int mId;
    private int mHoraInicio;
    private int mHoraFin;
    private Cliente mCliente;
    private Entrenador mEntrenador;
    private int mCompletada;

    public Sesion() {
    }

    public Sesion(int id, int horaInicio, int horaFin, Cliente cliente, Entrenador entrenador, int completada) {
        mId = id;
        mHoraInicio = horaInicio;
        mHoraFin = horaFin;
        mEntrenador = entrenador;
        mCliente = cliente;
        mCompletada = completada;
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

    public Entrenador getEntrenador() {
        return mEntrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        mEntrenador = entrenador;
    }

    public Cliente getCliente() {
        return mCliente;
    }

    public void setCliente(Cliente cliente) {
        mCliente = cliente;
    }

    public int isCompletada() {
        return mCompletada;
    }

    public void setCompletada(int completada) {
        mCompletada = completada;
    }

    public void guardar(Context contexto) {

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "INSERT INTO Sesiones values("+this.mHoraInicio+","+this.mHoraFin+"," + this.mCliente.getId() + "," + this.mEntrenador.getId() + "," + this.mCompletada + ")";
        db.execSQL(sql);

        db.close();
    }

    public void editar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "UPDATE Sesiones SET hora_inicio="+this.mHoraInicio+", hora_fin="+this.mHoraFin+", cliente_id=" +
                this.mCliente.getId() + ", entrenador_id=" + this.mEntrenador.getId() + ", completada=" + this.mCompletada +  " WHERE rowid='"+this.mId+"'";

        db.execSQL(sql);
        db.close();
    }

    public void eliminar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db  = aux.getWritableDatabase();

        String sql = "DELETE FROM Sesiones WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }
}
