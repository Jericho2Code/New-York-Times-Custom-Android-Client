package com.jericho2code.newyorktimescustom.presentation.article_list

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import java.util.ArrayList

/**
 * Created by Михаил on 29.01.2018.
 */
class ArticleAdapter(val context: Context): RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    var items = ArrayList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleHolder
            = ArticleHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_article, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArticleHolder?, position: Int) {
        holder?.bind(items[position], context)
    }


    class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.txt_article_headline)
        private val snippetView: TextView = itemView.findViewById(R.id.txt_article_snippet)
        private val dateView: TextView = itemView.findViewById(R.id.txt_article_date)
        private val articleImage: ImageView = itemView.findViewById(R.id.img_article_poster)

        fun bind(item: Article, context: Context) {
            titleView.text = item.title
            snippetView.text = item.abstract
            dateView.text = item.publicationDate?.toDateWithTimeString()
            val imageUrl = item.multimedia?.find { it.format == "mediumThreeByTwo210" }?.url

            if (imageUrl == null) {
                articleImage.visibility = View.GONE
            } else {
                GlideApp.with(articleImage)
                        .load(imageUrl)
                        .into(articleImage)
                articleImage.visibility = View.VISIBLE
            }

            itemView.setOnClickListener { context.openIntentOrShowErrorMessage(item.url!!) }
        }
    }

    interface OnArticleClickListener {
        fun onShareClick(url: String)
        fun onNoteClick()
        fun onArticleClick()
    }

}