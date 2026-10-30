package com.example.bustracking

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<View>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.navTrack).setOnClickListener {
            startActivity(Intent(this, TrackActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.navTerminals).setOnClickListener {
            startActivity(Intent(this, TerminalsActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.navProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
        
        findViewById<View>(R.id.btnNotifications).setOnClickListener {
            startActivity(Intent(this, NotificationsActivity::class.java))
        }

        val openBusDetails = View.OnClickListener {
            val intent = Intent(this, BusDetailsActivity::class.java)
            startActivity(intent)
        }
        findViewById<View>(R.id.cardFavoriteRoute1).setOnClickListener(openBusDetails)
        findViewById<View>(R.id.cardFavoriteRoute2).setOnClickListener(openBusDetails)
        findViewById<View>(R.id.cardFavoriteRoute3).setOnClickListener(openBusDetails)
    }
}