package com.jericho2code.newyorktimescustom.presentation.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jericho2code.newyorktimescustom.R

/**
 * @author Mikhail Panchukov
 */
class SearchFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_search, container, false)
}