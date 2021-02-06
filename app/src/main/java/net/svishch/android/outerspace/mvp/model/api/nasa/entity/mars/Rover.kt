package net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars

import com.google.gson.annotations.Expose

class Rover {

@Expose var id: Int? = null
@Expose var name: String? = null
@Expose var landingDate: String? = null
@Expose var launchDate: String? = null
@Expose var status: String? = null

}
