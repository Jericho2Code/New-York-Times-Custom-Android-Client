package com.jericho2code.newyorktimescustom.model

import android.util.Log
import com.jericho2code.newyorktimescustom.model.db.ArticleDao
import com.jericho2code.newyorktimescustom.model.entities.Article
import com.jericho2code.newyorktimescustom.model.rest.ArticleService
import com.jericho2code.newyorktimescustom.model.rest.RestConstants.API_KEY
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleRepository @Inject constructor(val articleApi: ArticleService, val articleDao: ArticleDao){

    val params = HashMap<String, String>().apply { put("api-key", API_KEY) }

    fun getArticleForMonth(month: Int, year: Int) =
        articleApi.getArticleForMonth(month, year, params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())

    fun getArticleByCategory(category: String) =
            articleApi.getArticleByCategory(category, params)
                    .map { it.results!! }
                    .toObservable()
                    .doOnNext { it.forEach { it.bookmark = true } }
                    .doOnNext { storeArticlesInDb(category, it) }
                    .onErrorReturn{ loadArticlesFromDbByCategory(category).blockingSingle() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())

    fun getBoormarks() = Observable.fromCallable { articleDao.getBookmarkedArticles() }
            .map { it.blockingGet() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.computation())

    private fun storeArticlesInDb(category: String, articles: List<Article>?) {
        if(articles != null) {
            articles.forEach {
                it.category = category
            }
            Observable.fromCallable { articleDao.insertAll(articles) }
                    .subscribeOn(Schedulers.computation())
                    .subscribe(
                            {},
                            {
                                Log.e("ArticleRepository", "storeArticlesInDb - error", it)
                            }
                    )

        }
    }

    private fun loadArticlesFromDbByCategory(category: String) =
        articleDao.getArticleByCategory(category).toObservable().onErrorReturn { emptyList() }

}