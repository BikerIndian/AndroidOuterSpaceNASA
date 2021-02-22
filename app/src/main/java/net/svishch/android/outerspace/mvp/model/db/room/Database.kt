package net.svishch.android.outerspace.mvp.model.db.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import net.svishch.android.outerspace.mvp.model.db.room.entity.RoomMarsRoverPhotos
import net.svishch.android.outerspace.mvp.model.db.room.entity.dao.MarsPhotosDao

@androidx.room.Database(entities = [RoomMarsRoverPhotos::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract val marsPhotosDao: MarsPhotosDao

    companion object {

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE RoomMarsRoverPhotos ADD COLUMN isFavorites BOOLEAN DEFAULT 0")
            }
        }

        const val DB_NAME = "database.db"
        private var instance: Database? = null

        fun getInstance() = instance ?: throw RuntimeException("База данных не создана")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }

}