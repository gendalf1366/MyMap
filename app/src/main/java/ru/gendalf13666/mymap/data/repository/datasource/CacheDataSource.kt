package ru.gendalf13666.mymap.data.repository.datasource

import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain

interface CacheDataSource {
    suspend fun addMarker(marker: MarkerDomain): AppState
    suspend fun getMarkers(): AppState
    suspend fun getMarkerById(markerId: Int): AppState
    suspend fun removeMarker(markerId: Int): AppState
    suspend fun updateMarker(marker: MarkerDomain): AppState
}