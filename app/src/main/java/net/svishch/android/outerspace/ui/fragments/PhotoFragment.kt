package net.svishch.android.outerspace.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_photo.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import net.svishch.android.outerspace.App
import net.svishch.android.outerspace.R
import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.mvp.model.image.GlideImageLoader
import net.svishch.android.outerspace.mvp.presenter.PhotoPresenter
import net.svishch.android.outerspace.mvp.view.PhotoView
import net.svishch.android.outerspace.ui.BackButtonListener


class PhotoFragment() : MvpAppCompatFragment(), PhotoView, BackButtonListener {

    companion object {
        private const val PHOTO_ARG = "photo"

        fun newInstance(photo: Photo) = PhotoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PHOTO_ARG, photo)
            }
        }
    }


    val presenter: PhotoPresenter by moxyPresenter {
        PhotoPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var actionBar: ActionBar? = null
    private lateinit var photo: Photo
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        View.inflate(context, R.layout.fragment_photo, null)

    override fun init() {
        setHasOptionsMenu(true)
        addBtnBackInActionBar() // Кнопка назад
        photo = arguments?.getParcelable<Photo>(PHOTO_ARG) as Photo
        photo.imgSrc?.let { setImgSrc(it) }
        setId(photo.id.toString())
        photo.camera?.fullName?.let { setCamera(it) }
        photo.rover?.name?.let { setRover(it) }
    }

    override fun setImgSrc(imgSrc: String) {
        GlideImageLoader().loadInto(imgSrc, iv_photo_imgSrc)

    }

    override fun setId(id: String) {
        tv_photo_id_val.text = id
    }

    override fun setCamera(camera: String) {
        tv_photo_camera_val.text = camera
    }

    override fun setRover(rover: String) {
        tv_photo_rover_name_val.text = rover
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.photo_menu, menu)
        this.menu = menu
        setIconFavorites()
    }

    override fun backPressed(): Boolean {
        delBtnBackInActionBar()
        return presenter.backPressed()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when (item.itemId) {

            android.R.id.home -> {
                backPressed()
                true
            }

            R.id.menu_add -> {

                if (photo.isFavorites) {
                    photo.isFavorites = false
                    setIconFavorites()
                } else {
                    photo.isFavorites = true
                    setIconFavorites()
                }

                presenter.update(photo)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Меняем иконку избранного
    private fun setIconFavorites() {
        var item = menu.findItem(R.id.menu_add)

        if (photo.isFavorites) {
            item.setIcon(R.drawable.ic_baseline_check_24)
        } else {
            item.setIcon(android.R.drawable.ic_menu_add)
        }
    }

    private fun addBtnBackInActionBar() {
        if(activity is AppCompatActivity){
            actionBar = (activity as AppCompatActivity).supportActionBar!!
        }
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun delBtnBackInActionBar() {
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

}