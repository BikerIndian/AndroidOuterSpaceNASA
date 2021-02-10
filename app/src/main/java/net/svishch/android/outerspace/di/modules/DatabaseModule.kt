package net.svishch.android.outerspace.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.svishch.android.outerspace.App
import net.svishch.android.outerspace.mvp.model.db.DataDb
import net.svishch.android.outerspace.mvp.model.db.room.Database
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun dataDb(database: Database): DataDb {
        return DataDb(database)
    }

}