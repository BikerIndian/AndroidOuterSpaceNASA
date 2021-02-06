package net.svishch.android.outerspace.mvp.model

import io.reactivex.rxjava3.core.Single
import net.svishch.android.outerspace.App
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import net.svishch.android.outerspace.mvp.model.network.AndroidNetworkStatus
import retrofit.ApiHolder
import retrofit.mvp.model.api.nasa.retrofit.INasaMarsPhotos
import net.svishch.android.outerspace.mvp.model.api.nasa.retrofit.RetrofitNasaPhotos

class ModelDataProviders : ModelData {

    private  val dataApi: INasaMarsPhotos = RetrofitNasaPhotos(ApiHolder().api)

    override fun getMarsPhotos(): Single<Photos> {
        val photos = dataApi.getPhotos()
        return photos
    }

    // Работа с данными
    companion object {
        var networkStatus = false
        fun newInstance() = ModelDataProviders().apply {
            App.instance.appComponent.inject(this)
        }

        fun initNetMonitor() {
            AndroidNetworkStatus(App.instance).isOnline().subscribe {
                networkStatus = it
            }
        }
    }

}





