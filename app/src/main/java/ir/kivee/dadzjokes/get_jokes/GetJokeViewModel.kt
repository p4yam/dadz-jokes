package ir.kivee.dadzjokes.get_jokes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.kivee.dadzjokes.database.JokeDao
import ir.kivee.dadzjokes.database.JokeModel
import ir.kivee.dadzjokes.network.DadsApi
import kotlinx.coroutines.*

class GetJokeViewModel(val database: JokeDao, application: Application) :
    AndroidViewModel(application) {

    private val _state = MutableLiveData<Int>()
    val state: LiveData<Int>
        get() = _state

    private val _saveState = MutableLiveData<Boolean>()
    val saveState: LiveData<Boolean>
        get() = _saveState

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
            _saveState.value=false
            val service = DadsApi.retrofitAdiService.getAJoke()
            try {
                val result = service.await()
                _state.value = 1
                _joke.value = result.joke
            } catch (ex: Exception) {
                _state.value = 1
                _joke.value = ex.localizedMessage
            }
        }
    }

    fun refreshJoke() {
        scopeModel.launch {
            _state.value = 0
            _saveState.value=false
            val service = DadsApi.retrofitAdiService.getAJoke()
            try {
                val result = service.await()
                _state.value = 1
                _joke.value = result.joke
            } catch (ex: Exception) {
                _state.value = 1
                _joke.value = ex.localizedMessage
            }
        }
    }

    fun addJoke(jokeContent: String) {
        scopeModel.launch {
            try {
                if(_saveState.value!=true) {
                    addJokeToDb(jokeContent)
                    _saveState.value = true
                }
            } catch (ex: Exception) {
                _saveState.value = false
            }
        }
    }

    private suspend fun addJokeToDb(jokeContent: String) {
        withContext(Dispatchers.IO) {
            database.addJoke(JokeModel(joke = jokeContent))
        }
    }

/*    private fun changestate(current: Int) {
        _state.value = current
    }*/

    override fun onCleared() {
        super.onCleared()
        jokeJob.cancel()
    }
}