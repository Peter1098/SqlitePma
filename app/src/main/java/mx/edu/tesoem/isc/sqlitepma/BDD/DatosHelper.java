package mx.edu.tesoem.isc.sqlitepma.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatosHelper extends SQLiteOpenHelper {

    private static DatosHelper datosHelper = null;

    private static String nomDB = "BdAgenda7S11";
    private static int versDB = 1;

    public static class tablaDatos{
        public static String TABLA = "tbAgenda";
        public static String COLUMNA_ID = "ID";
        public static String COLUMNA_NOMBRE = "NOMBRE";
        public static String COLUMNA_EDAD = "EDAD";
        public static String COLUMNA_CORREO = "CORREO";
    }

    public static final String CONSULTA_CREAR_TABLA =
            "CREATE TABLE " + tablaDatos.TABLA + "("
            + tablaDatos.COLUMNA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + tablaDatos.COLUMNA_NOMBRE + " VARCHAR, "
            + tablaDatos.COLUMNA_EDAD + " INTEGER, "
            + tablaDatos.COLUMNA_CORREO + " VARCHAR);"
            ;

    public static final String CONSULTA_ELIMINAR_TABLA = "DROP TABLE IF EXISTS " + tablaDatos.TABLA;

    public static DatosHelper getInstance(Context context){
        if(datosHelper == null){
            datosHelper = new DatosHelper(context.getApplicationContext());
        }
        return datosHelper;
    }

    public DatosHelper(Context context){
        super(context, nomDB, null, versDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONSULTA_CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CONSULTA_ELIMINAR_TABLA);
        onCreate(db);
    }
}
