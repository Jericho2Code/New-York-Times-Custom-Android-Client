package com.jericho2code.newyorktimescustom.presentation.bookmarklist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.jericho2code.newyorktimescustom.model.ArticleRepository
import javax.inject.Inject

@InjectViewState
class BookmarkListPresenter: MvpPresenter<BookmarkListView>() {
    @Inject
    lateinit var repository: ArticleRepository

    fun loadBookmarks() {
        repository.getBoormarks().subscribe(
                {
                    viewState.onBookmarkLoaded(it)
                },
                {
                    viewState.onError()
                }
        )
    }
}