package ninja.irvyne.earthquakes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(this, R.array.earthquakes_list, android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mainSpinner.adapter = adapter

        mainButtonMaps.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_CHOICE, mainSpinner.selectedItem.toString())
            }

            startActivity(intent)
        }
        mainButtonList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java).apply {

            }

            startActivity(intent)
        }

    }

    companion object {
        const val EXTRA_CHOICE = "choice"
    }
}
