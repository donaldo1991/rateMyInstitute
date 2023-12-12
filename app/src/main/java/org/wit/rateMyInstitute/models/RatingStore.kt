package org.wit.rateMyInstitute.models

interface RatingStore {
    fun findAll() : List<RatingModel>
    fun findById(id: Long) : RatingModel?
    fun create(rating: RatingModel)
}