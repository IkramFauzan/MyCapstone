package com.example.trycapstone.presentation.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.trycapstone.MainActivity
import com.example.trycapstone.R
import com.example.trycapstone.databinding.FragmentLoginBinding
import com.example.trycapstone.databinding.FragmentProfileBinding
import com.example.trycapstone.presentation.fragment.FragmentHome
import com.example.trycapstone.presentation.fragment.FragmentProfile
import com.example.trycapstone.presentation.main.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Suppress("DEPRECATION")
class FragmentLogin : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("login_status", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(requireActivity(), SignUpActivity::class.java)
            startActivity(intent)
        }

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            when {
                email.isEmpty() -> {
                    binding.emailEditText.error = getString(R.string.email_input)
                }
                password.isEmpty() -> {
                    binding.passwordEditText.error = getString(R.string.password_input)
                } password.length < 8 -> {
                Toast.makeText(requireActivity(), getString(R.string.must_8_character), Toast.LENGTH_SHORT).show()
            }
                else -> {
                    loginFirebase(email, password)
                }
            }
        }
    }

    private fun loginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isUserLoggedIn", true)
                    editor.apply()
                    startActivity(Intent(requireActivity(), FragmentProfile::class.java))
                } else {
                    Toast.makeText(requireContext(), "Login failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}