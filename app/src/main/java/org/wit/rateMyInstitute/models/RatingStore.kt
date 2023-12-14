package org.wit.rateMyInstitute.models

import androidx.lifecycle.MutableLiveData

interface RatingStore {
    fun findAll(ratingsList:
                MutableLiveData<List<RatingModel>>)
    fun findAll(email:String,
                ratingsList: MutableLiveData<List<RatingModel>>)
    fun findById(email:String, id: String, rating: MutableLiveData<RatingModel>)
    fun create(rating: RatingModel)
    fun delete(email:String,id: String)
    fun update(email: String,id: String, rating: RatingModel)
}
