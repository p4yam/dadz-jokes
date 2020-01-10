package ir.kivee.dadzjokes.favorite_jokes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import ir.kivee.dadzjokes.R
import ir.kivee.dadzjokes.databinding.FragmentFavoriteJokesBinding

/**
 * A simple [Fragment] subclass.
 */
class FavoriteJokesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteJokesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_favorite_jokes,container,false)
        return binding.root
    }


}
