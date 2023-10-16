package org.wit.rateMyInstitute.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.rateMyInstitute.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "rateMyInstitute.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<RatingModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RatingJSONStore(private val context: Context) : RatingStore {

    var ratings = mutableListOf<RatingModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RatingModel> {
        logAll()
        return ratings
    }

    override fun create(rating: RatingModel) {
        rating.id = generateRandomId()
        ratings.add(rating)
        serialize()
    }


    override fun update(rating: RatingModel) {
        val ratingsList = findAll() as ArrayList<RatingModel>
        var foundrating: RatingModel? = ratingsList.find { p -> p.id == rating.id }
        if (foundrating != null) {
            foundrating.title = rating.title
            foundrating.description = rating.description
            foundrating.image = rating.image
            foundrating.lat = rating.lat
            foundrating.lng = rating.lng
            foundrating.zoom = rating.zoom
        }
        serialize()
    }

    override fun delete(rating: RatingModel) {
        ratings.remove(rating)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(ratings, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        ratings = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        ratings.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}