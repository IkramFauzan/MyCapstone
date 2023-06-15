package com.example.trycapstone.data

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trycapstone.databinding.LayoutBeritaBinding
import com.example.trycapstone.presentation.main.DetailActivity

class BeritaAdapter(private val onItemClick: (News) -> Unit) : RecyclerView.Adapter<BeritaAdapter.NewsViewHolder>() {

    private var newsList: List<News> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setNewsList(newsList: List<News>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LayoutBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsList = newsList[position]
        holder.bind(newsList)
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(private val binding: LayoutBeritaBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val newsItem = newsList[position]
                    onItemClick(newsItem)
                }
            }
        }

        fun bind(newsList: News) {
            with(binding) {
                titleTextView.text = newsList.title
            }
            Glide.with(itemView.context)
                .load(newsList.image)
                .into(binding.newsImageView)

//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra("URL_article", newsList.url)
//                itemView.context.startActivity(intent)

        }

    }
}