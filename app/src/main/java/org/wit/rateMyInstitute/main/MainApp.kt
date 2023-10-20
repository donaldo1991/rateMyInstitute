package org.wit.rateMyInstitute.main

import android.app.Application
import org.wit.rateMyInstitute.models.RatingMemStore
import org.wit.rateMyInstitute.models.RatingJSONStore
import org.wit.rateMyInstitute.models.RatingStore
import timber.log.Timber
import timber.log.Timber.Forest.i
import com.google.firebase.FirebaseApp

class MainApp : Application() {

    lateinit var ratings:RatingStore

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Timber.plant(Timber.DebugTree())
        ratings = RatingMemStore()
        //ratings = RatingJSONStore(applicationContext)
        i("rating started")

    }
}