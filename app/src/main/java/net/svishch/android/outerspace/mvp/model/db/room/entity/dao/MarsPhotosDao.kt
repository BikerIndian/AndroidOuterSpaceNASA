package net.svishch.android.outerspace.mvp.model.db.room.entity.dao

import androidx.room.*

import net.svishch.android.outerspace.mvp.model.db.room.entity.RoomMarsRoverPhotos

@Dao
interface MarsPhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: RoomMarsRoverPhotos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos: RoomMarsRoverPhotos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<RoomMarsRoverPhotos>)

    @Update
    fun update(photo: RoomMarsRoverPhotos)

    @Update
    fun update(vararg photos: RoomMarsRoverPhotos)

    @Update
    fun update(photos: List<RoomMarsRoverPhotos>)

    @Delete
    fun delete(photo: RoomMarsRoverPhotos)

    @Delete
    fun delete(vararg photos: RoomMarsRoverPhotos)

    @Delete
    fun delete(photos: List<RoomMarsRoverPhotos>)

    @Query("SELECT * FROM RoomMarsRoverPhotos")
    fun getAll() : List<RoomMarsRoverPhotos>

    @Query("SELECT * FROM RoomMarsRoverPhotos WHERE id = :id LIMIT 1")
    fun findById(id: Int?) : RoomMarsRoverPhotos
}