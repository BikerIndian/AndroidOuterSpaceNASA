package net.svishch.android.outerspace.mvp.model.api.nasa

import io.reactivex.rxjava3.core.Single
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET
    fun getPhotos(@Url url: String): Single<Photos>
}