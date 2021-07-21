package eu.seijindemon.randomwaifu.data.remote

import eu.seijindemon.randomwaifu.data.model.Waifu
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DbApi {

    @GET("/{type}/{category}")
    suspend fun getWaifu(@Path("type") type: String, @Path("category") category: String): Response<Waifu>

}