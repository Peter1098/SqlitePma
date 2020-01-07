package mx.edu.tesoem.isc.sqlitepma.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mx.edu.tesoem.isc.ajsm.p3p5ajsm.BDD.DatosHelper.*;

public class DatosConexion {

    private SQLiteDatabase baseDatos;
    private DatosHelper datosHelper;

    public DatosConexion(Context context) {
        datosHelper = DatosHelper.getInstance(context);
    }

    public void open(){
        baseDatos = datosHelper.getWritableDatabase();
    }

    public void close(){
        baseDatos.close();
    }

    public String[] fillGridView(){
        String[] datos;
        int col = 4;
        DatosHelper.tablaDatos tablaDatos;
        Cursor cursor = baseDatos.rawQuery("SELECT * FROM " + tablaDatos.TABLA, null);
        if(cursor.getCount() <= 0){
            datos = new String[4];
            datos[0] = tablaDatos.COLUMNA_ID;
            datos[1] = tablaDatos.COLUMNA_NOMBRE;
            datos[2] = tablaDatos.COLUMNA_EDAD;
            datos[3] = tablaDatos.COLUMNA_CORREO;
        }else{
            datos = new String[(cursor.getCount() * 4) + 4];
            datos[0] = tablaDatos.COLUMNA_ID;
            datos[1] = tablaDatos.COLUMNA_NOMBRE;
            datos[2] = tablaDatos.COLUMNA_EDAD;
            datos[3] = tablaDatos.COLUMNA_CORREO;
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                datos[col] = String.valueOf(cursor.getInt(0));
                datos[col + 1] = cursor.getString(1);
                datos[col + 2] = String.valueOf(cursor.getInt(2));
                datos[col + 3] = cursor.getString(3);
                col += 4;
                cursor.moveToNext();
            }
        }
        return datos;
    }

    public boolean insert(ContentValues contentValues){
        boolean estado = true;
        baseDatos.isOpen();
        int resultadoConsulta = (int)baseDatos.insert(tablaDatos.TABLA, null, contentValues);
        if(!(resultadoConsulta == 1)){
            estado = false;
        }
        baseDatos.close();
        return estado;
    }

    public boolean update(ContentValues contentValues, String[] condicion){
        boolean estado = true;
        baseDatos.isOpen();
        int resultadoConsulta = (int)baseDatos.update(tablaDatos.TABLA, contentValues, tablaDatos.COLUMNA_ID + "= ?", condicion);
        if(!(resultadoConsulta == 1)){
            estado = false;
        }
        baseDatos.close();
        return estado;
    }

    public boolean delete(String[] condicion){
        boolean estado = true;
        baseDatos.isOpen();
        int resultadoConsulta = (int)baseDatos.delete(tablaDatos.TABLA, tablaDatos.COLUMNA_ID + "= ?", condicion);
        if(!(resultadoConsulta == 1)){
            estado = false;
        }
        baseDatos.close();
        return estado;
    }
}
