# Earthquakes IIM

Useful links:

- [Android Developers](https://developer.android.com/index.html)
- [Earthquake API](https://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php)
- [Earthquake API (list of significant earthquakes)](https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson)
- [Android Recycler View](https://developer.android.com/guide/topics/ui/layout/recyclerview.html)
- [Android List View](https://developer.android.com/guide/topics/ui/layout/listview.html)
- [Android List View Click](https://stackoverflow.com/questions/2468100/how-to-handle-listview-click-in-android)
- [Android Maps Utils](https://developers.google.com/maps/documentation/android-api/utility/)

## TODO

The Android Project limit due date is Sunday, 1st of April at 11:59pm.  
The project need to be hosted on [GitHub](https://github.com) and an email containing the link have to be send to the address thibaud[dot]bardin+iim[at]gmail.com


Ps: The project have to be developed in Kotlin (no Jav a file inside the app module).

### MainActivity

Show a *Spinner* element with choices for earthquakes magnitudes:
- Significant Earthquakes
- M4.5+ Earthquakes
- M2.5+ Earthquakes
- M1.0+ Earthquakes
- All Earthquakes

When we click on the **Maps** button we have to change the activity to **MapsActivity** and display markers for earthquakes (of selected magnitude).

When we click on the **List** button we have to change the activity to **ListActivity** and display a list of earthquakes (of selected magnitude).

### ListActivity

List earthquakes depending of the value received in "Extra message".

When an item is clicked (not long pressed), we have to change the activity to **MapsActivity** and display only one marker (latitude, longitude and title of the clicked row).

### MapsActivity

Show earthquakes on map depending of the value received in "Extra message".
# renduandroid
