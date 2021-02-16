package net.svishch.android.outerspace.mvp.model

import android.os.Bundle
import io.reactivex.rxjava3.core.Single
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos

interface ModelData {
   fun getMarsPhotos() : Single<Photos>
   fun getBundle(): Bundle
   fun updatePhoto(photo: Photo)
}