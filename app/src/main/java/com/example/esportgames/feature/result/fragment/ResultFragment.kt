package com.example.esportgames.feature.result.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentResultBinding
import com.example.esportgames.feature.result.adapter.ResultAdapter
import com.example.esportgames.feature.result.data.ResultModel

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var resultAdapter: ResultAdapter
    val resultList = listOf(
        ResultModel("Free Fire", "John", "₹1000", "12 Apr 2025", R.drawable.banner),
        ResultModel("PUBG Mobile", "Alice", "₹750", "11 Apr 2025", R.drawable.banner),
        ResultModel("Valorant", "Leo", "₹2000", "10 Apr 2025", R.drawable.banner),
        ResultModel("BGMI", "Raj", "₹500", "09 Apr 2025", R.drawable.banner)
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultAdapter = ResultAdapter(resultList)
        binding.resultRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.resultRecyclerView.adapter = resultAdapter
    }
}