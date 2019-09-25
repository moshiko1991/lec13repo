package tomerbu.edu.lec13.controllers

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import tomerbu.edu.lec13.R
import tomerbu.edu.lec13.database.StudentDAO
import tomerbu.edu.lec13.models.KStudent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        //TODO: Threads


        //INSERT:
        val dao = StudentDAO.getInstance(this)
        dao.addStudent(
            //new student:
            KStudent("Dave", "Dave@gmail.com", null)
        )

        //update:
        dao.updateStudent(KStudent("Sacha", "Sacha@gmail.com", 1))

        //GET:
        println(dao.students)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {


        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
