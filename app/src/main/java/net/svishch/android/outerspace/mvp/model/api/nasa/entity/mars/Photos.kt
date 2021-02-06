package net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars

import com.google.gson.annotations.Expose


class Photos {

    @Expose
    private var photos: List<Photo>? = null

    fun getPhotos(): List<Photo>? {
        return photos
    }

    fun setPhotos(photos: List<Photo>?) {
        this.photos = photos
    }
}