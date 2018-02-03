package com.jericho2code.newyorktimescustom.app.di

import com.jericho2code.newyorktimescustom.app.di.modules.AppModule
import com.jericho2code.newyorktimescustom.app.di.modules.ArticleRepositoryModule
import com.jericho2code.newyorktimescustom.model.AppScope
import com.jericho2code.newyorktimescustom.presentation.article_list.ArticleListPresenter
import com.jericho2code.newyorktimescustom.presentation.bookmarklist.BookmarkListPresenter
import dagger.Component

/**
 * Created by Михаил on 18.01.2018.
 */
@AppScope
@Component(modules = [AppModule::class, ArticleRepositoryModule::class])
interface ArticleListComponent {
    fun inject(presenter: ArticleListPresenter)
    fun inject(presenter: BookmarkListPresenter)
}