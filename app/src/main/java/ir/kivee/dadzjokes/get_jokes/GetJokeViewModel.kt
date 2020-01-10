package ir.kivee.dadzjokes.get_jokes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ir.kivee.dadzjokes.network.DadsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class GetJokeViewModel : ViewModel() {

    private val _state = MutableLiveData<Int>()
    val state: LiveData<Int>
        get() = _state

    private val _joke = MutableLiveData<String>()
    val joke: LiveData<String>
        get() = _joke

    private val jokeJob = Job()
    private val scopeModel = CoroutineScope(jokeJob + Dispatchers.Main)

    init {
        getJoke()
    }

    private fun getJoke() {
        scopeModel.launch {
            _state.value = 0
            val service = DadsApi.retrofitAdiService.getAJoke()
            try {
                val result=service.await()
                _state.value=1
                _joke.value=result.joke
                Log.d("p4yam",result.joke)
            }catch (ex:Exception){
                Log.d("p4yam",ex.toString())
                _state.value=1
                _joke.value=ex.localizedMessage
            }
        }
    }

    fun refreshJoke(){
        scopeModel.launch(Dispatchers.Main) {
            _state.value = 0
            val service = DadsApi.retrofitAdiService.getAJoke()
            try {
                val result=service.await()
                _state.value=1
                _joke.value=result.joke
                Log.d("p4yam",result.joke)
            }catch (ex:Exception){
                Log.d("p4yam",ex.toString())
                _state.value=1
                _joke.value=ex.localizedMessage
            }
        }
    }

    private fun changestate(current: Int) {
        _state.value = current
    }

    override fun onCleared() {
        super.onCleared()
        jokeJob.cancel()
    }
}