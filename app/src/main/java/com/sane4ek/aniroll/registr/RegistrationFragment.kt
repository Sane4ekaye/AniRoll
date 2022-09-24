package com.sane4ek.aniroll.registr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll._database.room.entities.User
import com.sane4ek.aniroll.databinding.FragmentRegistrationBinding
import com.sane4ek.aniroll.utils.PREFS_USER
import com.sane4ek.aniroll.utils.SharedPrefs
import com.sane4ek.aniroll.utils.isEmailValid

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()
    private var email = ""
    private var userExistsEmail = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        registrationViewModel.registrationSuccessLiveData.observe(viewLifecycleOwner, registrationSuccess())
        registrationViewModel.registrationFailureEmailLiveData.observe(viewLifecycleOwner, registrationFailureEmail())
        registrationViewModel.registrationFailureNicknameLiveData.observe(viewLifecycleOwner, registrationFailureNickname())

        binding.btnSignUp.setOnClickListener {
            clearEditText(binding.etNickname, binding.textNicknameError, R.drawable.ic_nickname)
            clearEditText(binding.etEmail, binding.textEmailError, R.drawable.ic_email)

            val nickname = binding.etNickname.text.toString()
            email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            // проходит проверка полей, а затем регистрация
            if (nickname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (email.isEmailValid()) {
                    registrationViewModel.registerUser(User(nickname = nickname, email = email, password = password))
                } else {
                    Toast.makeText(requireContext(), "Введите корректный email", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.etEmail.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clearEditText(binding.etEmail, binding.textEmailError, R.drawable.ic_email)
            }
        }
        binding.etNickname.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clearEditText(binding.etNickname, binding.textNicknameError, R.drawable.ic_nickname)
            }
        }

        binding.btnGoToLogin.setOnClickListener {
            if (userExistsEmail) {
                val bundle = Bundle()
                bundle.putString("email", email)
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment, bundle)
            } else {
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }

        binding.btnSkipRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_splashFragment)
        }
    }

    private fun registrationFailureEmail() = Observer<String> {
        setEditTextError(binding.etEmail, binding.textEmailError, it, R.drawable.ic_email_red)
        userExistsEmail = true
    }

    private fun registrationFailureNickname() = Observer<String> {
        setEditTextError(binding.etNickname, binding.textNicknameError, it, R.drawable.ic_nickname_red)
    }

    private fun registrationSuccess() = Observer<User> {
        SharedPrefs.saveUser(PREFS_USER, it, requireContext())
        findNavController().navigate(R.id.action_registrationFragment_to_splashFragment)
    }

    private fun setEditTextError(editText: EditText, textError: TextView, errorMessage: String, icon: Int) {
        val view = View(context)
        view.setBackgroundResource(R.drawable.red_stroke_edittext)
        editText.setHintTextColor(resources.getColor(R.color.et_red60))
        editText.background = view.background
        editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        textError.text = errorMessage
        val params = textError.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        textError.layoutParams = params
    }

    private fun clearEditText(editText: EditText, textError: TextView, icon: Int) {
        val view = View(context)
        view.setBackgroundResource(R.drawable.gray_stroke_edittext)
        editText.setHintTextColor(resources.getColor(R.color.et_hint))
        editText.background = view.background
        editText.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        textError.text = ""
        val params = textError.layoutParams
        params.height = 0
        textError.layoutParams = params
    }
}