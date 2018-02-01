package com.jericho2code.newyorktimescustom.model

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
                    .doOnNext { storeArticlesInDb(it) }
                    .onErrorReturn{ loadArticlesFromDbByCategory(category).blockingSingle() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.computation())

    private fun storeArticlesInDb(articles: List<Article>?) {
        if(articles != null) {
            Observable.fromCallable { articleDao.insertAll(articles) }
                    .subscribeOn(Schedulers.computation())
                    .subscribe(
                            {},
                            {
                                val ss = "s"
                                ss
                            }
                    )

        }
    }

    private fun loadArticlesFromDbByCategory(category: String) =
        articleDao.getArticleByCategory(category.capitalize()).toObservable().onErrorReturn { emptyList() }

}