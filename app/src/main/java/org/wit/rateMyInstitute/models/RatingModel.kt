package org.wit.rateMyInstitute.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class RatingModel(
    val id: Long = 0,
    var name: String = "",
    var description: String = "",
    var fee: Double = 0.0,
    var overallRating: Double = 0.0,
    var gradRate: Int = 0,
    val email: String = "joe@bloggs.com") : Parcelable