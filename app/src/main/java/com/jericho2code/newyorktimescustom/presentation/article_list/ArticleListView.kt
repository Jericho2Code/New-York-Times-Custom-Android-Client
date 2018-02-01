package com.jericho2code.newyorktimescustom.presentation.article_list

import com.arellomobile.mvp.MvpView
import com.jericho2code.newyorktimescustom.model.entities.Article

/**
 * Created by Михаил on 30.01.2018.
 */
interface ArticleListView: MvpView {
    fun displayDoc(docs: List<Article>)
}