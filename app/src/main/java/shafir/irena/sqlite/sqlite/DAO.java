package shafir.irena.sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import shafir.irena.sqlite.models.Todo;

/**
 * Created by irena on 21/07/2017.
 */

// DATA ACCESS OBJECT
    // access DB helper

    // singleton == no constructor but we have a method of getInstance (it's like a factory method)
    // not thread safe since multiple threads can enter the get instance method.
    // since we want only 1 instance we will add synchronized to the method
    // in order not to slow down the process we use s design pattern called "double check singleton"
    // it checks if the instance is null. if so, threads will get in a synchronized way & only than create new

public class DAO  {

    private static DAO sharedInstance ;
    private SQLiteDatabase db;

    // no one can create an instance
    private DAO (){/* No Instance Please*/};
    private DAO (Context context){
        db = new TodosDBHelper(context).getWritableDatabase();
    };


    // factory method
    public static DAO getInstance(Context context){
        // if an instance have never been created, let's create one & save it

        // design pattern = double checked singleton
        if (DAO.sharedInstance == null) {
            synchronized (DAO.class) {
                if (DAO.sharedInstance == null) {
                    DAO.sharedInstance = new DAO(context);
                }
            }
        }
        return DAO.sharedInstance;
    }


    // add todos
    public void addTodo(String mission, String importance){
//        TodosDBHelper helper = new TodosDBHelper(context);
//        SQLiteDatabase db = helper.getWritableDatabase();

//        db.execSQL("INSERT INTO Todos(mission, importance) " +
//                "VALUES('" + mission + "','" + importance + "');");

        // this option allows the user to insert values of SQL & by that destroy the DB
        // that's why we'll use prepared statements instead of hard coding of strings

        // Content value is like maps = key value pairs
        ContentValues values = new ContentValues();
        values.put(TodosDBHelper.TodosContract.TBL_TODOS_COL_MISSION, mission);
        values.put(TodosDBHelper.TodosContract.TBL_TODOS_COL_IMPORTANCE, importance);
        db.insert(TodosDBHelper.TodosContract.TBL_TODOS, null, values);
    }


    public List<Todo> getTodo(){
        ArrayList<Todo> todos = new ArrayList<>();

        String [] columns = new String[]{"mission, importance, id"};
        String where = "mission LIKE ? AND importance = ?";
        String[] selectionArgs = {"C%", "High"};

        // SELECT * FROM Todos
        // Query does SELECT for us
//        db.query(TodosDBHelper.TodosContract.TBL_TODOS,
//                columns /*same as * in SQL SELECT * */,
//                where,
//                selectionArgs,
//                null,  /*Did not study aggregation functions*/
//                null, /*Did not study aggregation functions*/
//                "importance"
//                );

        Cursor cursor = db.query(TodosDBHelper.TodosContract.TBL_TODOS,
                new String[]{"id", "mission", "importance"}, null, null, null, null, "importance");
        if (cursor != null && cursor.moveToFirst()){
            // another option to set the column index is with this:
//            int missionIndex = cursor.getColumnIndex("mission");
            do {
//                String mission = cursor.getString(cursor.getColumnIndex("mission"));
                int id = cursor.getInt(0);
                String mission = cursor.getString(1);
                String importance = cursor.getString(2);

                Todo todo = new Todo(id, mission, importance);
            }while (cursor.moveToNext());
        }

        return todos;
    }



}
