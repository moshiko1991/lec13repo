package tomerbu.edu.lec13.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import tomerbu.edu.lec13.models.KStudent;

//CRUD => CREATE READ UPDATE DELETE
public class StudentDAO {
    //properties:
    private SQLiteDatabase db;

    //private ctor
    private StudentDAO(SQLiteDatabase db) {
        this.db = db;
    }


    //allow only one instance (singleton)
    private static StudentDAO instance = null;

    public synchronized static StudentDAO getInstance(Context c) {
        //singleton:
        if (instance == null) {
            StudentsDB sdb = new StudentsDB(c);
            SQLiteDatabase db = sdb.getWritableDatabase();
            instance = new StudentDAO(db);
        }
        return instance;
    }

    public int addStudent(KStudent s) {
        ContentValues cv = new ContentValues();
        cv.put("name", s.getName());
        cv.put("email", s.getEmail());

        int id = (int) db.insert(StudentsDB.TABLE_NAME, null, cv);
        s.setId(id);
        return id;
    }

    //@return the number of rows affected
    public boolean updateStudent(KStudent s) {
        ContentValues cv = from(s);
        return db.update(StudentsDB.TABLE_NAME, cv,
                "_id = ?", new String[]{s.getId() + ""}) > 0;
    }


    public boolean deleteStudent(KStudent s) {
        return db.delete(StudentsDB.TABLE_NAME,
                "_id = ?",
                new String[]{"" + s.getId()}
        ) > 0;
    }

    //helper method:
    private ContentValues from(KStudent s) {
        ContentValues cv = new ContentValues();
        cv.put("name", s.getName());
        cv.put("email", s.getEmail());
        return cv;
    }

    //READ:
    public List<KStudent> getStudents() {
        //new list
        List<KStudent> students = new ArrayList<>();

        //which columns do we want?
        String[] projection = {"_id", "name", "email"};
      /*  String selection = "name LIKE ? AND email LIKE ?";
        String[] selectionArgs = {"m%", "%@%"};*/

        String selection = "_id > ?";
        String[] selectionArgs = {"10; DROP TABLE students"};

        //query -> fills the list
        Cursor cursor = db.query(StudentsDB.TABLE_NAME, projection,
                null, null, null, null,
                "name ASC");

        if (!cursor.moveToFirst()) {
            return students;
        }

        do {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);

            students.add(new KStudent(name, email, id));
        } while (cursor.moveToNext());

        cursor.close();

        //return list
        return students;
    }


}
