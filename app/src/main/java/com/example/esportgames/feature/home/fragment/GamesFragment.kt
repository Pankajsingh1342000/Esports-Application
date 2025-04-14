package com.example.esportgames.feature.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentGamesBinding
import com.example.esportgames.feature.home.adapter.MatchAdapter
import com.example.esportgames.feature.home.data.MatchModel
import androidx.navigation.fragment.findNavController
import com.example.esportgames.feature.home.fragment.GamesFragmentDirections.Companion.actionGamesFragmentToTournamentDetailFragment

class GamesFragment : Fragment() {
    private lateinit var binding: FragmentGamesBinding
    private lateinit var adapter: MatchAdapter

    val dummyMatches = listOf(
        MatchModel(R.drawable.banner, "8:00pm", "1 VS 1 LONE WOLF", "Rs. 30", "SOLO", "1/2", "20 coins", "00hr 06min 58s", "Hunter ESP"),
        MatchModel(R.drawable.banner, "9:00pm", "Squad Showdown", "Rs. 100", "SQUAD", "3/4", "50 coins", "00hr 25min 12s", "Pro Squad"),
        MatchModel(R.drawable.banner_pubg, "9:00pm", "Squad Showdown", "Rs. 100", "SQUAD", "3/4", "50 coins", "00hr 25min 12s", "Pro Squad"),
        MatchModel(R.drawable.banner, "4:00pm", "1 VS 1 LONE WOLF", "Rs. 90", "SOLO", "1/2", "30 coins", "00hr 06min 58s", "Hunter ESP"),
        MatchModel(R.drawable.banner_valorant, "6:00pm", "Squad Showdown", "Rs. 30", "SQUAD", "4/4", "50 coins", "00hr 06min 58s", "Pro Squad"),
        MatchModel(R.drawable.banner, "3:00pm", "1 VS 1 LONE WOLF", "Rs. 30", "SOLO", "1/2", "20 coins", "00hr 06min 58s", "Hunter ESP"),
        MatchModel(R.drawable.banner_pubg, "7:00pm", "Squad Showdown", "Rs. 60", "SOLO", "1/2", "70 coins", "00hr 06min 58s", "Hunter ESP"),
        MatchModel(R.drawable.banner, "2:00pm", "1 VS 1 LONE WOLF", "Rs. 80", "SQUAD", "2/4", "90 coins", "00hr 06min 58s", "Hunter ESP"),
        MatchModel(R.drawable.banner, "9:00pm", "1 VS 1 LONE WOLF", "Rs. 30", "SOLO", "1/2", "70 coins", "00hr 06min 58s", "Pro Squad"),
        MatchModel(R.drawable.banner_valorant, "1:00pm", "Squad Showdown", "Rs. 70", "SOLO", "1/2", "40 coins", "00hr 06min 58s", "Hunter ESP"),
        MatchModel(R.drawable.banner, "7:00pm", "1 VS 1 LONE WOLF", "Rs. 30", "SQUAD", "3/4", "40 coins", "00hr 06min 58s", "Pro Squad"),
        MatchModel(R.drawable.banner_pubg, "8:00pm", "Squad Showdown", "Rs. 70", "SOLO", "1/2", "60 coins", "00hr 06min 58s", "Pro Squad"),
        MatchModel(R.drawable.banner, "4:00pm", "1 VS 1 LONE WOLF", "Rs. 30", "SOLO", "1/2", "20 coins", "00hr 06min 58s", "Hunter ESP")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MatchAdapter(dummyMatches) { match ->
            val action = actionGamesFragmentToTournamentDetailFragment(
                bannerResId = match.bannerResId,
                time = match.time,
                title = match.title,
                prize = match.prize,
                type = match.type,
                slots = match.slots,
                entryFee = match.entryFee,
                countdown = match.countdown,
                organizer = match.organizer
            )
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
    }
}