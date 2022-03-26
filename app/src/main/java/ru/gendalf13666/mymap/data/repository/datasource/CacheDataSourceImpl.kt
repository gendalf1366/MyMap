package ru.gendalf13666.mymap.data.repository.datasource

import com.yandex.mapkit.geometry.Point
import ru.gendalf13666.mymap.data.mappers.markerToDataLayer
import ru.gendalf13666.mymap.data.storage.Storage
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.*

class CacheDataSourceImpl(private val storage: Storage) : CacheDataSource {
    override suspend fun addMarker(marker: MarkerDomain) =
        try {
            val result = storage
                .storageDao()
                .addMarker(markerToDataLayer(marker))
            AppState.Success(
                NewMarkerResult(
                    result > ZERO_INT,
                    Point(
                        marker.latitude, marker.longitude
                    )
                )
            )
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getMarkers() =
        try {
            val result = storage
                .storageDao()
                .getMarkers()
                .map { it.toDomain() }
            AppState.Success(MarkersResult(result))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getMarkerById(markerId: Int): AppState =
        try {
            val result = storage
                .storageDao()
                .getMarkerById(markerId = markerId)
                .toDomain()
            AppState.Success(MarkerResult(result = result))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun removeMarker(markerId: Int) =
        try {
            val result = storage
                .storageDao()
                .removeMarker(markerId)
            AppState.Success(OperationResult(result > ZERO_INT))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun updateMarker(marker: MarkerDomain) =
        try {
            val result = storage
                .storageDao()
                .updateMarker(markerToDataLayer(marker))
            AppState.Success(OperationResult(result > ZERO_INT))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    companion object {
        private const val ZERO_INT = 0
    }
}
