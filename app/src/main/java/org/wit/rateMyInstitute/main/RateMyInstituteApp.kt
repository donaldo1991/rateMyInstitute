package org.wit.rateMyInstitute.main

import android.app.Application
import timber.log.Timber

class RateMyInstituteApp : Application() {

    //lateinit var donationsStore: DonationStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
      //  donationsStore = DonationManager()
        Timber.i("rateMyInstitute Application Started")
    }
}