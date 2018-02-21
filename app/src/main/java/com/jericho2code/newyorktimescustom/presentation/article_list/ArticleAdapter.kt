package com.jericho2code.newyorktimescustom.presentation.article_list

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
import com.jericho2code.newyorktimescustom.toDateWithTimeString
import com.jericho2code.newyorktimescustom.toStringWithTimeZone
import org.threeten.bp.ZonedDateTime
import java.util.*

/**
 * Created by Михаил on 29.01.2018.
 */
class ArticleAdapter(val context: Context, val changeListener: OnArticleClickListener): RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    var items = ArrayList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleHolder
            = ArticleHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_article, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArticleHolder?, position: Int) {
        holder?.bind(items[position], changeListener)
    }


    class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.txt_article_headline)
        private val snippetView: TextView = itemView.findViewById(R.id.txt_article_snippet)
        private val dateView: TextView = itemView.findViewById(R.id.txt_article_date)
        private val articleImage: ImageView = itemView.findViewById(R.id.img_article_poster)
        private val shareImage: ImageView = itemView.findViewById(R.id.img_share)

        fun bind(item: Article, listener: OnArticleClickListener) {
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

            shareImage.setOnClickListener { listener.onShareClick(item) }
            itemView.setOnClickListener { listener.onArticleClick(item)}
        }
    }

    interface OnArticleClickListener {
        fun onShareClick(item: Article)
        fun onNoteClick(item: Article)
        fun onArticleClick(item: Article)
    }

}