package org.wit.rateMyInstitute.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.wit.rateMyInstitute.R

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val passwordText = findViewById<TextView>(R.id.loginPasswordEditText)
        val emailText = findViewById<TextView>(R.id.loginEmailEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Authentication successful
                        val user = auth.currentUser
                        Toast.makeText(this, "Welcome, ${user?.email}", Toast.LENGTH_SHORT).show()
                        // Navigate to the next activity
                        val intent = Intent(this, RatingListActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Authentication failed
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
