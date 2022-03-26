package ru.gendalf13666.mymap.domain.repository

import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain

interface CacheRepository {
    suspend fun addMarker(marker: MarkerDomain): AppState
    suspend fun getMarkers(): AppState
    suspend fun getMarkerById(markerId: Int): AppState
    suspend fun removeMarker(markerId: Int): AppState
    suspend fun updateMarker(marker: MarkerDomain): AppState
}
