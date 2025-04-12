package com.example.esportgames.feature.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentGamesBinding
import com.example.esportgames.databinding.FragmentTournamentDetailBinding

class TournamentDetailFragment : Fragment() {
    private lateinit var binding: FragmentTournamentDetailBinding
    private val args by navArgs<TournamentDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTournamentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bannerImage.setImageResource(args.bannerResId)
        binding.matchTime.text = args.time
        binding.matchTitle.text = args.title
        binding.prize.text = args.prize
        binding.type.text = args.type
        binding.slots.text = args.slots
        binding.entry.text = args.entryFee
        binding.countdown.text = args.countdown
        binding.organizerName.text = args.organizer
    }
}