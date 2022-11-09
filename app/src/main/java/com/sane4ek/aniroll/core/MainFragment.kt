package com.sane4ek.aniroll.core

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.core.data.AppDataModel
import com.sane4ek.aniroll.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val navController = Navigation.findNavController(requireActivity(), R.id.mainFragmentContainer)
        binding.bottomNavigationView.setupWithNavController(navController)


    }
}