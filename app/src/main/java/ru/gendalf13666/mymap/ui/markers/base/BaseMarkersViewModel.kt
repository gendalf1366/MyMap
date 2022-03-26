package ru.gendalf13666.mymap.ui.markers.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.gendalf13666.mymap.domain.AppState

abstract class BaseMarkersViewModel : ViewModel() {
    private val operationLiveData = MutableLiveData<AppState>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO +
            SupervisorJob() +
            CoroutineExceptionHandler { _, throwable ->
                handleError(throwable)
            }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun getOperationLiveData() = operationLiveData

    abstract fun getMarkers(): Job
    abstract fun removeMarker(markerId: Int): Job
}
