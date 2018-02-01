package com.jericho2code.newyorktimescustom.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jericho2code.newyorktimescustom.R
import com.jericho2code.newyorktimescustom.presentation.article_list.ArticleListFragment
import com.jericho2code.newyorktimescustom.presentation.utils.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_article_pager.*

/**
 * Created by Михаил on 30.01.2018.
 */
class ArticlePagerFragment: Fragment() {

    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(ArticleListFragment.instanse("world"), "World")
        adapter.addFragment(ArticleListFragment.instanse("opinion"), "Opinion")
        adapter.addFragment(ArticleListFragment.instanse("movies"), "Movies")
        adapter.addFragment(ArticleListFragment.instanse("health"), "Health")
        adapter.addFragment(ArticleListFragment.instanse("sports"), "Sports")
        adapter.addFragment(ArticleListFragment.instanse("technology"), "Technology")
        adapter.addFragment(ArticleListFragment.instanse("business"), "Business day")
        adapter.addFragment(ArticleListFragment.instanse("arts"), "Arts")
        adapter.addFragment(ArticleListFragment.instanse("magazine"), "Magazine")
        adapter.addFragment(ArticleListFragment.instanse("food"), "Food")
        adapter.addFragment(ArticleListFragment.instanse("travel"), "Travel")
        adapter.addFragment(ArticleListFragment.instanse("automobiles"), "Automobiles")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_article_pager, container, false)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = ""
        toolbar.setLogo(R.drawable.ic_nyt_logo)
        toolbar.inflateMenu(R.menu.main)

        view_pager.adapter = adapter

        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        } )

        tab_layout?.setupWithViewPager(view_pager)

        tab_layout.visibility = View.VISIBLE
        view_pager.visibility = View.VISIBLE
        article_list_progress.visibility = View.GONE
    }
}