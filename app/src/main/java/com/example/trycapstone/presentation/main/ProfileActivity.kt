package com.example.trycapstone.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trycapstone.R
import com.example.trycapstone.databinding.ActivityProfileBinding
import com.example.trycapstone.presentation.auth.FragmentLogin
import com.example.trycapstone.presentation.auth.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {
            logout()
        }

        setContentView(binding.root)
    }

    private fun logout() {
        firebaseAuth.signOut()

        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()

    }
}