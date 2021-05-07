package com.example.geofiremutilocation

import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
    private lateinit var geofencingClient: GeofencingClient
    private final var FINE_LOCATION_ACCESS_REQUEST_CODE :Int = 10001;
    private var GEOEENCE_RADIUS = 200.00;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        geofencingClient = LocationServices.getGeofencingClient(this)
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

        // Add a marker in eiffel and move the camera
        val eiffel = LatLng(48.8589, 2.29365)
        mMap.addMarker(MarkerOptions().position(eiffel).title("Marker in eiffel"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eiffel, 16F))

        enableUserLocation()

        onMapLongClick();
    }

    private fun enableUserLocation(){
       if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
         mMap.isMyLocationEnabled = true
       }
        else{
           // ask for permission
           if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION)){

               // we need to show user a dialog for displaying why the permission is needed and then ask for the permission...
               ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),FINE_LOCATION_ACCESS_REQUEST_CODE)
           }
           else{
               ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),FINE_LOCATION_ACCESS_REQUEST_CODE)
           }
       }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                // we have the permission
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                mMap.isMyLocationEnabled = true

            }
            else{
                // we do not have the permission
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMarker(latLng: LatLng){
       val markerOptions = MarkerOptions().position(latLng);
       mMap.addMarker(markerOptions);
    }
    private fun addCircle(latLng: LatLng, radius: Double){
       val circleOptions = CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255,255,0,0))
        circleOptions.fillColor(Color.argb(64,255,0,0))
        circleOptions.strokeWidth(4f);

        mMap.addCircle(circleOptions);

    }

     private fun onMapLongClick() {
         Toast.makeText(this, "map", Toast.LENGTH_SHORT).show()
//         mMap.setOnMapLongClickListener{ latLng ->
//             addMarker(latLng);
//             addCircle(latLng,GEOEENCE_RADIUS)
//         }

    }
}


