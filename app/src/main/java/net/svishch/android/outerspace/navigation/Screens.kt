package net.svishch.android.outerspace.navigation

import net.svishch.android.outerspace.mvp.model.api.nasa.entity.mars.Photo
import net.svishch.android.outerspace.ui.fragments.MarsPhotosFragment
import net.svishch.android.outerspace.ui.fragments.PhotoFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class MarsPhotos() : SupportAppScreen() {
        override fun getFragment() = MarsPhotosFragment.newInstance()
    }

    class PhotoScreens(private val photo: Photo) : SupportAppScreen() {
        override fun getFragment() = PhotoFragment.newInstance(photo)
    }
}