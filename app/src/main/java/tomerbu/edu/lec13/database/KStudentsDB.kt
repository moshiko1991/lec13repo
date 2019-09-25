package tomerbu.edu.lec13.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class KStudentsDB(context: Context) : SQLiteOpenHelper(context,
    DB_NAME, null,
    DB_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        //nulls are handled with an operator!
        //if (db != null)
        db?.execSQL(CREATE_TABLE_STUDENTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    //static in java -> companion object
    companion object {
        val DB_VERSION = 1
        val DB_NAME = "StudentsDB"
        val TABLE_NAME = "Students"

        //multi line strings:
        val CREATE_TABLE_STUDENTS = """
                                        CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT,
                                                              name TEXT NOT NULL, 
                                                              email TEXT 
                                                              )
                                    """
    }
}