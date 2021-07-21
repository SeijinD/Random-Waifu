package eu.seijindemon.randomwaifu.data.repository

import eu.seijindemon.randomwaifu.data.model.Waifu
import eu.seijindemon.randomwaifu.data.remote.RetrofitInstance
import retrofit2.Response

class WaifuRepository {

    suspend fun getWaifu(type: String, category: String): Response<Waifu> {
        return RetrofitInstance.dbApi.getWaifu(type, category)
    }

}