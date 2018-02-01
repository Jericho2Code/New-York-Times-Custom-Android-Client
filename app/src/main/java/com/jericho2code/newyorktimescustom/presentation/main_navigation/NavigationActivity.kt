package com.jericho2code.newyorktimescustom.presentation.main_navigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatDelegate
import com.arellomobile.mvp.MvpAppCompatActivity
import com.jericho2code.newyorktimescustom.R
import com.jericho2code.newyorktimescustom.app.navigation.HasCicerone
import com.jericho2code.newyorktimescustom.app.navigation.HasRouter
import com.jericho2code.newyorktimescustom.presentation.ArticlePagerFragment
import com.jericho2code.newyorktimescustom.presentation.bookmarklist.BookmarkListFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import ru.kinoplan24.app.navigation.FrontContainer
import ru.kinoplan24.app.navigation.Screens
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.commands.Replace

class NavigationActivity: MvpAppCompatActivity(), HasRouter, HasCicerone {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    val screens = listOf(Screens.ARTICLE_LISTS,
            Screens.BOOKMARKS,
            Screens.SEARCH)

    val cicerone = screens.map { it to Cicerone.create() }.toMap()
    private val tabCicerone = Cicerone.create()

    override fun cicerone(tag: String) = cicerone[tag]!!

    override fun router() = cicerone(index2screen()).router!!

    private fun screen2fragment(screen: String) = when(screen) {
        Screens.ARTICLE_LISTS -> ArticlePagerFragment()
        Screens.BOOKMARKS -> BookmarkListFragment()
        else -> Fragment()
    }

    private fun index2screen(id: Int = navigation.selectedItemId) = when(id) {
        R.id.navigation_home -> Screens.ARTICLE_LISTS
        R.id.navigation_bookmark -> Screens.BOOKMARKS
        R.id.navigation_search -> Screens.SEARCH
        else -> throw IllegalStateException()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val screen = index2screen(item.itemId)
        tabCicerone.router.replaceScreen(screen, screen2fragment(screen))
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        tabCicerone.navigatorHolder.setNavigator({
            when(it) {
                is Replace -> it.run {
                    // - detach each (but not 'screenKey' (selected) fragment) already
                    //   attached tab fragment
                    // - attach or add selected tab fragment
                    (screens - screenKey).map { supportFragmentManager.findFragmentByTag(it) }
                            .filterNotNull()
                            .fold(supportFragmentManager.beginTransaction(), FragmentTransaction::detach)
                            .apply {
                                supportFragmentManager.findFragmentByTag(screenKey)?.let {
                                    attach(it)
                                } ?: add(R.id.content, FrontContainer(), screenKey)
                            }
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commitAllowingStateLoss()
                }
                else -> throw UnsupportedOperationException()
            }
        })
        cicerone.forEach {
            it.value.router.newRootScreen(it.key, screen2fragment(it.key))
        }
        val homeScreen = screens[0]
        tabCicerone.router.replaceScreen(homeScreen, screen2fragment(homeScreen))

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onBackPressed() {
        router().exit()
    }
}