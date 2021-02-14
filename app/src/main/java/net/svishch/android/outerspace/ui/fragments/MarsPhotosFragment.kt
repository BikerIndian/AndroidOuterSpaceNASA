package net.svishch.android.outerspace.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_mars_photos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import net.svishch.android.outerspace.App
import net.svishch.android.outerspace.R
import net.svishch.android.outerspace.mvp.model.ModelDataProviders
import net.svishch.android.outerspace.mvp.model.image.GlideImageLoader
import net.svishch.android.outerspace.mvp.presenter.MarsPhotosPresenter
import net.svishch.android.outerspace.mvp.view.MarsPhotosView
import net.svishch.android.outerspace.ui.BackButtonListener
import net.svishch.android.outerspace.ui.adapter.MarsPhotosRVAdapter


class MarsPhotosFragment() : MvpAppCompatFragment(), MarsPhotosView, BackButtonListener {

    var KEY_RECYCLER_PHOTOS = "net.svishch.android.outerspace.ui.fragments.Recycler"

    companion object {
        fun newInstance() = MarsPhotosFragment()
    }

    val presenter: MarsPhotosPresenter by moxyPresenter {
        MarsPhotosPresenter(
            AndroidSchedulers.mainThread(),
            ModelDataProviders.newInstance()
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: MarsPhotosRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        View.inflate(context, R.layout.fragment_mars_photos, null)

    override fun init() {
        rv_mars_photos.layoutManager = LinearLayoutManager(context)
        adapter = MarsPhotosRVAdapter(presenter.marsPhotosListPresenter, GlideImageLoader())
        rv_mars_photos.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        presenter.getBundle()
            .putParcelable(KEY_RECYCLER_PHOTOS, rv_mars_photos.layoutManager?.onSaveInstanceState())
        super.onPause()
    }

    override fun updateList() {
        var marsPhotosManagerState: Parcelable? =
            presenter.getBundle().getParcelable(KEY_RECYCLER_PHOTOS)
        adapter?.notifyDataSetChanged()
        rv_mars_photos.getLayoutManager()?.onRestoreInstanceState(marsPhotosManagerState)
    }

    override fun backPressed() = presenter.backPressed()
}