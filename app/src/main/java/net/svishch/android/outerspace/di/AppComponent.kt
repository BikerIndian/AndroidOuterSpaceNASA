package net.svishch.android.outerspace.di

import dagger.Component
import net.svishch.android.outerspace.di.modules.*
import net.svishch.android.outerspace.mvp.model.ModelDataProviders
import net.svishch.android.outerspace.mvp.presenter.MainPresenter
import net.svishch.android.outerspace.mvp.presenter.MarsPhotosPresenter
import net.svishch.android.outerspace.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RepoModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: MarsPhotosPresenter)

    fun inject(modelDataProviders: ModelDataProviders)

    /*
    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoPresenter: RepoPresenter)
    fun inject(infoPresenter: InfoPresenter)

    */
}