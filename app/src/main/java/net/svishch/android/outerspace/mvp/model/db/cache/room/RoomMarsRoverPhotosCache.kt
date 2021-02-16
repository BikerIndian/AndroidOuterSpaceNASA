package net.svishch.android.outerspace.mvp.model.db.cache.room

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import net.svishch.android.outerspace.mvp.model.db.cache.IMarsRoverPhotosCache
import net.svishch.android.outerspace.mvp.model.db.room.Database
import net.svishch.android.outerspace.mvp.model.db.room.entity.RoomMarsRoverPhotos

class RoomMarsRoverPhotosCache(var db: Database) : IMarsRoverPhotosCache {
    override fun getPhotos(): Single<Photos> {

        return Single.fromCallable {
            Photos(db.marsPhotosDao.getAll()
                .map { roomPhotos ->
                    var photo = Photo(
                        roomPhotos.id,
                        roomPhotos.imgSrc,
                        roomPhotos.earthDate
                    )
                    photo.isFavorites  = roomPhotos.isFavorites
                    photo
                })

        }.subscribeOn(Schedulers.io())
    }

    override fun photosUpdate(photos: Single<Photos>) {
        photos.observeOn(Schedulers.io())
            .subscribe { photos ->
                val roomPhotos = photos.getPhotos()?.map { photo ->
                    var dbPhotos = RoomMarsRoverPhotos(
                        photo.id ?: 0,
                        photo.imgSrc ?: "",
                        photo.earthDate ?: "",
                    )
                    
                    if (db.marsPhotosDao.findById(photo.id) != null) {
                        dbPhotos.isFavorites =  db.marsPhotosDao.findById(photo.id).isFavorites
                    }

                    dbPhotos
                }
                roomPhotos?.let { db.marsPhotosDao.insert(it) }
            }
    }

    override fun updatePhoto(photo: Photo) {
        Single.fromCallable {
            var photoDb = db.marsPhotosDao.findById(photo.id)
            photoDb.imgSrc = photo.imgSrc.toString()
            photoDb.isFavorites = photo.isFavorites
            db.marsPhotosDao.update(photoDb)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun getFavorites(): Single<Photos> {

        return Single.fromCallable {
            Photos(db.marsPhotosDao.getFavorites()
                .map { roomPhotos ->
                  var photo = Photo(
                        roomPhotos.id,
                        roomPhotos.imgSrc,
                        roomPhotos.earthDate
                    )
                    photo.isFavorites  = roomPhotos.isFavorites
                    photo
                })

        }.subscribeOn(Schedulers.io())
    }
}