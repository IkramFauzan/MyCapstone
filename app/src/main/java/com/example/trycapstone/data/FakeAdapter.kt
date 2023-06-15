package com.example.trycapstone.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trycapstone.databinding.RowStatsBinding

class FakeAdapter : RecyclerView.Adapter<FakeAdapter.ViewHolder>() {

    private var statsList: MutableList<DataItem> = mutableListOf()

    inner class ViewHolder(private val binding: RowStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(statsList: DataItem) {
            with(binding) {
                dateTextView.text = statsList.jsonMember012020
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
    fun setData(data: List<DataItem>) {
        statsList.clear()
        statsList.addAll(data)
        notifyDataSetChanged()
    }
}