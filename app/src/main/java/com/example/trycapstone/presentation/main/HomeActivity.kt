package com.example.trycapstone.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trycapstone.R
import com.example.trycapstone.data.News
import com.example.trycapstone.data.BeritaAdapter
import com.example.trycapstone.databinding.ActivityHomeBinding
import com.example.trycapstone.databinding.ActivityMainBinding
import com.example.trycapstone.presentation.auth.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: BeritaAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        val newsList = mutableListOf<News>()

        binding.usersrecyclerview.layoutManager = LinearLayoutManager(this)

        val adapter = BeritaAdapter { newsItem ->
            navigateToDetailActivity(newsItem)
        }

        adapter.setNewsList(newsList)

        binding.usersrecyclerview.adapter = adapter

//        adapter.setOnItemClickListener { newsItem ->
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra("url", newsItem.url)
//            startActivity(intent)
//        }

        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("articles")

        collectionRef.orderBy("id", Query.Direction.DESCENDING).addSnapshotListener { querySnapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }

            newsList.clear()

            for (document in querySnapshot!!) {
                val title = document.getString("judul")
                val image = document.getString("URL_gambar")
                val url = document.getString("URL_article")

                if (!title.isNullOrEmpty() && !image.isNullOrEmpty() && !url.isNullOrEmpty()) {
                    val newsItem = News(title, image, url)
                    newsList.add(newsItem)
                }
            }

            adapter.notifyDataSetChanged()
        }

//        val db = FirebaseFirestore.getInstance()
//        val newsPath = db.collection("articles")
//            .orderBy("id", Query.Direction.DESCENDING)
//        newsPath.get().addOnSuccessListener { query ->
//            for (document in query.documents) {
//                val title = document.getString("judul")
//                val image = document.getString("URL_gambar")
//
//                val news = News(title!!, image!!)
//                newsList.add(news)
//            }
//
//            adapter.notifyDataSetChanged()
//        }

        setupAction()
        setContentView(binding.root)
    }

    private fun navigateToDetailActivity(newsItem: News) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("URL_article", newsItem.url)
        startActivity(intent)
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
}