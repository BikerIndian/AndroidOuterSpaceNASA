package net.svishch.android.outerspace.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MarsPhotosView : MvpView {
    fun init()
    fun updateList()
}