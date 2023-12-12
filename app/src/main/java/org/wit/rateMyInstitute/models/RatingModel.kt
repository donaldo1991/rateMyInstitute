package org.wit.rateMyInstitute.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RatingModel(
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var fee: Double = 0.0,
    var overallRating: Double = 0.0,
    var gradRate: Int = 0,
    var image: Uri = Uri.EMPTY,
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var zoom: Float = 0f) : Parcelable