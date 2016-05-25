package co.edu.cuc.gymapp.model;

import java.util.Date;

public class Entrenador {

    private int mIdentificacion;
    private String mNombre;
    private String mApellido;
    private int mPeso;
    private int mAltura;
    private Date mFechaNacimiento;

    public Entrenador() {
    }

    public Entrenador(int identificacion, String nombre, String apellido, int peso, int altura, Date fechaNacimiento) {
        mIdentificacion = identificacion;
        mNombre = nombre;
        mApellido = apellido;
        mPeso = peso;
        mAltura = altura;
        mFechaNacimiento = fechaNacimiento;
    }

    public int getIdentificationNumber() {
        return mIdentificacion;
    }

    public void setIdentificationNumber(int identificationNumber) {
        mIdentificacion = identificationNumber;
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

    public Date getFechaNacimiento() {
        return mFechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        mFechaNacimiento = fechaNacimiento;
    }
}
