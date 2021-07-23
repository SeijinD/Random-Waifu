package eu.seijindemon.randomwaifu.data.remote

import eu.seijindemon.randomwaifu.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val client = OkHttpClient.Builder()
        .addInterceptor(ResponseInterceptor())
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dbApi: DbApi by lazy {
        retrofit.create(DbApi::class.java)
    }

}