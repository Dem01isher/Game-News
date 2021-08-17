package com.leskov.game_news.views.news

import android.provider.Settings.Global.getString
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leskov.game_news.R
import com.leskov.game_news.databinding.ListItemNewsBinding
import com.leskov.game_news.domain.response.NewsResponse

/**
 *  Created by Android Studio on 8/17/2021 6:21 PM
 *  Developer: Sergey Leskov
 */

class NewsAdapter : ListAdapter<NewsResponse, NewsAdapter.NewsViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = getItem(holder.adapterPosition)

        holder.binding.news = item

        holder.binding.url.text = "${item.url} - ${item.time}"

        Glide.with(holder.itemView.context)
            .load(item.img)
            .centerCrop()
            .into(holder.binding.image)



    }

    companion object{
        val callback = object : DiffUtil.ItemCallback<NewsResponse>(){
            override fun areItemsTheSame(oldItem: NewsResponse, newItem: NewsResponse): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsResponse, newItem: NewsResponse): Boolean =
                oldItem == newItem

        }
    }

    inner class NewsViewHolder(val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}