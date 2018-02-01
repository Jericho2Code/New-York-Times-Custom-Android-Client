package ru.kinoplan24.app.navigation

import android.view.View
import ru.terrakok.cicerone.commands.Command

class ForwardWithTransition(val screenKey: String, val transitionData: Any, val sharedElements: Map<String, View>) : Command