package com.example.trycapstone.presentation.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trycapstone.R
import com.example.trycapstone.data.ApiService
import com.example.trycapstone.data.FakeAdapter
import com.example.trycapstone.data.Price
import com.example.trycapstone.data.StatsAdapter
import com.example.trycapstone.presentation.auth.SignInActivity
import com.example.trycapstone.databinding.FragmentStatsBinding
import com.example.trycapstone.presentation.vm.CabaiViewModel
import com.example.trycapstone.presentation.vm.PriceViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth

class FragmentStats : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    //private lateinit var adapter: StatsAdapter
    private lateinit var adapter: FakeAdapter
    private lateinit var viewModel: PriceViewModel

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

        //adapter = StatsAdapter()
        adapter = FakeAdapter()
        binding.usersrecyclerview.adapter = adapter
        binding.usersrecyclerview.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.priceList.observe(requireActivity()) {cabai ->
            if (cabai != null) {
                adapter.setData(cabai)
            }
        }

        val provinces = listOf(
            "ACEH", "BALI", "BANTEN", "BENGKULU", "DI YOGYAKARTA", "DKI JAKARTA",
            "GORONTALO", "JAMBI", "JAWA BARAT", "JAWA TENGAH", "JAWA TIMUR",
            "KALIMANTAN BARAT", "KALIMANTAN SELATAN", "KALIMANTAN TENGAH",
            "KALIMANTAN TIMUR", "KALIMANTAN UTARA", "KEPULAUAN BANGKA BELITUNG",
            "KEPULAUAN RIAU", "LAMPUNG", "MALUKU", "MALUKU UTARA", "NUSA TENGGARA BARAT",
            "NUSA TENGGARA TIMUR", "PAPUA", "PAPUA BARAT", "RIAU", "SULAWESI BARAT",
            "SULAWESI SELATAN", "SULAWESI TENGAH", "SULAWESI TENGGARA", "SULAWESI UTARA",
            "SUMATERA BARAT", "SUMATERA SELATAN", "SUMATERA UTARA", "KALIMANTAN SELATAN",
            "SUMATERA SELATAN", "SUMATERA UTARA"
        )

        val adapter = ArrayAdapter(requireActivity(), R.layout.list_province, provinces)

        binding.autoComplete.setAdapter(adapter)

        binding.autoComplete.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val itemSelected = adapterView.getItemAtPosition(i) as String
            Toast.makeText(requireActivity(), "Anda memilih: $itemSelected", Toast.LENGTH_SHORT).show()

            viewModel.getPrice(itemSelected)
        }

        viewModel.listPrice.observe(requireActivity() ) { priceData ->
            val sB = StringBuilder()
            for (price in priceData) {
                sB.append("Date: ${price.monthYear}, Price: ${price.price}\n")
            }

        }

        firebaseAuth = FirebaseAuth.getInstance()

        showChart()

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