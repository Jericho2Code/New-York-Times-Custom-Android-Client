package com.jericho2code.newyorktimescustom.presentation.article_list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jericho2code.newyorktimescustom.R
import com.jericho2code.newyorktimescustom.app.di.DaggerArticleListComponent
import com.jericho2code.newyorktimescustom.model.entities.Article
import kotlinx.android.synthetic.main.fragment_article_list.*
import com.jericho2code.newyorktimescustom.app.di.modules.ContextModule
import com.jericho2code.newyorktimescustom.app.di.modules.RoomModule
import com.jericho2code.newyorktimescustom.openIntentOrShowErrorMessage
import com.jericho2code.newyorktimescustom.shareMessage

class ArticleListFragment : MvpAppCompatFragment(), ArticleListView {

    companion object {
        val CATEGORY = "category"

        fun instanse(category: String): ArticleListFragment {
            val fragment = ArticleListFragment()
            val args = Bundle()
            args.putString(CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var adapter: ArticleAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration

    @InjectPresenter
    lateinit var presenter: ArticleListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerArticleListComponent.builder()
                .contextModule(ContextModule(context))
                .roomModule(RoomModule(this.activity.application))
                .build()
                .inject(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_article_list, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = arguments.getString(CATEGORY)

        adapter = ArticleAdapter(context, object : ArticleAdapter.OnArticleClickListener {
            override fun onShareClick(item: Article) {
                context.shareMessage(item.title + "\n" + item.shortUrl)
            }

            override fun onNoteClick(item: Article) {
            }

            override fun onArticleClick(item: Article) {
                context.openIntentOrShowErrorMessage(item.shortUrl)
            }
        })
        layoutManager = LinearLayoutManager(context)
        dividerItemDecoration = DividerItemDecoration(article_list.context, layoutManager.orientation)
        article_list.layoutManager = layoutManager
        article_list.addItemDecoration(dividerItemDecoration)
        article_list.adapter = adapter
        presenter.loadDocs(category)
    }


    override fun displayDoc(docs: List<Article>) {
        article_progress.visibility = View.GONE
        adapter.items = ArrayList(docs)
        adapter.notifyDataSetChanged()
    }
}
