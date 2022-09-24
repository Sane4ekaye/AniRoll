package com.sane4ek.aniroll.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.FragmentCollectionBinding
import com.sane4ek.aniroll.databinding.FragmentHistoryBinding
import com.sane4ek.aniroll.utils.PREFS_USER
import com.sane4ek.aniroll.utils.SharedPrefs

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHistoryBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val user = SharedPrefs.getUser(PREFS_USER, requireContext())
        if (user == null) {
            binding.offerToLogin.visibility = View.VISIBLE
            binding.mainLayout.visibility = View.GONE
        } else {
            // делаем все че надо там в mainLayout
        }

        binding.offerBtnLogin.setOnClickListener {
            val mainNavView = requireActivity().findViewById<View>(R.id.mainContainerFragment)
            Navigation.findNavController(mainNavView).navigate(R.id.action_mainFragment_to_loginFragment2)
//            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }
}