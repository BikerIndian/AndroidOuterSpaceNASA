package net.svishch.android.outerspace.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.outerspace.mvp.view.PhotoView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PhotoPresenter() : MvpPresenter<PhotoView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}