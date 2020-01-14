package ir.kivee.dadzjokes.get_jokes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.kivee.dadzjokes.database.JokeDao

@Suppress("UNCHECKED_CAST")
class GetJokesViewModelFactory(
    private val database: JokeDao,
    private val application: Application
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetJokeViewModel::class.java)) {
            return GetJokeViewModel(database, application) as T
        }
        throw IllegalArgumentException("Class must be member of GetJokeViewModel")
    }
}