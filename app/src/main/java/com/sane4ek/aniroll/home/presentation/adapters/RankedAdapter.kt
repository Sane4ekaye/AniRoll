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
import com.sane4ek.aniroll.databinding.ItemVerticalRvBinding
import com.sane4ek.aniroll.splash.data.model.AnimeEntity

class RankedAdapter(private var rankedList: ArrayList<AnimeEntity>) : RecyclerView.Adapter<RankedAdapter.MyHolder>() {

    inner class MyHolder(private val binding: ItemVerticalRvBinding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        fun bind(model: AnimeEntity) {
            binding.tvName.text = model.title.english
            binding.tvDescriprion.text = model.description
            binding.tvRating.text = (model.meanScore.toDouble() / 10).toString()
            Glide.with(context).load(model.coverImage.large).into(binding.posterAnime)
            Log.i("pizda", "bind: ${model.title.english}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = ItemVerticalRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(view)
    }

//    fun updateList(list: ArrayList<TokenModel>) {
//        assetsList.clear()
//        assetsList.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Log.i("pizda", "SIZE: ${rankedList.size}")
        holder.bind(model = rankedList[position])
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
        return rankedList.size
    }
}