package com.example.bustracking

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TerminalInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_terminal_info)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val name = intent.getStringExtra("TERMINAL_NAME") ?: "Terminal Info"
        val address = intent.getStringExtra("TERMINAL_ADDRESS") ?: "Address details"

        findViewById<TextView>(R.id.terminalName).text = name
        findViewById<TextView>(R.id.terminalAddress).text = address

        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<View>(R.id.btnViewRoutes).setOnClickListener {
            finish() // For now just go back or could go to Track
        }
    }
}