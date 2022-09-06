package com.sane4ek.aniroll.presentation.auth.reg

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sane4ek.aniroll.data.room.entities.User
import com.sane4ek.aniroll.databinding.FragmentRegistrationBinding
import com.sane4ek.aniroll.domain.usecase.RegistrationUseCase
import com.sane4ek.aniroll.utils.isEmailValid

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private val registrationUseCase by lazy { RegistrationUseCase() }

    private val registratiobViewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            val nickname = binding.etNickname.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            // проходит проверка полей, а затем регистрация
            if (nickname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (email.isEmailValid()) {
                    registratiobViewModel.registerUser(User(nickname = nickname, email = email, password = password))
                } else {
                    Toast.makeText(requireContext(), "Введите корректный email", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoRoLogin.setOnClickListener {
            // перейти на экран логина
        }

        binding.btnSkipRegistration.setOnClickListener {
            // скинуть регистрацию и перейти на мейн экран
        }
    }
}