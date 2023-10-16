package org.wit.rateMyInstitute.main

import android.app.Application
import org.wit.rateMyInstitute.models.RatingMemStore
import timber.log.Timber
import timber.log.Timber.Forest.i

class MainApp : Application() {

    val ratings = RatingMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("rating started")

    }
}