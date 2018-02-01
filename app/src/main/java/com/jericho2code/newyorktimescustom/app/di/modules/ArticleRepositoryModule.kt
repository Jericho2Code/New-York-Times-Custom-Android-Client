package com.jericho2code.newyorktimescustom.app.di.modules

import android.content.Context
import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.model.ArticleRepository
import com.jericho2code.newyorktimescustom.model.db.ArticleDao
import com.jericho2code.newyorktimescustom.model.rest.ArticleService
import dagger.Module
import dagger.Provides

/**
 * Created by Михаил on 18.01.2018.
 */
@Module(includes = [ArticleApiModule::class, ContextModule::class, RoomModule::class])
open class ArticleRepositoryModule {
    @Provides
    @AppScope
    open fun provideRepository(context: Context, api: ArticleService, dao: ArticleDao) = ArticleRepository(api, dao)
}