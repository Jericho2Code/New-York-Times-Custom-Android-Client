package com.jericho2code.newyorktimescustom.app.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.model.db.ApplicationDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by Михаил on 18.01.2018.
 */
@Module(includes = [AppModule::class])
open class RoomModule(application: Application){
    val db: ApplicationDatabase = Room.databaseBuilder(application, ApplicationDatabase::class.java, "nyt-custom-database").build()

    @Provides
    @AppScope
    open fun providesRoomDb() = db

    @Provides
    @AppScope
    open fun providesArticleDao() = db.articleDao()
}