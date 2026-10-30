package com.example.bustracking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class TrackActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_track)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        findViewById<View>(R.id.navHome).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View>(R.id.busDetailCard).setOnClickListener {
            val intent = Intent(this, BusDetailsActivity::class.java)
            startActivity(intent)
        }
        
        findViewById<View>(R.id.navTerminals).setOnClickListener {
            val intent = Intent(this, TerminalsActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View>(R.id.navProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<View>(R.id.btnNotifications).setOnClickListener {
            val intent = Intent(this, NotificationsActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.btnMapLayers).setOnClickListener {
            Toast.makeText(this, "Map layers coming soon", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnRefreshMap).setOnClickListener {
            Toast.makeText(this, "Refreshing bus locations...", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnRecenter).setOnClickListener {
            val pagadian = LatLng(7.8256, 123.4393)
            googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(pagadian, 15f))
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Focus on Pagadian City as default
        val pagadian = LatLng(7.8256, 123.4393)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(pagadian, 14f))
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