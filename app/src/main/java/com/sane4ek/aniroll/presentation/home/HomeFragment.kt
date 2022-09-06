package com.sane4ek.aniroll.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.FragmentHomeBinding
import com.sane4ek.aniroll.databinding.FragmentMainBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

}