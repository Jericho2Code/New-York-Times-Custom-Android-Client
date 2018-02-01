package ru.kinoplan24.app.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.jericho2code.newyorktimescustom.R
import com.jericho2code.newyorktimescustom.app.navigation.HasCicerone

internal class FrontContainer : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            FrameLayout(container!!.context).apply {
                id = R.id.content
            }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cicerone().navigatorHolder.setNavigator(object: MyFragmentNavigator(childFragmentManager, R.id.content) {
            override fun exit() {
                activity.finish()
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        cicerone().navigatorHolder.removeNavigator()
    }
    // requesting cicerone by tag instead of tab index just because sometimes
    // we need to request cicerone at inactive tab
    private fun cicerone() = (activity as HasCicerone).cicerone(tag)
}