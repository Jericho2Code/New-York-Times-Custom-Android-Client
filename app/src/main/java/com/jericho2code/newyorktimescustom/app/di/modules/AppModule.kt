package com.jericho2code.newyorktimescustom.app.di.modules

import android.app.Application
import com.jericho2code.newyorktimescustom.model.AppScope
import dagger.Module
import dagger.Provides

/**
 * Created by Михаил on 18.01.2018.
 */
@Module
open class AppModule(@get:[Provides AppScope]  val application: Application)