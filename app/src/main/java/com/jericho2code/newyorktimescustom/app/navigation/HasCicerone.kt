package com.jericho2code.newyorktimescustom.app.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

/**
 * Created by Михаил on 31.01.2018.
 */
interface HasCicerone {
    fun cicerone(tag: String): Cicerone<Router>
}