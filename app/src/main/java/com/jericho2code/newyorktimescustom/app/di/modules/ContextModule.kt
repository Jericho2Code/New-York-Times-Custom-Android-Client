package com.jericho2code.newyorktimescustom.app.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
open class ContextModule(val context: Context) {
    @Provides
    fun context(): Context = context.applicationContext
}
