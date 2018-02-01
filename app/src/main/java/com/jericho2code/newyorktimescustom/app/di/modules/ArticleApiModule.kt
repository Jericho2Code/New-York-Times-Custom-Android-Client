package com.jericho2code.newyorktimescustom.app.di.modules

import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.model.rest.ArticleService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Михаил on 18.01.2018.
 */
@Module(includes = [(RetrofitModule::class)])
open class ArticleApiModule {
    @Provides
    @AppScope
    open fun messageApi(retrofit: Retrofit) = retrofit.create(ArticleService::class.java)
}