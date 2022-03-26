package ru.gendalf13666.mymap.data.repository

import ru.gendalf13666.mymap.data.repository.datasource.CacheDataSource
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain
import ru.gendalf13666.mymap.domain.repository.CacheRepository

class CacheRepositoryImpl(private val dataSource: CacheDataSource) : CacheRepository {
    override suspend fun addMarker(marker: MarkerDomain): AppState =
        dataSource.addMarker(marker = marker)

    override suspend fun getMarkers(): AppState =
        dataSource.getMarkers()

    override suspend fun getMarkerById(markerId: Int): AppState =
        dataSource.getMarkerById(markerId = markerId)

    override suspend fun removeMarker(markerId: Int): AppState =
        dataSource.removeMarker(markerId = markerId)

    override suspend fun updateMarker(marker: MarkerDomain): AppState =
        dataSource.updateMarker(marker = marker)
}
