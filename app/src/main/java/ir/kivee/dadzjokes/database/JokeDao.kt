package ir.kivee.dadzjokes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDao {

    @Insert
    fun addJoke(joke: JokeModel)

    @Query("Select * From jokes_table")
    fun getJokes(): LiveData<JokeModel>

    @Delete
    fun deleteJoke(joke: JokeModel)
}