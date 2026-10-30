package com.example.bustracking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class VerificationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var storedAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verification)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://pilibus-99b16-default-rtdb.firebaseio.com/")

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
            return
        }

        val etSecurityAnswer = findViewById<EditText>(R.id.etSecurityAnswer)
        val btnVerify = findViewById<Button>(R.id.btnVerify)
        val btnSignOut = findViewById<TextView>(R.id.btnSignOut)
        val tvSecurityQuestion = findViewById<TextView>(R.id.tvSecurityQuestion)

        tvSecurityQuestion.text = "Security Question: What is your primary transit terminal or departure hub?"

        val userId = currentUser.uid
        val answerRef = database.getReference("users").child(userId).child("securityAnswer")

        // Fetch stored security answer if any
        answerRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                storedAnswer = snapshot.getValue(String::class.java)
                if (storedAnswer != null) {
                    etSecurityAnswer.hint = "Enter your registered secret answer"
                } else {
                    etSecurityAnswer.hint = "Set your secret answer (first time setup)"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Ignore or handle
            }
        })

        btnVerify.setOnClickListener {
            val inputAnswer = etSecurityAnswer.text.toString().trim()
            if (inputAnswer.isEmpty()) {
                Toast.makeText(this, "Please enter your security answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (storedAnswer == null) {
                // First time setup - save answer
                answerRef.setValue(inputAnswer).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Security answer registered successfully!", Toast.LENGTH_SHORT).show()
                        proceedToHome()
                    } else {
                        Toast.makeText(this, "Failed to save security answer: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Verify answer
                if (inputAnswer.equals(storedAnswer, ignoreCase = true)) {
                    Toast.makeText(this, "Identity verified successfully!", Toast.LENGTH_SHORT).show()
                    proceedToHome()
                } else {
                    Toast.makeText(this, "Incorrect security answer. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnSignOut.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun proceedToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
