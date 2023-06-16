package com.example.trycapstone.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trycapstone.databinding.RowStatsBinding

class StatsAdapter(private val statsList: List<Price>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {
    class ViewHolder(var binding: RowStatsBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statsList = statsList[position]
        holder.binding.dateTextView.text = statsList.category
        holder.binding.priceTextView.text = statsList.price
    }
    override fun getItemCount() = statsList.size

}