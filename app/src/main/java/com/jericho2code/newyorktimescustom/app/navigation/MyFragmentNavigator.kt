package ru.kinoplan24.app.navigation

import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.*

abstract class MyFragmentNavigator(val fm: FragmentManager,
                                   val containerId: Int) : Navigator {
    override fun applyCommand(command: Command) {
        when(command) {
            is Forward -> command.run {
                fm.beginTransaction()
                        .replace(containerId, transitionData as Fragment)
                        .addToBackStack(screenKey)
                        .commit()
            }
            is ForwardWithTransition -> command.run {
                (if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    sharedElements.toList()
                else
                    emptyList()).fold(fm.beginTransaction(), { fm, pair -> fm.addSharedElement(pair.second, pair.first) })
                        .replace(containerId, transitionData as Fragment)
                        .addToBackStack(screenKey)
                        .commit()
            }
            is Back -> {
                if(fm.backStackEntryCount > 0) {
                    fm.popBackStackImmediate()
                } else {
                    exit()
                }
            }
            is Replace -> command.run {
                if(fm.backStackEntryCount > 0) {
                    fm.popBackStackImmediate()
                    fm.beginTransaction()
                            .replace(containerId, transitionData as Fragment)
                            .addToBackStack(screenKey)
                            .commit()
                } else {
                    fm.beginTransaction()
                            .replace(containerId, transitionData as Fragment)
                            .commit()
                }
            }
            is BackTo -> command.run {
                fun backToRoot() {
                    if(fm.backStackEntryCount > 0) {
                        // fm.popBackStackImmediate(fm.getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        fm.popBackStackImmediate(fm.getBackStackEntryAt(0).id, /*FragmentManager.POP_BACK_STACK_INCLUSIVE*/0)
                        fm.popBackStack()
                    }
                }
                if(screenKey == null) {
                    backToRoot()
                } else {
                    (0..(fm.backStackEntryCount - 1)).find {
                        screenKey == fm.getBackStackEntryAt(it).name
                    }?.run { fm.popBackStackImmediate(screenKey, 0) } ?: backToRoot()
                }
            }
            // SystemMessage is redundant feature
            else -> throw IllegalStateException("Unimplemented")
        }
    }
    protected abstract fun exit()
}