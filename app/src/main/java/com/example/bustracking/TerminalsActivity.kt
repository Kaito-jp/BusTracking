package com.example.bustracking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TerminalsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terminals)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        findViewById<View>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.navTrack).setOnClickListener {
            startActivity(Intent(this, TrackActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.navProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }

        findViewById<View>(R.id.btnViewOnMap).setOnClickListener {
            startActivity(Intent(this, TrackActivity::class.java))
        }

        findViewById<View>(R.id.chipNearMe).setOnClickListener {
            Toast.makeText(this, "Filtering terminals near you", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.chipFavorites).setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.cardPITX).setOnClickListener {
            val intent = Intent(this, TerminalInfoActivity::class.java)
            intent.putExtra("TERMINAL_NAME", getString(R.string.pitx_name))
            intent.putExtra("TERMINAL_ADDRESS", getString(R.string.pitx_addr))
            startActivity(intent)
        }

        findViewById<View>(R.id.cardCubao).setOnClickListener {
            val intent = Intent(this, TerminalInfoActivity::class.java)
            intent.putExtra("TERMINAL_NAME", getString(R.string.cubao_name))
            intent.putExtra("TERMINAL_ADDRESS", getString(R.string.cubao_addr))
            startActivity(intent)
        }

        findViewById<View>(R.id.btnGetInfoPITX).setOnClickListener {
            val intent = Intent(this, TerminalInfoActivity::class.java)
            intent.putExtra("TERMINAL_NAME", getString(R.string.pitx_name))
            intent.putExtra("TERMINAL_ADDRESS", getString(R.string.pitx_addr))
            startActivity(intent)
        }

        findViewById<View>(R.id.btnGetInfoCubao).setOnClickListener {
            val intent = Intent(this, TerminalInfoActivity::class.java)
            intent.putExtra("TERMINAL_NAME", getString(R.string.cubao_name))
            intent.putExtra("TERMINAL_ADDRESS", getString(R.string.cubao_addr))
            startActivity(intent)
        }

        findViewById<View>(R.id.btnFullSchedulePITX).setOnClickListener {
            val intent = Intent(this, FullScheduleActivity::class.java)
            intent.putExtra("TERMINAL_NAME", getString(R.string.pitx_name))
            startActivity(intent)
        }

        findViewById<View>(R.id.btnFullScheduleCubao).setOnClickListener {
            val intent = Intent(this, FullScheduleActivity::class.java)
            intent.putExtra("TERMINAL_NAME", getString(R.string.cubao_name))
            startActivity(intent)
        }
    }
}