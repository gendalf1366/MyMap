package ru.gendalf13666.mymap.data.storage

import androidx.room.*
import ru.gendalf13666.mymap.data.storage.entity.Marker

@Dao
interface StorageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMarker(marker: Marker): Long

    @Query("SELECT * FROM tab_map_markers ORDER by title")
    fun getMarkers(): List<Marker>

    @Query("SELECT * FROM tab_map_markers WHERE markerId = :markerId")
    fun getMarkerById(markerId: Int): Marker

    @Query("DELETE FROM tab_map_markers WHERE markerId = :markerId")
    fun removeMarker(markerId: Int): Int

    @Update
    fun updateMarker(marker: Marker): Int
}
