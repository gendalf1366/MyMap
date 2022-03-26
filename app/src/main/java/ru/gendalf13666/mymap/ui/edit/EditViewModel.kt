package ru.gendalf13666.mymap.ui.edit

import kotlinx.coroutines.launch
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain
import ru.gendalf13666.mymap.domain.uscases.GetMarkerByIdUseCase
import ru.gendalf13666.mymap.domain.uscases.UpdateMarkerUseCase
import ru.gendalf13666.mymap.ui.edit.base.BaseEditViewModel

class EditViewModel(
    private val updateMarkerUseCase: UpdateMarkerUseCase,
    private val getMarkerByIdUseCase: GetMarkerByIdUseCase
) : BaseEditViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun updateMarker(marker: MarkerDomain) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(
                    updateMarkerUseCase.execute(marker)
                )
        }

    override fun getMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkerByIdUseCase.execute(markerId))
        }
}
