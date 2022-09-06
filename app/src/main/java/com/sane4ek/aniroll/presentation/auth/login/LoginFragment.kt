package com.sane4ek.aniroll.presentation.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.data.room.entities.User
import com.sane4ek.aniroll.databinding.FragmentLoginBinding
import com.sane4ek.aniroll.utils.PREFS_USER
import com.sane4ek.aniroll.utils.SharedPrefs
import com.sane4ek.aniroll.utils.isEmailValid

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.loginSuccessLiveData.observe(viewLifecycleOwner, loginSuccess())
        loginViewModel.loginFailureLiveData.observe(viewLifecycleOwner, loginFailure())

        binding.btnSignIn.setOnClickListener() {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            binding.etPassword.inputType = 0
            // проходит проверка полей, а затем авторизация
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (email.isEmailValid()) {
                    loginViewModel.login(email = email, password = password)
                } else {
                    Toast.makeText(requireContext(), "Введите корректный email", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoToReg.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun loginFailure() = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun loginSuccess() = Observer<User> {
        SharedPrefs.saveUser(PREFS_USER, it, requireContext())
        findNavController().navigate(R.id.action_loginFragment_to_splashFragment)
    }
}