package com.example.esportgames.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportgames.databinding.ItemMatchCardBinding
import com.example.esportgames.feature.home.data.MatchModel

class MatchAdapter(private val matches: List<MatchModel>, private val onItemClick: (MatchModel) -> Unit) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(private val binding: ItemMatchCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchModel) {
            binding.bannerImage.setImageResource(match.bannerResId)
            binding.matchTime.text = match.time
            binding.matchTitle.text = match.title
            binding.prize.text = match.prize
            binding.type.text = match.type
            binding.slots.text = match.slots
            binding.entry.text = match.entryFee
            binding.countdown.text = match.countdown
            binding.organizerName.text = match.organizer

            binding.root.setOnClickListener {
                onItemClick(match)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matches[position])
    }

    override fun getItemCount(): Int = matches.size
}