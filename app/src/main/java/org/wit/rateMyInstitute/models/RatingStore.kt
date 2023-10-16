package org.wit.rateMyInstitute.models

interface RatingStore {
    fun findAll(): List<RatingModel>
    fun create(rating: RatingModel)
    fun update(rating: RatingModel)
}