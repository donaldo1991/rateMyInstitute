package org.wit.rateMyInstitute.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.wit.rateMyInstitute.models.RatingManager
import org.wit.rateMyInstitute.models.RatingModel
import timber.log.Timber
import java.lang.Exception

class ReportViewModel : ViewModel() {

    private val ratingsList =
        MutableLiveData<List<RatingModel>>()

    val observableRatingsList: LiveData<List<RatingModel>>
        get() = ratingsList

    var liveFirebaseUser = MutableLiveData<FirebaseUser>()

    init { load() }

    fun load() {
        try {
            FirebaseDBManager.findAll(liveFirebaseUser.value?.uid!!,ratingsList)
            //RatingManager.findAll(liveFirebaseUser.value?.email!!, ratingsList)
            Timber.i("Report Load Success : ${ratingsList.value}")
        }
        catch (e: Exception) {
            Timber.i("Report Load Error : ${e.toString()}")
        }
    }

    fun delete(userid: String, id: String) {
        try {
            //RatingManager.delete(email,id)
            FirebaseDBManager.delete(userid,id)
            Timber.i("Report Delete Success")
        }
        catch (e: Exception) {
            Timber.i("Report Delete Error : $e.message")
        }
    }
}

