package com.jericho2code.newyorktimescustom.presentation.bookmarklist

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.jericho2code.newyorktimescustom.R
import com.jericho2code.newyorktimescustom.model.entities.Bookmark
import kotlinx.android.synthetic.main.fragment_bookmark.*

/**
 * Created by Михаил on 31.01.2018.
 */
class BookmarkListFragment: MvpAppCompatFragment(), BookmarkListView {

    lateinit var adapter: BookmarkAdapter
    lateinit var layoutManager: LinearLayoutManager

    @InjectPresenter
    lateinit var presenter: BookmarkListPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_bookmark, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = ""
        toolbar.setLogo(R.drawable.ic_nyt_logo)
        toolbar.inflateMenu(R.menu.main)

        adapter = BookmarkAdapter(context)
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(bookmark_list.context, layoutManager.orientation)
        bookmark_list.adapter = adapter
        bookmark_list.layoutManager = layoutManager
        bookmark_list.addItemDecoration(dividerItemDecoration)

        presenter.loadBookmarks()
    }

    override fun onBookmarkLoaded(bookmarks: List<Bookmark>) {
        adapter.items = bookmarks
        adapter.notifyDataSetChanged()
        bookmark_list.visibility = View.VISIBLE
        bookmark_progress.visibility = View.GONE
    }
}