package com.example.trycapstone.presentation.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trycapstone.data.News
import com.example.trycapstone.data.BeritaAdapter
import com.example.trycapstone.databinding.FragmentHomeBinding
import com.example.trycapstone.presentation.auth.SignInActivity
import com.example.trycapstone.presentation.main.DetailActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: BeritaAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
            requireActivity().finish()
        }
        val newsList = mutableListOf<News>()

        binding.usersrecyclerview.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = BeritaAdapter { newsItem ->
            navigateToDetailActivity(newsItem)
        }

        adapter.setNewsList(newsList)

        binding.usersrecyclerview.adapter = adapter

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

        setupAction()

    }

    private fun navigateToDetailActivity(newsItem: News) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("URL_article", newsItem.url)
        startActivity(intent)
    }

    private fun setupAction() {

    }
}