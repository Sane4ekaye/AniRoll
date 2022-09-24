package com.sane4ek.aniroll.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll._database.room.entities.User
import com.sane4ek.aniroll.databinding.FragmentLoginBinding
import com.sane4ek.aniroll.utils.PREFS_USER
import com.sane4ek.aniroll.utils.SharedPrefs
import com.sane4ek.aniroll.utils.isEmailValid

class LoginFragment : Fragment() {

    private val TAG = "loginfrag"

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        loginViewModel.loginSuccessLiveData.observe(viewLifecycleOwner, loginSuccess())
        loginViewModel.loginFailureEmailLiveData.observe(viewLifecycleOwner, loginFailureEmail())
        loginViewModel.loginFailurePasswordLiveData.observe(viewLifecycleOwner, loginFailurePassword())

        val existsEmail = arguments?.getString("email")
        Log.i(TAG, "init: $existsEmail")
        if (existsEmail != null) {
            binding.etEmail.setText(existsEmail)
        }

        binding.btnSignIn.setOnClickListener() {
            clearEditText(binding.etPassword, binding.textPasswordError, R.drawable.ic_password)
            clearEditText(binding.etEmail, binding.textEmailError, R.drawable.ic_email)

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
//            binding.etPassword.inputType = 0
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

        binding.etEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clearEditText(binding.etEmail, binding.textEmailError, R.drawable.ic_email)
            }
        }
        binding.etPassword.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clearEditText(binding.etPassword, binding.textPasswordError, R.drawable.ic_password)
            }
        }

        binding.btnGoToReg.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.btnSkipLogin.setOnClickListener() {
            findNavController().navigate(R.id.action_loginFragment_to_splashFragment)
        }
    }

    private fun loginFailureEmail() = Observer<Boolean> {
        setEditTextError(binding.etEmail, binding.textEmailError, "Такой email не зарегистрирован", R.drawable.ic_email_red)
    }

    private fun loginFailurePassword() = Observer<Boolean> {
        setEditTextError(binding.etPassword, binding.textPasswordError, "Неправильный пароль, попробуйте снова", R.drawable.ic_password_red)
    }

    private fun loginSuccess() = Observer<User> {
        SharedPrefs.saveUser(PREFS_USER, it, requireContext())
        findNavController().navigate(R.id.action_loginFragment_to_splashFragment)
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