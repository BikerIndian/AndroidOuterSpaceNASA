package net.svishch.android.outerspace.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_mars_photos.view.*
import net.svishch.android.outerspace.R
import net.svishch.android.outerspace.mvp.model.image.IImageLoader
import net.svishch.android.outerspace.mvp.presenter.list.IMarsPhotosListPresenter
import net.svishch.android.outerspace.mvp.view.list.MarsPhotosItemView


class MarsPhotosRVAdapter(
    val presenter: IMarsPhotosListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<MarsPhotosRVAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, MarsPhotosItemView {

        override var pos = -1

        override fun setInfo(text: String) = with(containerView) {
            tv_info.text = text
        }
        override fun loadImg(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_img)
        }

        override fun favoritesImgOn(favorites: Boolean) = with(containerView){
           // @ColorRes var fillColorRes: Int = R.color.color_white
            if (favorites) {
                iv_img_favorites.visibility = View.VISIBLE
            }else{
                iv_img_favorites.visibility = View.INVISIBLE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_mars_photos,
                parent,
                false
            )
        )

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }
}