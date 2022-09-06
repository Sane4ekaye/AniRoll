package com.sane4ek.aniroll.presentation.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.data.room.repository.UserRealization
import com.sane4ek.aniroll.data.room.repository.UserRepository

class SplashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        object : CountDownTimer(1000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//
//            }
//
//            override fun onFinish() {
//                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
//            }
//
//        }.start()
    }
}