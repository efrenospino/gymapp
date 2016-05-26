package co.edu.cuc.gymapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.edu.cuc.gymapp.ListaHorariosActivity;
import co.edu.cuc.gymapp.model.Cliente;
import co.edu.cuc.gymapp.model.Entrenador;
import co.edu.cuc.gymapp.model.Horario;

public class OrmHelper {

    public static ArrayList<Entrenador> traerEntrenadores(Context contexto) {
        SQLiteDatabase db;
        String sql, nombre, apellido, cedula, cumpleaños, peso, estatura;
        int rowid;
        ArrayList<Entrenador> entrenadores = new ArrayList<>();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        db = aux.getReadableDatabase();

        sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, rowid FROM Entrenadores";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                nombre = c.getString(0);
                apellido = c.getString(1);
                cedula = c.getString(2);
                cumpleaños = c.getString(3);
                peso = c.getString(4);
                estatura = c.getString(5);
                rowid = c.getInt(6);
                Entrenador entrenador = new Entrenador(Integer.valueOf(cedula), nombre, apellido, Integer.valueOf(peso), Integer.valueOf(estatura), cumpleaños, rowid);
                entrenadores.add(entrenador);
            } while (c.moveToNext());
        }

        db.close();
        return entrenadores;
    }

    public static ArrayList<Cliente> traerClientes(Context contexto) {
        SQLiteDatabase db;
        String sql, nombre, apellido, cedula, cumpleaños, peso, estatura;
        int rowid;
        ArrayList<Cliente> clientes = new ArrayList<>();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        db = aux.getReadableDatabase();

        sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, rowid FROM Clientes";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                nombre = c.getString(0);
                apellido = c.getString(1);
                cedula = c.getString(2);
                cumpleaños = c.getString(3);
                peso = c.getString(4);
                estatura = c.getString(5);
                rowid = c.getInt(6);
                Cliente cliente = new Cliente(Integer.valueOf(cedula), nombre, apellido, Integer.valueOf(peso), Integer.valueOf(estatura), cumpleaños, rowid);
                clientes.add(cliente);
            } while (c.moveToNext());
        }

        db.close();
        return clientes;
    }

    public static Cliente buscarClientePorId(Context contexto, int id) {
        Cliente cliente = new Cliente();
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getReadableDatabase();

        String sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, rowid FROM Clientes WHERE rowid = " + id + ";";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            cliente.setNombre(c.getString(0));
            cliente.setApellido(c.getString(1));
            cliente.setIdentificacion(Integer.parseInt(c.getString(2)));
            cliente.setFechaNacimiento(c.getString(3));
            cliente.setPeso(Integer.parseInt(c.getString(4)));
            cliente.setEstatura(Integer.parseInt(c.getString(5)));
            cliente.setId(c.getInt(6));
        }
        db.close();

        return cliente;
    }

    public static Entrenador buscarEntrenadorPorId(Context contexto, int id) {
        Entrenador entrenador = new Entrenador();
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getReadableDatabase();

        String sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, rowid FROM Entrenadores WHERE rowid = " + id + ";";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            entrenador.setNombre(c.getString(0));
            entrenador.setApellido(c.getString(1));
            entrenador.setIdentificacion(Integer.parseInt(c.getString(2)));
            entrenador.setFechaNacimiento(c.getString(3));
            entrenador.setPeso(Integer.parseInt(c.getString(4)));
            entrenador.setAltura(Integer.parseInt(c.getString(5)));
            entrenador.setId(c.getInt(6));
        }
        db.close();

        return entrenador;
    }

    public static Horario buscarHorarioPorId(Context context, int id) {
        SQLiteDatabase db;
        Horario horario = new Horario();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(context);
        db = aux.getReadableDatabase();

        String sql = "SELECT hora_inicio, hora_fin, rowid FROM Horarios WHERE rowid=" + id + ";";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            horario.setHoraInicio(c.getInt(0));
            horario.setHoraFin(c.getInt(1));
            horario.setId(c.getInt(2));
        }

        db.close();
        return horario;
    }

    public static List<Horario> traerHorarios(Context context) {
        SQLiteDatabase db;
        int rowid, horaInicio, horaFin;

        ArrayList<Horario> horarios = new ArrayList<>();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(context);
        db = aux.getReadableDatabase();

        String sql = "SELECT hora_inicio, hora_fin, rowid FROM Horarios";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                horaInicio = c.getInt(0);
                horaFin = c.getInt(1);
                rowid = c.getInt(2);
                Horario horario = new Horario(rowid, horaInicio, horaFin);
                horarios.add(horario);
            } while (c.moveToNext());
        }

        db.close();
        return horarios;
    }
}
