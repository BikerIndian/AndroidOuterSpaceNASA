package net.svishch.android.outerspace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        View.inflate(context, R.layout.fragment_photo, null)

    override fun init() {
        val photo = arguments?.getParcelable<Photo>(PHOTO_ARG) as Photo
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


    override fun backPressed() = presenter.backPressed()
}