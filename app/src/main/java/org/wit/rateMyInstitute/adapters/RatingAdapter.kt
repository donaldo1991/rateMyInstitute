package org.wit.rateMyInstitute.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.wit.rateMyInstitute.databinding.CardRatingBinding
import org.wit.rateMyInstitute.models.RatingModel

interface RatingListener {
    fun onRatingClick(rating: RatingModel)
}

class RatingAdapter constructor(private var ratings: List<RatingModel>,
                                   private val listener: RatingListener) :
        RecyclerView.Adapter<RatingAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRatingBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val rating = ratings[holder.adapterPosition]
        holder.bind(rating, listener)
    }

    override fun getItemCount(): Int = ratings.size

    class MainHolder(private val binding : CardRatingBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(rating: RatingModel, listener: RatingListener) {
            binding.ratingTitle.text = rating.title
            binding.description.text = rating.description
            Picasso.get().load(rating.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onRatingClick(rating) }
        }
    }
}
