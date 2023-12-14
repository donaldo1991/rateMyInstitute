package org.wit.rateMyInstitute.models

import androidx.lifecycle.MutableLiveData
import org.wit.rateMyInstitute.api.RatingClient
import org.wit.rateMyInstitute.api.RatingWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

object RatingManager : RatingStore {

    private var ratings = ArrayList<RatingModel>()

    override fun findAll(ratingsList: MutableLiveData<List<RatingModel>>) {

        val call = RatingClient.getApi().findall()

        call.enqueue(object : Callback<List<RatingModel>> {
            override fun onResponse(call: Call<List<RatingModel>>,
                                    response: Response<List<RatingModel>>
            ) {
                ratingsList.value = response.body() as ArrayList<RatingModel>
                Timber.i("Retrofit findAll() = ${response.body()}")
            }

            override fun onFailure(call: Call<List<RatingModel>>, t: Throwable) {
                Timber.i("Retrofit findAll() Error : $t.message")
            }
        })
    }

    override fun findAll(email: String, ratingsList: MutableLiveData<List<RatingModel>>) {

        val call = RatingClient.getApi().findall(email)

        call.enqueue(object : Callback<List<RatingModel>> {
            override fun onResponse(call: Call<List<RatingModel>>,
                                    response: Response<List<RatingModel>>
            ) {
                ratingsList.value = response.body() as ArrayList<RatingModel>
                Timber.i("Retrofit findAll() = ${response.body()}")
            }

            override fun onFailure(call: Call<List<RatingModel>>, t: Throwable) {
                Timber.i("Retrofit findAll() Error : $t.message")
            }
        })
    }

    override fun findById(email: String, id: String, rating: MutableLiveData<RatingModel>)   {

        val call = RatingClient.getApi().get(email,id)

        call.enqueue(object : Callback<RatingModel> {
            override fun onResponse(call: Call<RatingModel>, response: Response<RatingModel>) {
                rating.value = response.body() as RatingModel
                Timber.i("Retrofit findById() = ${response.body()}")
            }

            override fun onFailure(call: Call<RatingModel>, t: Throwable) {
                Timber.i("Retrofit findById() Error : $t.message")
            }
        })
    }

    override fun create( rating: RatingModel) {

        val call = RatingClient.getApi().post(rating.email,rating)

        call.enqueue(object : Callback<RatingWrapper> {
            override fun onResponse(call: Call<RatingWrapper>,
                                    response: Response<RatingWrapper>
            ) {
                val ratingWrapper = response.body()
                if (ratingWrapper != null) {
                    Timber.i("Retrofit ${ratingWrapper.message}")
                    Timber.i("Retrofit ${ratingWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<RatingWrapper>, t: Throwable) {
                Timber.i("Retrofit Error : $t.message")
            }
        })
    }

    override fun delete(email: String,id: String) {

        val call = RatingClient.getApi().delete(email,id)

        call.enqueue(object : Callback<RatingWrapper> {
            override fun onResponse(call: Call<RatingWrapper>,
                                    response: Response<RatingWrapper>
            ) {
                val RatingWrapper = response.body()
                if (RatingWrapper != null) {
                    Timber.i("Retrofit Delete ${RatingWrapper.message}")
                    Timber.i("Retrofit Delete ${RatingWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<RatingWrapper>, t: Throwable) {
                Timber.i("Retrofit Delete Error : $t.message")
            }
        })
    }

    override fun update(email: String,id: String, rating: RatingModel) {

        val call = RatingClient.getApi().put(email,id,rating)

        call.enqueue(object : Callback<RatingWrapper> {
            override fun onResponse(call: Call<RatingWrapper>,
                                    response: Response<RatingWrapper>
            ) {
                val RatingWrapper = response.body()
                if (RatingWrapper != null) {
                    Timber.i("Retrofit Update ${RatingWrapper.message}")
                    Timber.i("Retrofit Update ${RatingWrapper.data.toString()}")
                }
            }

            override fun onFailure(call: Call<RatingWrapper>, t: Throwable) {
                Timber.i("Retrofit Update Error : $t.message")
            }
        })
    }
}