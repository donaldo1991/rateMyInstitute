package org.wit.rateMyInstitute.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.wit.rateMyInstitute.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Get references to views
        val welcomeText = findViewById<TextView>(R.id.welcome_text)
        val enterButton = findViewById<Button>(R.id.enter_button)

        // Set a click listener for the button
        enterButton.setOnClickListener(View.OnClickListener {
            // Handle the button click, e.g., navigate to another activity
            val intent = Intent(this, RatingListActivity::class.java)
            startActivity(intent)
        })
    }
}