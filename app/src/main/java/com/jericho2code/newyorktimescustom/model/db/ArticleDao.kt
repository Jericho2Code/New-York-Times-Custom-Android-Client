package com.jericho2code.newyorktimescustom.model.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.jericho2code.newyorktimescustom.model.entities.Article
import io.reactivex.Single

/**
 * Created by Михаил on 31.01.2018.
 */

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Article>?)

    @Query("SELECT * FROM articles")
    fun getArticles(): Single<List<Article>>

    @Query("SELECT * FROM articles WHERE shortUrl = :id")
    fun getArticle(id: String): Single<Article>

    @Query("SELECT * FROM articles WHERE category = :category")
    fun getArticleByCategory(category: String): Single<List<Article>>

    @Query("SELECT * FROM articles WHERE bookmark = 1")
    fun getBookmarkedArticles(): Single<List<Article>>
}