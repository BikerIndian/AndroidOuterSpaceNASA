package net.svishch.android.outerspace.mvp.presenter.list

import net.svishch.android.outerspace.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}