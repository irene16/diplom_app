//package com.example.diplom
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.core.app.ActivityCompat
//import androidx.lifecycle.LifecycleOwner
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.google.maps.android.maps.compose.GoogleMap
//import com.google.maps.android.maps.compose.MapView
//import com.google.maps.android.maps.compose.rememberMapViewWithLifecycle
//import com.google.android.gms.location.*
//import kotlinx.coroutines.launch
//
//class MapActivity : AppCompatActivity() {
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Initialize FusedLocationProviderClient
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        setContent {
//            MapContent()
//        }
//    }
//
//    @Composable
//    fun MapContent() {
//        val navController = rememberNavController()
//        val mapView = rememberMapViewWithLifecycle()
//
//        // Request location permissions
//        val requestPermissionLauncher =
//            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//                if (isGranted) {
//                    // Permission is granted, request location updates
//                    requestLocationUpdates()
//                }
//            }
//
//        // Check location permissions
//        LaunchedEffect(key1 = true) {
//            if (ActivityCompat.checkSelfPermission(
//                    LocalContext.current,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // Request location permissions if not granted
//                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//            } else {
//                // Permission is already granted, request location updates
//                requestLocationUpdates()
//            }
//        }
//
//        // Composable content
//        MapView(
//            modifier = Modifier.fillMaxSize(),
//            mapView = mapView
//        )
//
//        MapScreen(navController, mapView)
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun requestLocationUpdates() {
//        val locationRequest = LocationRequest.create().apply {
//            interval = 10000
//            fastestInterval = 5000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }
//
//        val locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult?) {
//                locationResult ?: return
//                for (location in locationResult.locations) {
//                    // Handle location updates here
//                    // You can use location.latitude and location.longitude to get the latitude and longitude
//                }
//            }
//        }
//
//        fusedLocationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            null /* Looper */
//        )
//    }
//}
//
//@Composable
//fun MapScreen(navController: NavController, mapView: MapView) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val context = LocalContext.current
//    val locationViewModel: LocationViewModel by context.viewModels()
//
//    DisposableEffect(lifecycleOwner) {
//        val mapViewLifecycle = mapView
//        val observer = object : LifecycleOwner {
//            override fun getLifecycle() = mapViewLifecycle.lifecycle
//        }
//
//        locationViewModel.initMap(context, mapView, observer)
//
//        onDispose {
//            locationViewModel.dispose()
//        }
//    }
//
//    locationViewModel.addMarker(55.751244, 37.618423, "Marker 1")
//    locationViewModel.addMarker(55.751244, 37.618423, "Marker 2")
//    locationViewModel.addMarker(55.751244, 37.618423, "Marker 3")
//
//    // Draw markers on the map
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        mapView = mapView,
//        onMapLoaded = {
//            locationViewModel.drawMarkers()
//        }
//    )
//}
//
//@SuppressLint("MissingPermission")
//class LocationViewModel : ViewModel() {
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private var markers = mutableListOf<Pair<Double, Double>>()
//    private var map: MapView? = null
//    private var observer: LifecycleOwner? = null
//
//    fun initMap(context: Context, mapView: MapView, observer: LifecycleOwner) {
//        this.map = mapView
//        this.observer = observer
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//    }
//
//    fun addMarker(latitude: Double, longitude: Double, title: String) {
//        markers.add(Pair(latitude, longitude))
//    }
//
//    fun drawMarkers() {
//        map?.let { mapView ->
//            val lifecycle = observer?.lifecycle
//            lifecycle?.addObserver { _, event ->
//                if (event == Lifecycle.Event.ON_RESUME) {
//                    mapView.getMapAsync { googleMap ->
//                        for (marker in markers) {
//                            googleMap.addMarker {
//                                position(LatLng(marker.first, marker.second))
//                                title("Marker")
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    fun dispose() {
//        observer?.lifecycle?.removeObservers(this)
//    }
//}



//@Composable
//fun MapScreen(navController: NavController) {
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MapScreenPreview() {
//    MapScreen()
//}