package net.svishch.android.outerspace.ui.fragments

import android.os.Bundle
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
import net.svishch.android.outerspace.mvp.presenter.MarsPhotosPresenter
import net.svishch.android.outerspace.mvp.view.MarsPhotosView
import net.svishch.android.outerspace.ui.BackButtonListener
import net.svishch.android.outerspace.ui.adapter.MarsPhotosRVAdapter
import net.svishch.android.outerspace.ui.image.GlideImageLoader


class MarsPhotosFragment() : MvpAppCompatFragment(), MarsPhotosView,
    BackButtonListener {
    companion object {
        fun newInstance() = MarsPhotosFragment()
    }

    val presenter: MarsPhotosPresenter by moxyPresenter {
        MarsPhotosPresenter(AndroidSchedulers.mainThread(), ModelDataProviders.newInstance()).apply {
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

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}