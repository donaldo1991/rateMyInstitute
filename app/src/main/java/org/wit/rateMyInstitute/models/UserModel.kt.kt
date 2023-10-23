package org.wit.rateMyInstitute.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModel(var uid: String = "",
                     var firstName: String = "",
                     var lastName: String = "",
                     var email: String = "",
                     var password : String = ""): Parcelable