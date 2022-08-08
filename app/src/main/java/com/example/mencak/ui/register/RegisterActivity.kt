package com.example.mencak.ui.register

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mencak.R
import com.example.mencak.databinding.ActivityRegisterBinding
import com.example.mencak.model.Result
import com.example.mencak.ui.ViewModelFactory
import com.example.mencak.ui.home.HomeActivity
import com.example.mencak.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setupView()
//        setupViewModel()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

//    private fun setupViewModel() {
//        registerViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(this)
//        )[RegisterViewModel::class.java]
//    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                name.isEmpty() -> {
                    binding.nameEditTextLayout.error = getString(R.string.add_name)
                }
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = getString(R.string.add_email)
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = getString(R.string.add_password)
                }
                password.length < 6 -> {}
                else -> {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else {
                                Toast.makeText(this, "Register Failed", Toast.LENGTH_LONG).show()
                                binding.emailEditText.setText("")
                                binding.passwordEditText.setText("")
                                binding.nameEditText.setText("")
                            }
                        }
//                    registerViewModel.saveUser(name, email, password).observe(this) { result ->
//                        if (result != null) {
//                            when (result) {
//                                is Result.Loading -> {
//                                    binding.progressBar.visibility = View.VISIBLE
//                                }
//                                is Result.Success -> {
//                                    binding.progressBar.visibility = View.GONE
//                                    AlertDialog.Builder(this).apply {
//                                        setTitle("Yeah!")
//                                        setMessage(getString(R.string.signup_success))
//                                        setPositiveButton(getString(R.string.next)) { _, _ ->
//                                            val intent = Intent(context, LoginActivity::class.java)
//                                            intent.flags =
//                                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                                            startActivity(intent)
//                                            finish()
//                                        }
//                                        create()
//                                        show()
//                                    }
//                                }
//                                is Result.Error -> {
//                                    binding.progressBar.visibility = View.GONE
//                                    AlertDialog.Builder(this).apply {
//                                        setTitle("Aww!")
//                                        setMessage(getString(R.string.signup_failed))
//                                        setNegativeButton(getString(R.string.back)) { _, _ -> }
//                                        create()
//                                        show()
//                                    }
//                                }
//                            }
//                        }
//                    }
                }
            }
        }
        binding.loginTextViewButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }
}