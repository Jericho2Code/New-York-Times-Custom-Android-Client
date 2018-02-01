package com.jericho2code.newyorktimescustom.model.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.jericho2code.newyorktimescustom.model.db.converters.DateConverter
import com.jericho2code.newyorktimescustom.model.db.converters.MultimediaListConverter
import com.jericho2code.newyorktimescustom.model.db.converters.StringListConverter
import com.jericho2code.newyorktimescustom.model.entities.Article

/**
 * Created by Михаил on 31.01.2018.
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(StringListConverter::class, MultimediaListConverter::class, DateConverter::class)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}