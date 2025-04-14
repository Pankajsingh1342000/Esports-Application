package com.example.esportgames.feature.match.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentMatchBinding
import com.example.esportgames.feature.match.adapter.MatchTodayAdapter
import com.example.esportgames.feature.match.data.MatchTodayModel

class MatchFragment : Fragment() {
    private lateinit var binding: FragmentMatchBinding
    private lateinit var matchAdapter: MatchTodayAdapter
    private val matchList = listOf(
        MatchTodayModel("Free Fire", "4:00 PM", "Solo", "₹20", "₹1000", "45/100", R.drawable.banner),
        MatchTodayModel("BGMI", "6:00 PM", "Duo", "₹30", "₹1500", "60/100", R.drawable.banner_pubg),
        MatchTodayModel("Valorant", "8:00 PM", "Squad", "₹50", "₹2000", "30/50", R.drawable.banner_valorant),
        MatchTodayModel("Free Fire", "4:00 PM", "Solo", "₹20", "₹1000", "45/100", R.drawable.banner),
        MatchTodayModel("BGMI", "6:00 PM", "Duo", "₹30", "₹1500", "60/100", R.drawable.banner_pubg),
        MatchTodayModel("Valorant", "8:00 PM", "Squad", "₹50", "₹2000", "30/50", R.drawable.banner_valorant),
        MatchTodayModel("Free Fire", "4:00 PM", "Solo", "₹20", "₹1000", "45/100", R.drawable.banner),
        MatchTodayModel("BGMI", "6:00 PM", "Duo", "₹30", "₹1500", "60/100", R.drawable.banner_pubg),
        MatchTodayModel("Valorant", "8:00 PM", "Squad", "₹50", "₹2000", "30/50", R.drawable.banner_valorant)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        matchAdapter = MatchTodayAdapter(matchList)
        binding.matchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.matchRecyclerView.adapter = matchAdapter
    }
}