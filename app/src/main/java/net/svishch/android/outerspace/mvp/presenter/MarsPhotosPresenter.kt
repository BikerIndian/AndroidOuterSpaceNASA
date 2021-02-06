package net.svishch.android.outerspace.mvp.presenter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import net.svishch.android.outerspace.mvp.model.ModelData
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.presenter.list.IMarsPhotosListPresenter
import net.svishch.android.outerspace.mvp.view.MarsPhotosView
import net.svishch.android.outerspace.mvp.view.list.MarsPhotosItemView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MarsPhotosPresenter(private val mainThreadScheduler: Scheduler,private val modelData: ModelData) : MvpPresenter<MarsPhotosView>() {

  //  @Inject
  //  lateinit var modelData: IGithubUsersRepo
    @Inject
    lateinit var router: Router


     val marsPhotosListPresenter = MarsPhotosListPresenter()
/**/


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
/*
        // Выбор пользователя
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserRepoScreen(usersListPresenter.users[itemView.pos]))
        }

 */
    }

    private fun loadData() {

        modelData.getMarsPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn (mainThreadScheduler)
                .subscribe({ marsPhotos ->
                    marsPhotosListPresenter.update(marsPhotos.getPhotos())
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }


    class MarsPhotosListPresenter : IMarsPhotosListPresenter {
        val photos = mutableListOf<Photo>()

        override var itemClickListener: ((MarsPhotosItemView) -> Unit)? = null

        override fun getCount() = photos.size

        override fun bindView(view: MarsPhotosItemView) {
            val photo = photos[view.pos]

            val info:String = "Дата: ${photo.earthDate}"

            view.setInfo(info)
            photo.imgSrc?.let { view.loadImg(it) }              // проверка на null так как работат с сетью
        }

        fun update(photosIn: List<Photo>?) {
            photos.clear()
            photosIn?.let { photos.addAll(it) }
        }
    }

}

