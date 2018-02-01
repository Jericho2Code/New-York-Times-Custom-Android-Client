package com.jericho2code.newyorktimescustom.app.di.modules

import com.google.gson.*
import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.model.rest.GsonString2DateSerialization
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.jericho2code.newyorktimescustom.model.rest.RestConstants
import java.util.*

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
                .registerTypeAdapter(Date::class.java, GsonString2DateSerialization())
                .create()

}

