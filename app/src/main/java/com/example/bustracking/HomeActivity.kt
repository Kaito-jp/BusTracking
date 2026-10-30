package com.example.bustracking

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var db: FirebaseFirestore
    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize Firestore
        db = FirebaseFirestore.getInstance()
        
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        mapView = findViewById(R.id.homeMapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        
        findViewById<android.view.View>(R.id.navTrack).setOnClickListener {
            val intent = Intent(this, TrackActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<android.view.View>(R.id.btnTrackBus).setOnClickListener {
            val intent = Intent(this, TrackActivity::class.java)
            startActivity(intent)
        }

        findViewById<android.view.View>(R.id.btnViewFullMap).setOnClickListener {
            val intent = Intent(this, TrackActivity::class.java)
            startActivity(intent)
        }

        findViewById<android.view.View>(R.id.btnTerminals).setOnClickListener {
            val intent = Intent(this, TerminalsActivity::class.java)
            startActivity(intent)
        }

        findViewById<android.view.View>(R.id.btnFavorites).setOnClickListener {
            val scrollView = findViewById<androidx.core.widget.NestedScrollView>(R.id.homeScrollView)
            val favoriteSection = findViewById<android.view.View>(R.id.favoriteRoutesSection)
            scrollView.smoothScrollTo(0, favoriteSection.top)
            android.widget.Toast.makeText(this, "Showing your favorite routes", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<android.view.View>(R.id.btnSeeAllFavorites).setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }

        val openBusDetails = android.view.View.OnClickListener {
            val intent = Intent(this, BusDetailsActivity::class.java)
            startActivity(intent)
        }
        findViewById<android.view.View>(R.id.cardFavoriteRoute1).setOnClickListener(openBusDetails)
        findViewById<android.view.View>(R.id.cardFavoriteRoute2).setOnClickListener(openBusDetails)
        findViewById<android.view.View>(R.id.cardFavoriteRoute3).setOnClickListener(openBusDetails)

        findViewById<android.view.View>(R.id.navTerminals).setOnClickListener {
            val intent = Intent(this, TerminalsActivity::class.java)
            startActivity(intent)
        }

        findViewById<android.view.View>(R.id.navProfile).setOnClickListener {
            val intentToProfile = Intent(this, ProfileActivity::class.java)
            intentToProfile.putExtra("IS_GUEST", intent.getBooleanExtra("IS_GUEST", false))
            startActivity(intentToProfile)
        }

        findViewById<android.view.View>(R.id.btnNotifications).setOnClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }

        findViewById<android.view.View>(R.id.btnProfileAvatar).setOnClickListener {
            val intentToProfile = Intent(this, ProfileActivity::class.java)
            intentToProfile.putExtra("IS_GUEST", intent.getBooleanExtra("IS_GUEST", false))
            startActivity(intentToProfile)
        }

        findViewById<android.view.View>(R.id.btnFilter).setOnClickListener {
            android.widget.Toast.makeText(this, "Filter options coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAction).setOnClickListener {
            android.widget.Toast.makeText(this, "Quick report feature coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val pagadian = LatLng(7.8256, 123.4393)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(pagadian, 13f))
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}