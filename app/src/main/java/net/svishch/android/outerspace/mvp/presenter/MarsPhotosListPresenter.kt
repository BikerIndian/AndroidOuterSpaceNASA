package net.svishch.android.outerspace.mvp.presenter

import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.presenter.list.IMarsPhotosListPresenter
import net.svishch.android.outerspace.mvp.view.list.MarsPhotosItemView

class MarsPhotosListPresenter : IMarsPhotosListPresenter {
    val photos = mutableListOf<Photo>()

    override var itemClickListener: ((MarsPhotosItemView) -> Unit)? = null

    override fun getCount() = photos.size

    override fun bindView(view: MarsPhotosItemView) {
        val photo = photos[view.pos]

        val info: String = " ${photo.earthDate}"

        view.setInfo(info)
        photo.imgSrc?.let { view.loadImg(it) }              // проверка на null так как работат с сетью
        view.favoritesImgOn(photo.isFavorites)
    }

    fun update(photosIn: List<Photo>?) {
        photos.clear()
        photosIn?.let { photos.addAll(it) }
    }
}