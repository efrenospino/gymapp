package co.edu.cuc.gymapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import co.edu.cuc.gymapp.db.GymAppSQLiteOpenHelper;

public class Sesion {

    private int mId;
    private Horario mHorario;
    private Cliente mCliente;
    private Entrenador mEntrenador;

    public Sesion() {
    }

    public Sesion(int id, Horario horario, Cliente cliente, Entrenador entrenador) {
        mId = id;
        mHorario = horario;
        mCliente = cliente;
        mEntrenador = entrenador;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Horario getHorario() {
        return mHorario;
    }

    public void setHorario(Horario horario) {
        mHorario = horario;
    }

    public Cliente getCliente() {
        return mCliente;
    }

    public void setCliente(Cliente cliente) {
        mCliente = cliente;
    }

    public Entrenador getEntrenador() {
        return mEntrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        mEntrenador = entrenador;
    }

    public void guardar(Context contexto) {

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "INSERT INTO Sesiones values(" + this.mCliente.getId() + "," + this.mEntrenador.getId() + this.mHorario.getId() + "," + ")";
        db.execSQL(sql);

        db.close();
    }

    public void editar(Context contexto) {
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "UPDATE Sesiones SET cliente_id=" + this.mCliente.getId()+ ", entrenador_id=" + this.mEntrenador.getId() + ", horario_id=" + this.mHorario.getId() + " WHERE rowid='" + this.mId + "'";
        db.execSQL(sql);
        db.close();
    }

    public void eliminar(Context contexto) {
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "DELETE FROM Sesiones WHERE rowid='" + this.mId + "'";
        db.execSQL(sql);
        db.close();
    }
}
