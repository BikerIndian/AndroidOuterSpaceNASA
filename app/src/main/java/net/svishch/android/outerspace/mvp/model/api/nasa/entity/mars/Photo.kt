package net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class Photo (
    @Expose var id: Int? = null,
    @Expose var imgSrc: String? = null,
    @Expose var earthDate: String? = null,
    ) : Parcelable
{
    @Expose var sol: Int? = null
    @Expose var camera: Camera? = null
    @Expose var rover: Rover? = null
    var favorites : Boolean = false
}
