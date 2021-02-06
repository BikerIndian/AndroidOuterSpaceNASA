package net.svishch.android.outerspace

import android.app.Application
import net.svishch.android.outerspace.di.AppComponent
import net.svishch.android.outerspace.di.DaggerAppComponent
import net.svishch.android.outerspace.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // DaggerAppComponent создается сам
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}