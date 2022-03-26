package ru.gendalf13666.mymap.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.gendalf13666.mymap.domain.models.MarkerDomain

@Entity(
    tableName = "tab_map_markers"
)
data class Marker(
    @PrimaryKey(autoGenerate = true)
    val markerId: Int,
    val latitude: Double,
    val longitude: Double,
    val visible: Boolean = true,
    val title: String,
    val description: String
) {
    fun toDomain(): MarkerDomain =
        MarkerDomain(
            markerId = this.markerId,
            latitude = this.latitude,
            longitude = this.longitude,
            visible = this.visible,
            title = this.title,
            description = this.description
        )
}
