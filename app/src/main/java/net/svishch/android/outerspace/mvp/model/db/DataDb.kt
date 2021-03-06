package net.svishch.android.outerspace.mvp.model.db

import io.reactivex.rxjava3.core.Single
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import net.svishch.android.outerspace.mvp.model.db.cache.IMarsRoverPhotosCache
import net.svishch.android.outerspace.mvp.model.db.room.Database
import net.svishch.android.outerspace.mvp.model.db.cache.room.RoomMarsRoverPhotosCache

// Работа с базой данных
class DataDb(db: Database) {

    private var photosCache: IMarsRoverPhotosCache = RoomMarsRoverPhotosCache(db)

    fun getPhotos(): Single<Photos> = photosCache.getPhotos()
    fun photosUpdate(photos: Single<Photos>) = photosCache.photosUpdate(photos)
    fun updatePhoto(photo: Photo) {
        photosCache.updatePhoto(photo)
    }

    fun getFavorites(): Single<Photos>  = photosCache.getFavorites()

}
