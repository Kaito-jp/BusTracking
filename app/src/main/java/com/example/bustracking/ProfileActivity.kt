package com.example.bustracking

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val isGuest = intent.getBooleanExtra("IS_GUEST", false)
        if (isGuest) {
            findViewById<android.widget.TextView>(R.id.userName).text = "Guest"
            findViewById<android.widget.TextView>(R.id.userEmail).text = "Sign in for more features"
            findViewById<android.widget.TextView>(R.id.verifiedBadge).visibility = android.view.View.GONE
            findViewById<android.widget.TextView>(R.id.editProfileLink).visibility = android.view.View.GONE
        } else {
            val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
            if (user != null) {
                findViewById<android.widget.TextView>(R.id.userName).text = user.displayName ?: "Bus User"
                findViewById<android.widget.TextView>(R.id.userEmail).text = user.email
            }
        }

        findViewById<View>(R.id.btnDeleteData).setOnClickListener {
            if (isGuest) {
                android.widget.Toast.makeText(this, "Guest accounts have no stored data.", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Delete Account & All Data")
                .setMessage("Are you sure you want to permanently delete your account and all associated data? This action cannot be undone.")
                .setPositiveButton("Delete") { _, _ ->
                    val user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
                    if (user != null) {
                        val database = com.google.firebase.database.FirebaseDatabase.getInstance("https://pilibus-99b16-default-rtdb.firebaseio.com/")
                        val deletionLog = mapOf(
                            "uid" to user.uid,
                            "email" to (user.email ?: "unknown"),
                            "deletedAt" to System.currentTimeMillis()
                        )
                        // 1. Store deletion audit log in Firebase Database
                        database.getReference("deletion_logs").child(user.uid).setValue(deletionLog).addOnCompleteListener { _ ->
                            // 2. Remove user profile data
                            database.getReference("users").child(user.uid).removeValue().addOnCompleteListener { _ ->
                                // 3. Delete Firebase Auth account
                                user.delete().addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        android.widget.Toast.makeText(this, "Account and all data deleted successfully.", android.widget.Toast.LENGTH_LONG).show()
                                        val intent = Intent(this, MainActivity::class.java)
                                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        android.widget.Toast.makeText(this, "Failed to delete account: ${task.exception?.message}. Please sign in again and retry.", android.widget.Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        findViewById<View>(R.id.btnSignOut).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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

        findViewById<View>(R.id.btnArrivalRadius).setOnClickListener {
            android.widget.Toast.makeText(this, "Adjusting arrival radius...", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnLanguage).setOnClickListener {
            android.widget.Toast.makeText(this, "Language settings coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnHelpCenter).setOnClickListener {
            android.widget.Toast.makeText(this, "Opening Help Center...", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.editProfileLink).setOnClickListener {
            android.widget.Toast.makeText(this, "Edit Profile coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnReport).setOnClickListener {
            android.widget.Toast.makeText(this, "Opening report form...", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnDistanceUnits).setOnClickListener {
            android.widget.Toast.makeText(this, "Distance units settings coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnLocationPrivacy).setOnClickListener {
            android.widget.Toast.makeText(this, "Privacy settings coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnLinkedAccounts).setOnClickListener {
            android.widget.Toast.makeText(this, "Linked accounts coming soon", android.widget.Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnInviteFriends).setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Hey! Download PiliBus to track buses in real-time across the Philippines.")
            }
            startActivity(Intent.createChooser(shareIntent, "Invite Friends via"))
        }
    }
}