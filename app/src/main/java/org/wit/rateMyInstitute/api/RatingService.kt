package org.wit.rateMyInstitute.api
import org.wit.rateMyInstitute.models.RatingModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RatingService {
    @GET("/ratings")
    fun getall(): Call<List<RatingModel>>

    @GET("/ratings/{id}")
    fun get(@Path("id") id: String): Call<RatingModel>

    @DELETE("/ratings/{id}")
    fun delete(@Path("id") id: String): Call<RatingWrapper>

    @POST("/ratings")
    fun post(@Body donation: RatingModel): Call<RatingWrapper>

    @PUT("/ratings/{id}")
    fun put(@Path("id") id: String,
            @Body rating: RatingModel
    ): Call<RatingWrapper>
}