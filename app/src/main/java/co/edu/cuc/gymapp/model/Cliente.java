package co.edu.cuc.gymapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.Date;

import co.edu.cuc.gymapp.db.GymAppSQLiteOpenHelper;

public class Cliente {

    private int mId;
    private int mIdentificacion;
    private String mNombre;
    private String mApellido;
    private int mPeso;
    private int mEstatura;
    private String mFechaNacimiento;

    public Cliente() {
    }

    public Cliente(int identificacion, String nombre, String apellido, int peso, int estatura, String fechaNacimiento, int id) {
        mIdentificacion = identificacion;
        mNombre = nombre;
        mApellido = apellido;
        mPeso = peso;
        mEstatura = estatura;
        mFechaNacimiento = fechaNacimiento;
        mId = id;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getApellido() {
        return mApellido;
    }

    public void setApellido(String apellido) {
        mApellido = apellido;
    }

    public int getPeso() {
        return mPeso;
    }

    public void setPeso(int peso) {
        mPeso = peso;
    }

    public int getEstatura() {
        return mEstatura;
    }

    public void setEstatura(int estatura) {
        mEstatura = estatura;
    }

    public String getFechaNacimiento() {
        return mFechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        mFechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getIdentificacion() {
        return mIdentificacion;
    }

    public void setIdentificacion(int identificacion) {
        mIdentificacion = identificacion;
    }

    public void guardar(Context contexto){

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "INSERT INTO Clientes values('"+this.mNombre+"','"+this.mApellido+"','"+this.mIdentificacion+"','"+this.mFechaNacimiento+"','"+this.mPeso+"','"+this.mEstatura +"')";
        db.execSQL(sql);

        db.close();
    }

    public void editar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "UPDATE Clientes SET nombre='"+this.mNombre+"', apellido='"+this.mApellido+"', fecha_nacimiento='"+this.mFechaNacimiento+"', peso='"+this.mPeso+"', altura='"+this.mEstatura+"' WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }

    public void eliminar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db  = aux.getWritableDatabase();

        String sql = "DELETE FROM Clientes WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }


}
