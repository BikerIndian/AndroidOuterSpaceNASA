package net.svishch.android.outerspace.mvp.model.db.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomMarsRoverPhotos(
    @PrimaryKey var id: Int,
    var imgSrc: String,
    var earthDate: String,

){
    var isFavorites: Boolean = false
}