package com.leskov.game_news.views.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leskov.game_news.databinding.ListItemNewsBinding
import com.leskov.game_news.domain.response.NewsResponse
import android.view.View
import com.bumptech.glide.request.RequestOptions
import android.graphics.Color
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.SpannableString


/**
 *  Created by Android Studio on 8/17/2021 6:21 PM
 *  Developer: Sergey Leskov
 */

class NewsAdapter : ListAdapter<NewsResponse, NewsAdapter.NewsViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            ListItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = getItem(position)

        holder.binding.news = item

        val requestOptions = RequestOptions().placeholder(com.leskov.game_news.R.drawable.placeholder)

        if (item.img.isNullOrEmpty()){
            holder.binding.image.visibility = View.GONE
        } else {
            Glide.with(holder.itemView.context)
                .load(item.img)
                .apply(requestOptions)
                .centerCrop()
                .into(holder.binding.image)
        }

        holder.binding.url.setOnClickListener {
            val ss = SpannableString(item.url)
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    holder.itemView.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            ss.setSpan(clickableSpan, 0, holder.binding.url.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            holder.binding.url.text = ss
            holder.binding.url.movementMethod = LinkMovementMethod.getInstance()
            holder.binding.url.highlightColor = Color.TRANSPARENT
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<NewsResponse>() {
            override fun areItemsTheSame(oldItem: NewsResponse, newItem: NewsResponse): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsResponse, newItem: NewsResponse): Boolean =
                oldItem == newItem

        }
    }

    inner class NewsViewHolder(val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)
}