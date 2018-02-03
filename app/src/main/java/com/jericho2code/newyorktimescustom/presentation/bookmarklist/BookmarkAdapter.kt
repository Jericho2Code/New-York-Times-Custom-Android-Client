package com.jericho2code.newyorktimescustom.presentation.bookmarklist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jericho2code.newyorktimescustom.GlideApp
import com.jericho2code.newyorktimescustom.R
import com.jericho2code.newyorktimescustom.model.entities.Article
import com.jericho2code.newyorktimescustom.openIntentOrShowErrorMessage
import com.jericho2code.newyorktimescustom.toDateWithTimeString

/**
 * Created by Михаил on 01.02.2018.
 */
class BookmarkAdapter(val context: Context) : RecyclerView.Adapter<BookmarkAdapter.BookmarkHolder>() {
    var items: List<Article>? = null

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        val item = items?.get(position)
        holder.bind(item!!, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_bookmark, parent, false)
        return BookmarkHolder(view)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class BookmarkHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.txt_article_headline)
        private val snippetView: TextView = itemView.findViewById(R.id.txt_article_snippet)
        private val dateView: TextView = itemView.findViewById(R.id.txt_article_date)
        private val articleImage: ImageView = itemView.findViewById(R.id.img_article_poster)
        private val shareImage: ImageView = itemView.findViewById(R.id.img_share)

        fun bind(item: Article, context: Context) {
            titleView.text = item.title
            snippetView.text = item.abstract
            dateView.text = item.publicationDate!!.toDateWithTimeString()
            val imageUrl  = item.multimedia?.find { it.format == "mediumThreeByTwo210" }?.url

            GlideApp.with(articleImage)
                    .load(imageUrl)
                    .into(articleImage)

            itemView.setOnClickListener { context.openIntentOrShowErrorMessage(item.shortUrl) }
        }
    }
}