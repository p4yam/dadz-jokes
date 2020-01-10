package ir.kivee.dadzjokes.get_jokes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import ir.kivee.dadzjokes.R
import ir.kivee.dadzjokes.databinding.FragmentGetJokeBinding

/**
 * A simple [Fragment] subclass.
 */
class GetJokeFragment : Fragment() {

    private lateinit var binding: FragmentGetJokeBinding
    private lateinit var viewModel: GetJokeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_get_joke,container,false)
        viewModel=ViewModelProviders.of(activity!!).get(GetJokeViewModel::class.java)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.fGetJokesJoke.typeface=ResourcesCompat.getFont(context!!,R.font.font_main)
        return binding.root
    }



}
