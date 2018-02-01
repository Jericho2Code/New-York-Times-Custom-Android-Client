package com.jericho2code.newyorktimescustom.model

import com.jericho2code.newyorktimescustom.model.db.BookmarkDao
import com.jericho2code.newyorktimescustom.model.entities.Article
import com.jericho2code.newyorktimescustom.model.entities.Bookmark
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookmarkRepository(val bookmarkDao: BookmarkDao) {

    fun getBoormarks() = bookmarkDao.getBookmarks()
                    .toObservable()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())

    fun addBookmark(article: Article) = Observable.fromCallable {
            bookmarkDao.insert(Bookmark().apply {
                shortUrl = article.shortUrl
                title = article.title
                abstract = article.abstract
                publicationDate = article.publicationDate
                imageUrl = article.multimedia?.find { it.format == "mediumThreeByTwo210" }?.url
            })
        }


    fun deleteBookmark(bookmark: Bookmark) = Observable.fromCallable {
        bookmarkDao.deleteBookmark(bookmark)
    }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())
}