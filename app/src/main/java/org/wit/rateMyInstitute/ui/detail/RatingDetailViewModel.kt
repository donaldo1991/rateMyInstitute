package org.wit.rateMyInstitute.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.rateMyInstitute.models.RatingManager
import org.wit.rateMyInstitute.models.RatingModel
import timber.log.Timber

class RatingDetailViewModel : ViewModel() {
    private val rating = MutableLiveData<RatingModel>()

    var observableRating: LiveData<RatingModel>
        get() = rating
        set(value) {rating.value = value.value}

    fun getRating(userid:String, id: String) {
        try {
            //RatingManager.findById(email, id, rating)
            FirebaseDBManager.findById(userid, id, rating)
            Timber.i("Detail getrating() Success : ${rating.value.toString()}")
        }
        catch (e: Exception) {
            Timber.i("Detail getrating() Error : $e.message")
        }
    }

    fun updateRating(userid:String, id: String,rating: RatingModel) {
        try {
            //RatingManager.update(email, id, rating)
            FirebaseDBManager.update(userid, id, rating)
            Timber.i("Detail update() Success : $rating")
        }
        catch (e: Exception) {
            Timber.i("Detail update() Error : $e.message")
        }
    }
}