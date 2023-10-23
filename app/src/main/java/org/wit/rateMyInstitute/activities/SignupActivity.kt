package org.wit.rateMyInstitute.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.wit.rateMyInstitute.R
import org.wit.rateMyInstitute.main.MainApp
import org.wit.rateMyInstitute.models.UserModel

class SignupActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    private lateinit var fNameEditText: EditText
    private lateinit var lNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    lateinit var app: MainApp

    var newUser = UserModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fNameEditText = findViewById(R.id.signupFirstNameEditText)
        lNameEditText = findViewById(R.id.signupLastNameEditText)
        emailEditText = findViewById(R.id.signupEmailEditText)
        passwordEditText = findViewById(R.id.signupPasswordEditText)
        signupButton = findViewById(R.id.signupButton)

        app = application as MainApp

        signupButton.setOnClickListener {
            val fName = fNameEditText.text.toString()
            val lName = lNameEditText.text.toString()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (fName.isNotEmpty() && lName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show()
                            val user: FirebaseUser? = auth.currentUser
                            if (user != null) {
                                newUser.uid = user.uid
                            }
                            newUser.firstName = fName
                            newUser.lastName = lName
                            newUser.email = email
                            newUser.password = password
                            app.users.create(newUser.copy())
                            val intent = Intent(this, RatingListActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Handle empty email or password fields
                Toast.makeText(this, "Complete all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
