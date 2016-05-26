package co.edu.cuc.gymapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import co.edu.cuc.gymapp.db.GymAppSQLiteOpenHelper;

public class Entrenador {

    private int mIdentificacion;
    private String mNombre;
    private String mApellido;
    private int mPeso;
    private int mAltura;
    private String mFechaNacimiento;
    private int mId;

    public Entrenador() {
    }

    public Entrenador(int identificacion, String nombre, String apellido, int peso, int altura, String fechaNacimiento, int id) {
        mIdentificacion = identificacion;
        mNombre = nombre;
        mApellido = apellido;
        mPeso = peso;
        mAltura = altura;
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

    public int getAltura() {
        return mAltura;
    }

    public void setAltura(int altura) {
        mAltura = altura;
    }

    public String getFechaNacimiento() {
        return mFechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        mFechaNacimiento = fechaNacimiento;
    }

    public int getIdentificacion() {
        return mIdentificacion;
    }

    public void setIdentificacion(int identificacion) {
        mIdentificacion = identificacion;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public void guardar(Context contexto){

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "INSERT INTO Entrenadores values('"+this.mNombre+"','"+this.mApellido+"','"+this.mIdentificacion+"','"+this.mFechaNacimiento+"','"+this.mPeso+"','"+this.mAltura +"')";
        db.execSQL(sql);

        db.close();
    }

    public void editar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getWritableDatabase();

        String sql = "UPDATE Entrenadores SET nombre='"+this.mNombre+"', apellido='"+this.mApellido+"', fecha_nacimiento='"+this.mFechaNacimiento+"', peso='"+this.mPeso+"', altura='"+this.mAltura+"' WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }

    public void eliminar(Context contexto){
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db  = aux.getWritableDatabase();

        String sql = "DELETE FROM Entrenadores WHERE rowid='"+this.mId+"'";
        db.execSQL(sql);
        db.close();
    }
}
