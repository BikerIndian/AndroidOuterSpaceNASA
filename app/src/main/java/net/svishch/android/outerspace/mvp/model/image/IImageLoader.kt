package net.svishch.android.outerspace.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}