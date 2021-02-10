package net.svishch.android.outerspace.mvp.model

import io.reactivex.rxjava3.core.Single
import net.svishch.android.outerspace.App
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photos
import net.svishch.android.outerspace.mvp.model.network.AndroidNetworkStatus
import retrofit.ApiHolder
import retrofit.mvp.model.api.nasa.retrofit.INasaMarsPhotos
import net.svishch.android.outerspace.mvp.model.api.nasa.retrofit.RetrofitNasaPhotos
import net.svishch.android.outerspace.mvp.model.db.DataDb
import javax.inject.Inject

class ModelDataProviders : ModelData {
    // Работа с данными
    @Inject
    lateinit var dataDb: DataDb

    private val dataApi: INasaMarsPhotos = RetrofitNasaPhotos(ApiHolder().api)

    override fun getMarsPhotos(): Single<Photos> {


        if (networkStatus) {
            val photos = dataApi.getPhotos()
            dataDb.photosUpdate(photos)
            return photos
        } else {
            return dataDb.getPhotos()
        }

    }


    companion object {
        var networkStatus = false
        fun newInstance() = ModelDataProviders().apply {
            App.instance.appComponent.inject(this)
            initNetMonitor()
        }

        fun initNetMonitor() {
            AndroidNetworkStatus(App.instance).isOnline().subscribe {
                networkStatus = it
            }
        }
    }

}





