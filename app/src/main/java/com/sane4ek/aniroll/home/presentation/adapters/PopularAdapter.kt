package com.sane4ek.aniroll.home.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.ItemHorizontalRvBinding
import com.sane4ek.aniroll.splash.data.model.AnimeEntity

class PopularAdapter(private var popularList: ArrayList<AnimeEntity>,
                     private val onClickListener: (AnimeEntity) -> Unit) : RecyclerView.Adapter<PopularAdapter.MyHolder>() {

    inner class MyHolder(private val binding: ItemHorizontalRvBinding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        fun bind(model: AnimeEntity) {
            binding.tvName.text = model.title.english
            binding.tvDescriprion.text = model.description
            binding.tvRating.text = (model.meanScore.toDouble() / 10).toString()

            Glide.with(context).load(model.coverImage.large).into(binding.posterAnime)

            binding.layout.setOnClickListener {
                onClickListener(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = ItemHorizontalRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(view)
    }

//    fun updateList(list: ArrayList<TokenModel>) {
//        assetsList.clear()
//        assetsList.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(model = popularList[position])
    }

//    private fun removeItem(position: Int) {
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, assetsList.size)
//        assetsList.removeAt(position)
//    }

    // очистить кэщ картинок в списке
    override fun onViewRecycled(holder: MyHolder) {
        super.onViewRecycled(holder)
//        holder.logo.setImageDrawable(null)
//        Glide.with(context).clear(holder.logo)
    }

    override fun getItemCount(): Int {
        return popularList.size
    }
}