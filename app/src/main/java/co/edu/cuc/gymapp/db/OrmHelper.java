package co.edu.cuc.gymapp.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.edu.cuc.gymapp.model.Cliente;

public class OrmHelper {

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
}
