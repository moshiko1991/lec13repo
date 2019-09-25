package tomerbu.edu.lec13.database;


//Class to initially create the data base - create the TABLES


//USED ONCE -> to create the tables.

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//used all the time -> to get the db object
public class StudentsDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "StudentsDb";
    public static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "Students";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name TEXT NOT NULL," +
            " email TEXT)";

    public StudentsDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION); //ResultSet Factory
    }

    //only tables are created here
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    //when we need to update the database structure
    //the database is regularly uploaded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //destroy tables
        db.execSQL("DROP TABLE Students");
        //create new tables
        onCreate(db);
    }
}
