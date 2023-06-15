package com.example.trycapstone.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trycapstone.databinding.RowStatsBinding

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    private var statsList: MutableList<Cabai> = mutableListOf()

    inner class ViewHolder(private val binding: RowStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(statsList: Cabai) {
            with(binding) {
                dateTextView.text = statsList.monthYear
                priceTextView.text = "Rp. ${statsList.price}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statsList = statsList[position]
        holder.bind(statsList)

    }

    override fun getItemCount(): Int {
        return statsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Cabai>) {
        statsList.clear()
        statsList.addAll(data)
        notifyDataSetChanged()
    }
}