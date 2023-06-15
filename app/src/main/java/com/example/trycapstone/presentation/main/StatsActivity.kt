package com.example.trycapstone.presentation.main

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trycapstone.R
import com.example.trycapstone.databinding.ActivityStatsBinding
import com.example.trycapstone.presentation.auth.SignInActivity
import com.example.trycapstone.presentation.fragment.FragmentHome
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStatsBinding.inflate(layoutInflater)

        firebaseAuth = FirebaseAuth.getInstance()


        showChart()
        setupAction()
        setContentView(binding.root)
    }

    private fun showChart() {
        val entries = arrayListOf<Entry>()
        entries.add(Entry(1f, 50f))
        entries.add(Entry(2f, 70f))
        entries.add(Entry(3f, 60f))
        entries.add(Entry(4f, 80f))
        entries.add(Entry(5f, 75f))

        val dataSet = LineDataSet(entries, "Data Set")
        dataSet.color = Color.RED
        dataSet.valueTextColor = Color.BLACK

        val lineData = LineData(dataSet)
        binding.chart.data = lineData
        binding.chart.invalidate()
        binding.chart.animateY(2000)
    }

    private fun setupAction() {
//        binding.nav.setOnItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.navigation_home -> {
//                    val intent = Intent(this, HomeActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    true
//                }
//                R.id.navigation_stats -> {
//                    val intent = Intent(this, StatsActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    true
//                }
//                R.id.navigation_profile -> {
//                    val intent = Intent(this, ProfileActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    true
//                }
//                else -> false
//            }
//
//        }
    }

    private fun logout() {
        firebaseAuth.signOut()

        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()

    }
}