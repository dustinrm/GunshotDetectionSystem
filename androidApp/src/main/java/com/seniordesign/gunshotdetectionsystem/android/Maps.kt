package com.seniordesign.gunshotdetectionsystem.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.seniordesign.gunshotdetectionsystem.ConnectionDriver

class Maps : AppCompatActivity(), OnMapReadyCallback {
    private var locationPermissionGranted = false
    private var mapObject: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_activity)
        isLocationEnabled(this)
        getLocationPermission(this)
        if (locationPermissionGranted) {

            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(this)
        }
    }

    fun isLocationEnabled(context: Context): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(
                context,
                "Must have GPS on for this app functionality.",
                Toast.LENGTH_LONG
            )
            return false;
        }
        return true;
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getLocationPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            )
            { permissions ->
                when {
                    permissions.getOrDefault(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        false
                    ) -> {
                        locationPermissionGranted = true
                    }
                    permissions.getOrDefault(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        false
                    ) -> {
                        locationPermissionGranted = true
                    }
                    else -> {
                        locationPermissionGranted = false
                    }

                }

            }

            if (!locationPermissionGranted) {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
        locationPermissionGranted = true

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mapObject = googleMap
        Thread {
            while (true) {
                val connectionDriver = ConnectionDriver()
                val detections = connectionDriver.SQLQuery()
                val detectionList = findViewById<LinearLayout>(R.id.mapDetectionList)
                if (!detections.isEmpty()) {
                    runOnUiThread {
                        detectionList.removeAllViews()
                        detections.forEach {
                            createMarker(it.lat, it.lon, it.date.toString())
                            val detectionView =
                                layoutInflater.inflate(R.layout.detection_list_item, null)
                            detectionView.findViewById<TextView>(R.id.detection_gps)
                                .setText(it.lat.toString() + " , " + it.lon)
                            detectionView.findViewById<TextView>(R.id.detection_time)
                                .setText(it.time.toString())
                            detectionView.findViewById<TextView>(R.id.detection_date)
                                .setText(it.date.toString())
                            detectionView.findViewById<TextView>(R.id.detection_status)
                                .setText(it.status.toString())
                            val position = LatLng(it.lat, it.lon)
                            detectionView.setOnClickListener {
                                mapObject?.moveCamera(CameraUpdateFactory.newLatLng(position))
                                mapObject?.moveCamera(CameraUpdateFactory.zoomTo(10.0f))
                            }
                            detectionList.addView(detectionView)
                        }
                    }
                }
                Thread.sleep(2000)
            }
        }.start()
    }

    fun createMarker(lat: Double, lon: Double, title: String) {
        val position = LatLng(lat, lon)
        mapObject?.addMarker(
            MarkerOptions().position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

    }


}