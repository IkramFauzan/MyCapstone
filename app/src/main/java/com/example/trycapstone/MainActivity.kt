package com.example.trycapstone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trycapstone.data.News
import com.example.trycapstone.data.BeritaAdapter
import com.example.trycapstone.databinding.ActivityMainBinding
import com.example.trycapstone.presentation.auth.SignInActivity
import com.example.trycapstone.presentation.main.DetailActivity
import com.example.trycapstone.presentation.main.HomeActivity
import com.example.trycapstone.presentation.main.ProfileActivity
import com.example.trycapstone.presentation.main.StatsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.navigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    Navigation.findNavController(this, binding.fragContainMain.id).navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_stats -> {
                    Navigation.findNavController(this, binding.fragContainMain.id).navigate(R.id.navigation_stats)
                    true
                }
                R.id.navigation_scan -> {
                    Navigation.findNavController(this, binding.fragContainMain.id).navigate(R.id.navigation_scan)
                    true
                }
                R.id.navigation_profile -> {
                    Navigation.findNavController(this, binding.fragContainMain.id).navigate(R.id.navigation_profile)
                    hideNavBar()
                    true
                }
                else -> false
            }
        }



        setContentView(binding.root)
    }

    private fun hideNavBar() {
        binding.navigationView.visibility = View.GONE
    }


}

