package com.example.trycapstone.presentation.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.trycapstone.R
import com.example.trycapstone.User
import com.example.trycapstone.databinding.ActivitySignUpBinding
import com.example.trycapstone.presentation.fragment.FragmentHome
import com.example.trycapstone.presentation.main.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        setupView()
        setupAction()
        playPropertyAnimation()

        setContentView(binding.root)
    }

    private fun playPropertyAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -20f, 20f).apply {
            duration = 3000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(500)
        val register = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(500)
        val name = ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(title, name, email, password, signup, register)
            start()
        }
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

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val username = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                username.isBlank() -> {
                    binding.nameEditTextLayout.error = getString(R.string.input_name)
                }
                email.isBlank() -> {
                    binding.emailEditTextLayout.error = getString(R.string.email_input)
                }
                password.isBlank() -> {
                    binding.passwordEditTextLayout.error = getString(R.string.password_input)
                }
                password.length < 8 -> {
                    Toast.makeText(this, getString(R.string.must_8_character), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    registerFirebase(username, email, password)
                }
            }
        }
    }

    private fun registerFirebase(username: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {auth ->
                    val userDocRef = db.collection("users").document(auth.user?.uid!!)
                    val userData = User(email, username, password)
                    userDocRef.set(userData).addOnSuccessListener {
                        Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, FragmentHome::class.java)
                        startActivity(intent)
                    } .addOnFailureListener {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                        throw it
                    }
            } .addOnFailureListener {
                throw it
            }
        }
    }
}