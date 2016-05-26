package co.edu.cuc.gymapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.edu.cuc.gymapp.model.Cliente;
import co.edu.cuc.gymapp.model.Entrenador;
import co.edu.cuc.gymapp.model.Sesion;

public class OrmHelper {

    public static ArrayList<Entrenador> traerEntrenadores(Context contexto) {
        SQLiteDatabase db;
        String sql, nombre, apellido, cedula, cumpleaños, peso, estatura;
        int rowid, genero;
        ArrayList<Entrenador> entrenadores = new ArrayList<>();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        db = aux.getReadableDatabase();

        sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, genero, rowid FROM Entrenadores";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                nombre = c.getString(0);
                apellido = c.getString(1);
                cedula = c.getString(2);
                cumpleaños = c.getString(3);
                peso = c.getString(4);
                estatura = c.getString(5);
                genero = c.getInt(6);
                rowid = c.getInt(7);
                Entrenador entrenador = new Entrenador(cedula, nombre, apellido, Integer.valueOf(peso), Integer.valueOf(estatura), cumpleaños, rowid, genero);
                entrenadores.add(entrenador);
            } while (c.moveToNext());
        }

        db.close();
        return entrenadores;
    }

    public static ArrayList<Cliente> traerClientes(Context contexto) {
        SQLiteDatabase db;
        String sql, nombre, apellido, cedula, cumpleaños, peso, estatura;
        int rowid, genero;
        ArrayList<Cliente> clientes = new ArrayList<>();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        db = aux.getReadableDatabase();

        sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, genero, rowid FROM Clientes";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                nombre = c.getString(0);
                apellido = c.getString(1);
                cedula = c.getString(2);
                cumpleaños = c.getString(3);
                peso = c.getString(4);
                estatura = c.getString(5);
                genero = Integer.parseInt(c.getString(6));
                rowid = c.getInt(7);
                Cliente cliente = new Cliente (cedula, nombre, apellido, Integer.valueOf(peso), Integer.valueOf(estatura), cumpleaños, rowid, genero);
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

        String sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, genero, rowid FROM Clientes WHERE rowid = " + id + ";";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            cliente.setNombre(c.getString(0));
            cliente.setApellido(c.getString(1));
            cliente.setIdentificacion(c.getString(2));
            cliente.setFechaNacimiento(c.getString(3));
            cliente.setPeso(Integer.parseInt(c.getString(4)));
            cliente.setEstatura(Integer.parseInt(c.getString(5)));
            cliente.setGenero(c.getInt(6));
            cliente.setId(c.getInt(7));
        }
        db.close();

        return cliente;
    }

    public static Entrenador buscarEntrenadorPorId(Context contexto, int id) {
        Entrenador entrenador = new Entrenador();
        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(contexto);
        SQLiteDatabase db = aux.getReadableDatabase();

        String sql = "SELECT nombre, apellido, identificacion, fecha_nacimiento, peso, altura, genero, rowid FROM Entrenadores WHERE rowid = " + id + ";";
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            entrenador.setNombre(c.getString(0));
            entrenador.setApellido(c.getString(1));
            entrenador.setIdentificacion(c.getString(2));
            entrenador.setFechaNacimiento(c.getString(3));
            entrenador.setPeso(Integer.parseInt(c.getString(4)));
            entrenador.setAltura(Integer.parseInt(c.getString(5)));
            entrenador.setGenero(c.getInt(6));
            entrenador.setId(c.getInt(7));
        }
        db.close();

        return entrenador;
    }

    public static Sesion buscarHorarioPorId(Context context, int id) {
        SQLiteDatabase db;
        Sesion sesion = new Sesion();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(context);
        db = aux.getReadableDatabase();

        String sql = "SELECT hora_inicio, hora_fin, cliente_id, entrenador_id, completada, rowid FROM Sesiones WHERE rowid=" + id + ";";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            sesion.setHoraInicio(c.getInt(0));
            sesion.setHoraFin(c.getInt(1));
            sesion.setCliente(buscarClientePorId(context, c.getInt(2)));
            sesion.setEntrenador(buscarEntrenadorPorId(context, c.getInt(3)));
            sesion.setCompletada(c.getInt(4));
            sesion.setId(c.getInt(5));
        }

        db.close();
        return sesion;
    }

    public static List<Sesion> traerSesiones(Context context) {
        SQLiteDatabase db;
        int rowid, horaInicio, horaFin, clienteId, entrenadorId, completada;

        ArrayList<Sesion> sesiones = new ArrayList<>();

        GymAppSQLiteOpenHelper aux = new GymAppSQLiteOpenHelper(context);
        db = aux.getReadableDatabase();

        String sql = "SELECT hora_inicio, hora_fin, cliente_id, entrenador_id, completada, rowid FROM Sesiones";

        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                horaInicio = c.getInt(0);
                horaFin = c.getInt(1);
                clienteId = c.getInt(2);
                entrenadorId = c.getInt(3);
                completada = c.getInt(4);
                rowid = c.getInt(5);
                Sesion sesion = new Sesion(rowid, horaInicio, horaFin, buscarClientePorId(context, clienteId), buscarEntrenadorPorId(context, entrenadorId), completada);
                sesiones.add(sesion);
            } while (c.moveToNext());
        }

        db.close();
        return sesiones;
    }
}
