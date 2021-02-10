package net.svishch.android.outerspace.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface PhotoView : MvpView {
    fun init()
    fun setImgSrc(imgSrc: String)
    fun setId(id: String)
    fun setCamera(camera: String)
    fun setRover(rover: String)
}