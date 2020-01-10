package ir.kivee.dadzjokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ir.kivee.dadzjokes.databinding.ActivityMainBinding
import ir.kivee.dadzjokes.favorite_jokes.FavoriteJokesFragment
import ir.kivee.dadzjokes.get_jokes.GetJokeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        title = "Get a Joke"
        openFragment(GetJokeFragment())
        handleNavigation()
    }

    private fun handleNavigation() {
        binding.activityMainBottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_bar_get_joke ->
                    openFragment(GetJokeFragment())
                R.id.bottom_bar_saved_jokes ->
                    openFragment(FavoriteJokesFragment())
            }
            title = item.title
            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activityMainFrame, fragment)
            .addToBackStack(null)
            .commit()
    }
}
