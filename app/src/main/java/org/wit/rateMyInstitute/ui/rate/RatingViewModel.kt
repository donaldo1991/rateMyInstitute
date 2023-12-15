package org.wit.rateMyInstitute.ui.rate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.rateMyInstitute.models.RatingManager
import org.wit.rateMyInstitute.models.RatingModel
import timber.log.Timber

class RatingViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addRating(rating: RatingModel) {
        status.value = try {
            Timber.i("Rating to be added : $rating")
            RatingManager.create(rating)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}