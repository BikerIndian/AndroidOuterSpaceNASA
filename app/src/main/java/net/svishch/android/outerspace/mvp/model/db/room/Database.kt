package net.svishch.android.outerspace.mvp.model.db.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import net.svishch.android.outerspace.mvp.model.db.room.entity.RoomMarsRoverPhotos
import net.svishch.android.outerspace.mvp.model.db.room.entity.dao.MarsPhotosDao

@androidx.room.Database(entities = [RoomMarsRoverPhotos::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val marsPhotosDao: MarsPhotosDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException("База данных не создана")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME).build()
            }
        }
    }

}