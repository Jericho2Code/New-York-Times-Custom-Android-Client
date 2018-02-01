package com.jericho2code.newyorktimescustom.model.db

import android.arch.persistence.room.*
import com.jericho2code.newyorktimescustom.model.entities.Bookmark
import io.reactivex.Single

/**
 * Created by Михаил on 01.02.2018.
 */

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Bookmark)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Bookmark>?)

    @Query("SELECT * FROM bookmarks")
    fun getBookmarks(): Single<List<Bookmark>>

    @Query("SELECT * FROM bookmarks WHERE id = :id")
    fun getBookmark(id: Long): Single<Bookmark>

    @Delete
    fun deleteBookmark(bookmark: Bookmark)
}