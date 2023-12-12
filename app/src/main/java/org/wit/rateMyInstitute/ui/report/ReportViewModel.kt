package org.wit.rateMyInstitute.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.rateMyInstitute.models.RatingManager
import org.wit.rateMyInstitute.models.RatingModel

class ReportViewModel : ViewModel() {

    private val donationsList = MutableLiveData<List<RatingModel>>()

    val observableDonationsList: LiveData<List<RatingModel>>
        get() = donationsList

    init {
        load()
    }

    fun load() {
        donationsList.value = RatingManager.findAll()
    }
}