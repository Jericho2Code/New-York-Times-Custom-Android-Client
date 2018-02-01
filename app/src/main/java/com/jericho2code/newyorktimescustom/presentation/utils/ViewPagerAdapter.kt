package com.jericho2code.newyorktimescustom.presentation.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

/**
 * Адаптер для view pagers с вкладками
 *
 * Created by Михаил on 12.12.2017.
 */
class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList = ArrayList<Pair<Fragment, String>>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position].first
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(Pair(fragment, title))
    }

    fun createFragmentList(fragmentList: List<Pair<Fragment, String>>) {
        with(this.fragmentList) {
            clear()
            addAll(fragmentList)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentList[position].second
    }
}