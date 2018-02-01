package com.jericho2code.newyorktimescustom.app.di

import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.presentation.article_list.ArticleListPresenter
import dagger.Component
import com.jericho2code.newyorktimescustom.app.di.modules.AppModule
import com.jericho2code.newyorktimescustom.app.di.modules.ArticleRepositoryModule

/**
 * Created by Михаил on 18.01.2018.
 */
@AppScope
@Component(modules = [AppModule::class, ArticleRepositoryModule::class])
interface ArticleListComponent {
    fun inject(presenter: ArticleListPresenter)
}