package org.wit.rateMyInstitute.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.rateMyInstitute.R
import org.wit.rateMyInstitute.databinding.CardRatingBinding
import org.wit.rateMyInstitute.models.RatingModel

interface RatingClickListener {
    fun onRatingClick(rating: RatingModel)
}

class RatingAdapter constructor(private var ratings: ArrayList<RatingModel>,
                                  private val listener: RatingClickListener)
    : RecyclerView.Adapter<RatingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRatingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val rating = ratings[holder.adapterPosition]
        holder.bind(rating,listener)
    }

    fun removeAt(position: Int) {
        ratings.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int = ratings.size

    inner class MainHolder(val binding : CardRatingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(rating: RatingModel, listener: RatingClickListener) {
            binding.root.tag = rating
            binding.rating = rating
            binding.imageIcon.setImageResource(R.mipmap.ic_launcher_round)
            binding.root.setOnClickListener { listener.onRatingClick(rating) }
            binding.executePendingBindings()
        }
    }
}