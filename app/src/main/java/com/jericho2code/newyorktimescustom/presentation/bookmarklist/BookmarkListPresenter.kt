package com.jericho2code.newyorktimescustom.presentation.bookmarklist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class BookmarkListPresenter: MvpPresenter<BookmarkListView>() {
    fun loadBookmarks() {
        viewState.onBookmarkLoaded(emptyList())
    }
}