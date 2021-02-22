package net.svishch.android.outerspace.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.outerspace.mvp.model.ModelData
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.view.PhotoView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PhotoPresenter() : MvpPresenter<PhotoView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var modelData : ModelData

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun update(photo: Photo){
        modelData.updatePhoto(photo)
    }
}