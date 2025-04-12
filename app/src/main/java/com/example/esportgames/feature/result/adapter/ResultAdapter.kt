package com.example.esportgames.feature.result.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportgames.databinding.ItemResultCardBinding
import com.example.esportgames.feature.result.data.ResultModel

class ResultAdapter(private val resultList: List<ResultModel>) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    inner class ResultViewHolder(private val binding: ItemResultCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ResultModel) {
            binding.bannerImage.setImageResource(result.bannerResId)
            binding.gameTitle.text = result.gameTitle
            binding.winnerName.text = "Winner: ${result.winnerName}"
            binding.prize.text = "Prize: ${result.prize}"
            binding.matchDate.text = "Date: ${result.matchDate}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding = ItemResultCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    override fun getItemCount(): Int = resultList.size
}