package ir.kivee.dadzjokes.get_jokes


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

import ir.kivee.dadzjokes.R
import ir.kivee.dadzjokes.database.JokeDatabase
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
        initViewModel()
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.fGetJokesJoke.typeface=ResourcesCompat.getFont(context!!,R.font.font_main)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveState.observe(this, Observer<Boolean> { saveState ->
            showSnackbar(saveState)
        })
    }

    private fun showSnackbar(saveState:Boolean){
        if (saveState)
            Snackbar.make(binding.fGetJokesContainer,"Operation Successful",Snackbar.LENGTH_SHORT).show()

    }

    private fun initViewModel(){
        val application = requireNotNull(this.activity).application
        val dataSource=JokeDatabase.getInstance(application).jokeDao
        val vieModelFactory = GetJokesViewModelFactory(dataSource,application)
        viewModel=ViewModelProviders.of(activity!!,vieModelFactory).get(GetJokeViewModel::class.java)
    }



}
