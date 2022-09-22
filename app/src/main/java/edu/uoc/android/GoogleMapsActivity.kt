package edu.uoc.android

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.location.LocationServices


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import edu.uoc.android.databinding.ActivityGoogleMapsBinding
import edu.uoc.android.databinding.ActivityMuseumsBinding
import edu.uoc.android.models.Element
import edu.uoc.android.models.Museums
import edu.uoc.android.rest.RetrofitFactory
import retrofit2.Response
import java.util.jar.Manifest

private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMapsBinding
    private val userLocation = Location("")
    private lateinit var myLocationButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myLocationButton = findViewById(R.id.myLocationButton)

        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getUserLocation()
            } else {
                val permissionsArray = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                requestPermissions(permissionsArray, LOCATION_PERMISSION_REQUEST_CODE)
            }
        } else {
            getUserLocation()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getUserLocation()
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                showLocationPermissionRationaleDialog()
            } else {
                finish()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showLocationPermissionRationaleDialog() {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Need permission location")
            .setMessage("Please accept permission of location")
            .setPositiveButton(android.R.string.ok) {_, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            }.setNegativeButton(android.R.string.no) {_, _ ->
                finish()
            }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val fusedLocationCLient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationCLient.lastLocation.addOnSuccessListener {
            location: Location? ->
            if (location != null) {
                userLocation.latitude = location.latitude
                userLocation.longitude = location.longitude
                setupMap()
            }
        }
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
        val mMap = googleMap

        val userMarker = LatLng(userLocation.latitude, userLocation.longitude)
        mMap.addMarker(MarkerOptions()
            .position(userMarker)
            .title("My location"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker, 13.0f))

        val call = RetrofitFactory.museumAPI.museums("1","25")
        call?.enqueue(object : retrofit2.Callback<Museums?> {
            override fun onResponse(call: retrofit2.Call<Museums?>, response: Response<Museums?>) {
                if (response.code() == 200) {
                    val museums = response.body()!!
                    //Add a marker for each museum from the list,
                    // then move the camera to the last one
                    for (elem in museums.elements) {
                        val lat = elem.localitzacio.dropLast(elem.localitzacio.indexOf(','))
                        val lng = elem.localitzacio.drop(elem.localitzacio.indexOf(',') + 1)
                        val museumLoc = LatLng(lat.toDouble(), lng.toDouble())
                        mMap.addMarker(MarkerOptions()
                            .position(museumLoc).title(elem.adrecaNom)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_library)))
                    }
                }
            }
            override fun onFailure(call: retrofit2.Call<Museums?>, t: Throwable) {
                Log.d(TAG, "Retrofit2.Call Museums: Failled to enqueue")
            }
        })

        myLocationButton.setOnClickListener {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker, 13.0f))
        }
    }

    private fun setupMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

}