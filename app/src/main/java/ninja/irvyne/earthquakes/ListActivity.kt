package ninja.irvyne.earthquakes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ArrayAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import ninja.irvyne.earthquakes.api.EarthquakeService
import ninja.irvyne.earthquakes.api.model.EarthquakeData
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
    viewManager = LinearLayoutManager(this)
    viewAdapter = MyAdapter(myDataset)

    recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        setHasFixedSize(true)

        // use a linear layout manager
        layoutManager = viewManager

        // specify an viewAdapter (see also next example)
        adapter = viewAdapter

    }
}
 */
class ListActivity : AppCompatActivity() {

    private lateinit var mService: EarthquakeService
    private var mMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        mMessage = intent.getStringExtra(MainActivity.EXTRA_CHOICE)

        // Fetch Api
        val retrofit = Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mService = retrofit.create<EarthquakeService>(EarthquakeService::class.java)

        val request = when (mMessage) {
            "All" -> mService.listEarthquakes()
            "M1.0+" -> mService.listM1Earthquakes()
            "M2.5+" -> mService.ListM25Earthquakes()
            "M4.5" -> mService.ListM4Earthquakes()
            else -> mService.listSignificantEarthquakes()
        }

        request.enqueue(object : Callback<EarthquakeData> {
            override fun onFailure(call: Call<EarthquakeData>?, t: Throwable?) {
                Log.e(TAG, "An error occurred with listSignificantEarthquakes(), error: $t")
                longToast("Oups, an error occurred 🤟")
            }

            override fun onResponse(call: Call<EarthquakeData>?, response: Response<EarthquakeData>?) {
                Log.d(TAG, "Success, ${response?.body()}")
                longToast("Success 🍾")

                response?.body()?.let {
                    it.features?.forEach { feature ->
                        feature.features?.properties?.let {
                            mMap.addMarker(MarkerOptions().position(LatLng(it[1], it[0])).title(feature.properties?.title))
                        }
                    }
                }
            }

            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }
        })
    }

    companion object {
        private const val TAG = "MapsActivity"
    }
}


