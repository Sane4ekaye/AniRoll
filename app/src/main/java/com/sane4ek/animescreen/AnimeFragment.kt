package com.sane4ek.animescreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.FragmentAnimeBinding
import com.sane4ek.aniroll.databinding.FragmentHomeBinding

class AnimeFragment : Fragment() {

    private lateinit var binding: FragmentAnimeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}