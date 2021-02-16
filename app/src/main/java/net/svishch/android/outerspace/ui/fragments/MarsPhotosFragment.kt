package net.svishch.android.outerspace.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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
    private lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        View.inflate(context, R.layout.fragment_mars_photos, null)

    override fun init() {
        setHasOptionsMenu(true)
        actionBar = (activity as AppCompatActivity).supportActionBar!!

        rv_mars_photos.layoutManager = GridLayoutManager(context, 3) // В два ряда
        adapter = MarsPhotosRVAdapter(presenter.marsPhotosListPresenter, GlideImageLoader())
        rv_mars_photos.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        presenter.getBundle()
            .putParcelable(KEY_RECYCLER_PHOTOS, rv_mars_photos.layoutManager?.onSaveInstanceState())
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.photos_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                delBtnBackInActionBar()
                presenter.loadData(true)
                true
            }

            R.id.menu_favorites_list -> {

                addBtnBackInActionBar()
                presenter.loadFavorites()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun updateList() {
        var marsPhotosManagerState: Parcelable? =
            presenter.getBundle().getParcelable(KEY_RECYCLER_PHOTOS)
        adapter?.notifyDataSetChanged()
        rv_mars_photos.getLayoutManager()?.onRestoreInstanceState(marsPhotosManagerState)
    }

    override fun backPressed() = presenter.backPressed()

    private fun addBtnBackInActionBar() {
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun delBtnBackInActionBar() {
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }
}