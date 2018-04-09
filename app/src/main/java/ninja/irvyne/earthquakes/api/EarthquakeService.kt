package ninja.irvyne.earthquakes.api

import ninja.irvyne.earthquakes.api.model.EarthquakeData
import retrofit2.Call
import retrofit2.http.GET


interface EarthquakeService {
    // ALL EARTHQUAKES DATA
    @GET("earthquakes/feed/v1.0/summary/all_month.geojson")
    fun ListEarthquakes(): Call<EarthquakeData>
    // M1.0 EARTHQUAKES DATA
    @GET("earthquakes/feed/v1.0/summary/1.0_month.geojson")
    fun ListM1Earthquakes(): Call<EarthquakeData>
    // M2.5 EARTHQUAKES DATA
    @GET("earthquakes/feed/v1.0/summary/2.5_month.geojson")
    fun ListM25Earthquakes(): Call<EarthquakeData>
    // M4.5 EARTHQUAKES DATA
    @GET("earthquakes/feed/v1.0/summary/4.5_month.geojson")
    fun listM4Earthquakes(): Call<EarthquakeData>
    // SIGNIFICANT EARTHQUAKES DATA
    @GET("earthquakes/feed/v1.0/summary/significant_month.geojson")
    fun listSignificantEarthquakes(): Call<EarthquakeData>

    fun listEarthquakes(): Call<EarthquakeData>
    fun ListM4Earthquakes(): Call<EarthquakeData>
    fun listM1Earthquakes(): Call<EarthquakeData>
}