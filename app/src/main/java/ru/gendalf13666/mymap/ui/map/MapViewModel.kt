package ru.gendalf13666.mymap.ui.map

import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain
import ru.gendalf13666.mymap.domain.uscases.AddMarkerUseCase
import ru.gendalf13666.mymap.domain.uscases.GetMarkersUseCase
import ru.gendalf13666.mymap.ui.map.base.BaseMapViewModel

class MapViewModel(
    private val addMarkerUseCase: AddMarkerUseCase,
    private val getMarkersUseCase: GetMarkersUseCase
) : BaseMapViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun saveMarker(position: Point) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            addMarker(position)
        }

    private suspend fun addMarker(position: Point) {
        getOperationLiveData()
            .postValue(
                addMarkerUseCase.execute(
                    MarkerDomain(
                        latitude = position.latitude,
                        longitude = position.longitude
                    )
                )
            )
    }

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }
}
