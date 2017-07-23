package shafir.irena.sqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by irena on 21/07/2017.
 */

// an sqlite Data Base Helper
    // creates the data base
    // access the data base
    // modify the data base as necessary

public class TodosDBHelper extends SQLiteOpenHelper{

    public TodosDBHelper(Context context) {
        super(context, TodosContract.DBName , null, TodosContract.DBVersion);
    }

    // will run once- when database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
      // SQL script that will assist to create the charts of the data base

        db.execSQL("CREATE TABLE "+
                TodosContract.TBL_TODOS + "(" +
                TodosContract.TBL_TODOS_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TodosContract.TBL_TODOS_COL_MISSION + " TEXT NOT NULL, " +
                TodosContract.TBL_TODOS_COL_IMPORTANCE + " TEXT NOT NULL" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    // changes to dataBase as needed
    }


    // design pattern "contract"  -  for dataBase is to put all the constants in 1 class:
    // DB name/ version, chart names & so on...
    public static class TodosContract{

        // data base name:
        public static final String DBName = "Todos";

        // data base versions:
        public static final int DBVersion = 1 ;

        // we can insrt every chart in the data base on a diff class in order to make all organized
        public static final String TBL_TODOS = "Todos";
        public static final String TBL_TODOS_COL_MISSION = "mission";
        public static final String TBL_TODOS_COL_ID = "id";
        public static final String TBL_TODOS_COL_IMPORTANCE = "importance";





    }
}
