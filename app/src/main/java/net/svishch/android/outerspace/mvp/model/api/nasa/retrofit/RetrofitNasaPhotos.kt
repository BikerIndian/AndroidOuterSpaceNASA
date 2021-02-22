package net.svishch.android.outerspace.mvp.model.api.nasa.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svishch.android.outerspace.BuildConfig
import net.svishch.android.outerspace.mvp.model.api.nasa.IDataSource
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import retrofit.mvp.model.api.nasa.retrofit.INasaMarsPhotos

class RetrofitNasaPhotos(val api: IDataSource) : INasaMarsPhotos {
    companion object {
        val API_KEY: String = BuildConfig.API_KEY
        var url : String = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=3030&api_key=$API_KEY"
    }
    override fun getPhotos(): Single<Photos>  = api.getPhotos(url).subscribeOn(Schedulers.io())
    override fun getPhotos(urlRepo: String): Single<Photos>  = api.getPhotos(urlRepo).subscribeOn(Schedulers.io())
}