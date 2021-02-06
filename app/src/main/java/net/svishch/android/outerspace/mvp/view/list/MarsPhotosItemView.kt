package net.svishch.android.outerspace.mvp.view.list

interface MarsPhotosItemView : IItemView {
    fun setInfo(text: String)
    fun loadImg(url: String)
}