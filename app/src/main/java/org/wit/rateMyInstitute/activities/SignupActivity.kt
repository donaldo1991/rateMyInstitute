package org.wit.rateMyInstitute.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.wit.rateMyInstitute.R

class SignupActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        emailEditText = findViewById(R.id.signupEmailEditText)
        passwordEditText = findViewById(R.id.signupPasswordEditText)
        signupButton = findViewById(R.id.signupButton)

        signupButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Create a new user with email and password
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registration success
                            val user: FirebaseUser? = auth.currentUser
                            // You can update UI, navigate to the home screen, etc.
                        } else {
                            // Registration failed, display an error message
                            // For example, you can check the error code and show a relevant message
                            // val errorCode = (task.exception as FirebaseAuthException).errorCode
                            // Handle error codes as needed
                        }
                    }
            } else {
                // Handle empty email or password fields
            }
        }
    }
}
