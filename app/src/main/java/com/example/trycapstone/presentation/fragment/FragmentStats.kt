package com.example.trycapstone.presentation.fragment

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trycapstone.R
import com.example.trycapstone.data.*
import com.example.trycapstone.databinding.FragmentStatsBinding
import com.example.trycapstone.presentation.vm.PriceViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class FragmentStats : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var adapter: StatsAdapter
    private lateinit var viewModel: PriceViewModel
    private lateinit var provinsi: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[PriceViewModel::class.java]

        viewModel.dataCabai.observe(requireActivity()) {
            setUserData(it)
        }

        val searchView = binding.svUser
        binding.svUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.pencarianUser(query)
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        firebaseAuth = FirebaseAuth.getInstance()


    }

    private fun setUserData(userList: List<Price>) {
        val listUser = ArrayList<Price>()
        for (u in userList) {
            listUser.clear()
            listUser.addAll(userList)
        }
        val adapter = StatsAdapter(listUser)
        binding.usersrecyclerview.adapter = adapter
        binding.usersrecyclerview.layoutManager = LinearLayoutManager(requireActivity())

    }

}