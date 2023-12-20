package org.wit.rateMyInstitute.models

import android.net.Uri
import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@IgnoreExtraProperties
@Parcelize
data class RatingModel(
    var uid: String = "",
    var name: String = "",
    var description: String = "",
    var fee: Double = 0.0,
    var overallRating: Double = 0.0,
    var gradRate: Int = 0,
    val email: String = "joe@bloggs.com") : Parcelable

{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "name" to name,
            "description" to description,
            "fee" to fee,
            "overallRating" to overallRating,
            "gradRate" to gradRate,
            "email" to email
        )
    }
}
