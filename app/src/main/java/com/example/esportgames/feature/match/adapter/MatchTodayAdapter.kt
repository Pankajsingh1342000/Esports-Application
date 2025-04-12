package com.example.esportgames.feature.match.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportgames.databinding.ItemTodayMatchBinding
import com.example.esportgames.feature.match.data.MatchTodayModel

class MatchTodayAdapter(private val matches: List<MatchTodayModel>) :
    RecyclerView.Adapter<MatchTodayAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: ItemTodayMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchTodayModel) {
            binding.bannerImage.setImageResource(match.bannerResId)
            binding.gameTitle.text = match.gameTitle
            binding.matchTime.text = "Time: ${match.matchTime}"
            binding.matchType.text = "Type: ${match.matchType}"
            binding.entryFee.text = "Entry: ${match.entryFee}"
            binding.prizePool.text = "Prize: ${match.prizePool}"
            binding.slots.text = "Slots: ${match.slots}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemTodayMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    override fun getItemCount() = matches.size
}