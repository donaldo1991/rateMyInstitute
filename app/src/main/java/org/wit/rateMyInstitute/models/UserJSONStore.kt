package org.wit.rateMyInstitute.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.rateMyInstitute.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val User_JSON_FILE = "users.json"
val user_gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val user_listType: Type = object : TypeToken<ArrayList<RatingModel>>() {}.type


class UserJSONStore(private val context: Context) : UserStore {

    var users = mutableListOf<UserModel>()

    init {
        if (exists(context, User_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        logAll()
        return users
    }

    override fun create(user: UserModel) {
        users.add(user)
        serialize()
    }

    override fun delete(user: UserModel) {
        users.remove(user)
        serialize()
    }

    private fun serialize() {
        val jsonString = user_gsonBuilder.toJson(users, user_listType)
        write(context, User_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, User_JSON_FILE)
        users = user_gsonBuilder.fromJson(jsonString, user_listType)
    }

    private fun logAll() {
        users.forEach { Timber.i("$it") }
    }
}
