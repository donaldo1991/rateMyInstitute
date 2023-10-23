package org.wit.rateMyInstitute.models

import timber.log.Timber.Forest.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class RatingMemStore : RatingStore {

    val ratings = ArrayList<RatingModel>()

    override fun findAll(): List<RatingModel> {
        return ratings
    }

    override fun create(rating: RatingModel) {
        rating.id = getId()
        ratings.add(rating)
        logAll()
    }

    override fun update(rating: RatingModel) {
        var foundRating: RatingModel? = ratings.find { p -> p.id == rating.id }
        if (foundRating != null) {
            foundRating.name = rating.name
            foundRating.description = rating.description
            foundRating.fee = rating.fee
            foundRating.overallRating = rating.overallRating
            foundRating.gradRate = rating.gradRate
            foundRating.image = rating.image
            foundRating.lat = rating.lat
            foundRating.lng = rating.lng
            foundRating.zoom = rating.zoom
            logAll()
        }
    }

    override fun delete(rating: RatingModel) {
        ratings.remove(rating)
    }

    private fun logAll() {
        ratings.forEach { i("$it") }
    }
}