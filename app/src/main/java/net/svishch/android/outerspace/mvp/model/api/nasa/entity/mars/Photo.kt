package net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars

import com.google.gson.annotations.Expose

class Photo {

    @Expose var id: Int? = null
    @Expose var sol: Int? = null
    @Expose var camera: Camera? = null
    @Expose var imgSrc: String? = null
    @Expose var earthDate: String? = null
    @Expose var rover: Rover? = null
}
