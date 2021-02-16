package net.svishch.android.outerspace.mvp.model.db.cache

import io.reactivex.rxjava3.core.Single
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos

interface IMarsRoverPhotosCache {
    fun getPhotos(): Single<Photos>
    fun photosUpdate(photos: Single<Photos>)
    fun updatePhoto(photo: Photo)
}