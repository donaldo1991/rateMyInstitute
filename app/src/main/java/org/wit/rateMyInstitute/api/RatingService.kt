package org.wit.rateMyInstitute.api
import org.wit.rateMyInstitute.models.RatingModel
import retrofit2.Call
import retrofit2.http.*

interface RatingService {
    @GET("/ratings")
    fun findall(): Call<List<RatingModel>>
    @GET("/ratings/{email}")
    fun findall(@Path("email") email: String?)
            : Call<List<RatingModel>>
    @GET("/ratings/{email}/{id}")
    fun get(@Path("email") email: String?,
            @Path("id") id: String): Call<RatingModel>
    @DELETE("/donations/{email}/{id}")
    fun delete(@Path("email") email: String?,
               @Path("id") id: String): Call<RatingWrapper>

    @POST("/donations/{email}")
    fun post(@Path("email") email: String?,
             @Body donation: RatingModel)
            : Call<RatingWrapper>

    @PUT("/donations/{email}/{id}")
    fun put(@Path("email") email: String?,
            @Path("id") id: String,
            @Body donation: RatingModel
    ): Call<RatingWrapper>
}
