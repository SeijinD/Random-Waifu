package eu.seijindemon.randomwaifu.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.seijindemon.randomwaifu.data.model.Waifu
import eu.seijindemon.randomwaifu.data.repository.WaifuRepository
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class WaifuViewModel(private val repository: WaifuRepository): ViewModel() {

    val waifu = MutableLiveData<Waifu>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    fun getWaifu(type: String, category: String) {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = repository.getWaifu(type, category)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        waifu.value = it
                    }
                } else {
                    Log.d("Response", response.errorBody().toString())
                }
            }
        }
    }

}

class WaifuViewModelFactory(private val repository: WaifuRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WaifuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WaifuViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}