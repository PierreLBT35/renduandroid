package ninja.irvyne.earthquakes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ninja.irvyne.earthquakes.api.EarthquakeService
import ninja.irvyne.earthquakes.api.model.EarthquakeData
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mService: EarthquakeService
    private var mMessage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        mMessage = intent.getStringExtra(MainActivity.EXTRA_CHOICE)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Fetch Api
        val retrofit = Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mService = retrofit.create<EarthquakeService>(EarthquakeService::class.java)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val Paris = LatLng(48.866667, 2.333333)
        mMap.addMarker(MarkerOptions().position(Paris).title("Paris is the best city"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Paris))


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
                        feature.geometry?.coordinates?.let {
                            mMap.addMarker(MarkerOptions().position(LatLng(it[1], it[0])).title(feature.properties?.title))
                        }
                    }
                }
            }
        })
    }

    companion object {
        private const val TAG = "MapsActivity"
    }
}
