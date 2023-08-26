package com.bkm.mygooglemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.bkm.mygooglemap.databinding.ActivityMapsBinding
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback
import com.google.android.gms.maps.StreetViewPanorama
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.StreetViewPanoramaCamera

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        val streetViewPanoramaFragment =
//            supportFragmentManager
//                .findFragmentById(R.id.map) as SupportStreetViewPanoramaFragment
//        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this)
    }


    var marker: Marker? = null
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//
        // Add a marker in Sydney and move the camera
        val markhamat = LatLng(40.377947681654376, 70.45508546965759)

        marker = mMap.addMarker(MarkerOptions().position(markhamat))
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        mMap.setOnMapClickListener {
            marker?.position = it
            // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
            val cameraPosition = CameraPosition.Builder()
                .target(it) // Sets the center of the map to Mountain View
                .zoom(18f)            // Sets the zoom
//                .bearing(90f)         // Sets the orientation of the camera to east
                .tilt(75f)            // Sets the tilt of the camera to 30 degrees
                .build()              // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

        val cameraPosition = CameraPosition.Builder()
            .target(markhamat) // Sets the center of the map to Mountain View
            .zoom(18f)            // Sets the zoom
//                .bearing(90f)         // Sets the orientation of the camera to east
            .tilt(75f)            // Sets the tilt of the camera to 30 degrees
            .build()              // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }
    /*
    override fun onStreetViewPanoramaReady(p0: StreetViewPanorama) {
        val duration: Long = 1000
        val zoomBy = 0.5f
        val sanFrancisco = LatLng(37.754130, -122.447129)
        p0.setPosition(sanFrancisco)
        val camera = StreetViewPanoramaCamera.Builder()
            .zoom(p0.panoramaCamera.zoom + zoomBy)
            .tilt(p0.panoramaCamera.tilt)
            .bearing(p0.panoramaCamera.bearing)
            .build()
        p0.animateTo(camera, duration)
    }

     */
}