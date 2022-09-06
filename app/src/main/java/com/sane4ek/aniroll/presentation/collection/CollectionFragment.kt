package com.sane4ek.aniroll.presentation.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.FragmentCollectionBinding

class CollectionFragment : Fragment() {

    private lateinit var binding: FragmentCollectionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCollectionBinding.inflate(inflater)

        return binding.root
    }
}