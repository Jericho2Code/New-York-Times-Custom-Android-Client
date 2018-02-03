package com.jericho2code.newyorktimescustom.app.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.model.rest.GsonString2LocalDateTime
import com.jericho2code.newyorktimescustom.model.rest.RestConstants
import dagger.Module
import dagger.Provides
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class RetrofitModule {
    @Provides
    @AppScope
    open fun retrofit(gson: Gson) = Retrofit.Builder()
            .baseUrl(RestConstants.API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @AppScope
    open fun gson(): Gson  = GsonBuilder()
                .registerTypeAdapter(ZonedDateTime::class.java, GsonString2LocalDateTime())
                .create()

}

