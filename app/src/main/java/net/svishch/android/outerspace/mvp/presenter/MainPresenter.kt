package net.svishch.android.outerspace.mvp.presenter

import moxy.MvpPresenter
import net.svishch.android.outerspace.mvp.view.MainView
import net.svishch.android.outerspace.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router : Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(Screens.MarsPhotos())
    }

    fun backClicked() {
        router.exit()
    }

}