package net.svishch.android.outerspace.navigation

import net.svishch.android.outerspace.ui.fragments.MarsPhotosFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class MarsPhotos() : SupportAppScreen() {
        override fun getFragment() = MarsPhotosFragment.newInstance()
    }

}