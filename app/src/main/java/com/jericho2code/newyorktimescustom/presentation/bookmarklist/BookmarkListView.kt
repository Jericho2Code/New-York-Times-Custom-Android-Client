package com.jericho2code.newyorktimescustom.presentation.bookmarklist

import com.arellomobile.mvp.MvpView
import com.jericho2code.newyorktimescustom.model.entities.Bookmark

/**
 * Created by Михаил on 01.02.2018.
 */
interface BookmarkListView: MvpView {
    fun onBookmarkLoaded(bookmarks: List<Bookmark>)
}