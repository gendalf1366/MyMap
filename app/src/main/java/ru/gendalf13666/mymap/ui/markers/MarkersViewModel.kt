package ru.gendalf13666.mymap.ui.markers

import kotlinx.coroutines.launch
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.uscases.GetMarkersUseCase
import ru.gendalf13666.mymap.domain.uscases.RemoveMarkerUseCase
import ru.gendalf13666.mymap.ui.markers.base.BaseMarkersViewModel

class MarkersViewModel(
    private val getMarkersUseCase: GetMarkersUseCase,
    private val removeMarkerUseCase: RemoveMarkerUseCase
) : BaseMarkersViewModel() {

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun removeMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)

            getOperationLiveData()
                .postValue(removeMarkerUseCase.execute(markerId))

            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }
}
