package com.example.mencak.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.mencak.R
import com.example.mencak.databinding.ActivityLoginBinding
import com.example.mencak.model.Result
import com.example.mencak.model.UserPreference
import com.example.mencak.ui.ViewModelFactory
import com.example.mencak.ui.home.HomeActivity
import com.example.mencak.ui.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreference: UserPreference
    private lateinit var sharedPreferences: SharedPreferences
    private var token: String? = String()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
//        token = sharedPreferences.getString(UserPreference.TOKEN, null)
//        sharedPreference = UserPreference(this)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupView()
//        setupViewModel()
        setupAction()
        playAnimation()
    }

//    private fun showLoading(isLoading: Boolean) {
//        if (isLoading) {
//            binding.progressBar.visibility = View.VISIBLE
//        } else {
//            binding.progressBar.visibility = View.GONE
//        }
//    }

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
//        loginViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(this)
//        )[LoginViewModel::class.java]
//    }

    private fun setupAction() {
        binding.loginBtn.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                email.isEmpty() -> {
                    binding.layoutEmailEditText.error = getString(R.string.add_email)
                }
                password.isEmpty() -> {
                    binding.layoutPasswordEditText.error = getString(R.string.add_password)
                }
                else -> {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) {
                            if (it.isSuccessful) {
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else {
                                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                                binding.emailEditText.setText("")
                                binding.passwordEditText.setText("")
                            }
                        }
//                    loginViewModel.login(email, password).observe(this) { result ->
//                        if (result != null) {
//                            when (result) {
//                                is Result.Loading -> {
//                                    binding.progressBar.visibility = View.VISIBLE
//                                }
//                                is Result.Success -> {
//                                    binding.progressBar.visibility = View.GONE
//                                    AlertDialog.Builder(this).apply {
//                                        setTitle("Yeah!")
//                                        setMessage(getString(R.string.email_success))
//                                        setPositiveButton(getString(R.string.next)) { _, _ ->
//                                            val intent = Intent(context, HomeActivity::class.java)
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
//                                        setMessage(getString(R.string.email_failed))
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
        binding.signupTextViewButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val loginTitle = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1f).setDuration(500)
//        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
//        val messageTitle =
//            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.textView2, View.ALPHA, 1f).setDuration(500)
        val emailEdit =
            ObjectAnimator.ofFloat(binding.layoutEmailEditText, View.ALPHA, 1f).setDuration(500)
        val password =
            ObjectAnimator.ofFloat(binding.textView3, View.ALPHA, 1f).setDuration(500)
        val passwordEdit =
            ObjectAnimator.ofFloat(binding.layoutPasswordEditText, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.textView4, View.ALPHA, 1f).setDuration(500)
        val signupLink =
            ObjectAnimator.ofFloat(binding.signupTextViewButton, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(loginTitle, email, emailEdit, password, passwordEdit, signup, signupLink)
        }

//        val togethermessage = AnimatorSet().apply {
//            playTogether(messageTitle, title)
//        }

        AnimatorSet().apply {
            playSequentially(together)
            start()
        }
    }
}