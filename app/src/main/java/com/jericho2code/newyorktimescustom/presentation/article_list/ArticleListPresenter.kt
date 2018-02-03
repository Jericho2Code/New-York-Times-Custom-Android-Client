package com.jericho2code.newyorktimescustom.presentation.article_list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.jericho2code.newyorktimescustom.model.ArticleRepository
import javax.inject.Inject

/**
 * Created by Михаил on 30.01.2018.
 */
@InjectViewState
class ArticleListPresenter: MvpPresenter<ArticleListView>() {

    @Inject
    lateinit var repositoryRepository: ArticleRepository

    fun loadDocs(category: String) {
        repositoryRepository.getArticleByCategory(category).subscribe(
                {
                    viewState.displayDoc(it.sortedByDescending{ it.publicationDate })
                },
                {
                    val ss = "s"
                    ss
                }
        )
    }

}