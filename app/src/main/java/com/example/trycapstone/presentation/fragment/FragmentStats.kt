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

        viewModel.dataCabai.observe(requireActivity()) {cabai ->
            setUserData(cabai)
        }

        firebaseAuth = FirebaseAuth.getInstance()

        showChart()
        setUpSearchView()

    }

    private fun setUserData(userList: List<Price>) {
        val listUser = ArrayList<Price>()
        for (u in userList) {
            listUser.clear()
            listUser.addAll(userList)
        }
        val adapter = StatsAdapter(listUser)
        binding.usersrecyclerview.adapter = adapter

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_action, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpSearchView()  {
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = binding.svUser

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.province)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.e("Terserah", "onQueryTextSubmit()")
                viewModel.pencarianUser(query)
                searchView.clearFocus()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
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
}