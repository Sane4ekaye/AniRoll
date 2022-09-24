package com.sane4ek.aniroll.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.FragmentCollectionBinding
import com.sane4ek.aniroll.databinding.FragmentProfileBinding
import com.sane4ek.aniroll.utils.PREFS_USER
import com.sane4ek.aniroll.utils.SharedPrefs

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater)

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
    }
}