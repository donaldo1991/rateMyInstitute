package org.wit.rateMyInstitute.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.rateMyInstitute.R
import org.wit.rateMyInstitute.databinding.ActivityRatingBinding
import org.wit.rateMyInstitute.main.MainApp
import org.wit.rateMyInstitute.models.Location
import org.wit.rateMyInstitute.models.RatingModel
import org.wit.rateMyInstitute.showImagePicker
import timber.log.Timber.Forest.i

class RatingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding
    var rating = RatingModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp

        i("rating Activity started...")

        if (intent.hasExtra("rating_edit")) {
            edit = true
            rating = intent.extras?.getParcelable("rating_edit")!!
            binding.ratingTitle.setText(rating.title)
            binding.description.setText(rating.description)
            binding.btnAdd.setText(R.string.save_rating)
            Picasso.get()
                .load(rating.image)
                .into(binding.ratingImage)
            if (rating.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_rating_image)
            }

        }

        binding.btnAdd.setOnClickListener() {
            rating.title = binding.ratingTitle.text.toString()
            rating.description = binding.description.text.toString()
            if (rating.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_rating_title, Snackbar.LENGTH_LONG)
                        .show()
            } else {
                if (edit) {
                    app.ratings.update(rating.copy())
                } else {
                    app.ratings.create(rating.copy())
                }
            }
            i("add Button Pressed: $rating")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }

        binding.ratingLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }

        registerImagePickerCallback()
        registerMapCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_rating, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            rating.image = result.data!!.data!!
                            Picasso.get()
                                   .load(rating.image)
                                   .into(binding.ratingImage)
                            binding.chooseImage.setText(R.string.change_rating_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            location = result.data!!.extras?.getParcelable("location")!!
                            i("Location == $location")
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}