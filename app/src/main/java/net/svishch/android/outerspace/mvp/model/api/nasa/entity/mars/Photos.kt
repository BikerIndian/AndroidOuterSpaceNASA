package net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class Photos(
    @Expose private var photos: List<Photo>? = null
) : Parcelable {


    fun getPhotos(): List<Photo>? {
        return photos
    }

    fun setPhotos(photos: List<Photo>?) {
        this.photos = photos
    }
}