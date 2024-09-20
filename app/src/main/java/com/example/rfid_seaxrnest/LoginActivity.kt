package com.example.rfid_seaxrnest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val email = findViewById<EditText>(R.id.et_email)
        val password = findViewById<EditText>(R.id.et_pass)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            val emailInput = email.text.toString()
            val passInput = password.text.toString()

            auth.signInWithEmailAndPassword(emailInput, passInput)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("LoginActivity", "Email is: $emailInput")
                        Log.d("LoginActivity", "Password is: $passInput")

                        val user = auth.currentUser
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}