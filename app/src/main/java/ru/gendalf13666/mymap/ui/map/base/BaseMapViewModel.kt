package ru.gendalf13666.mymap.ui.map.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.*
import ru.gendalf13666.mymap.domain.AppState

abstract class BaseMapViewModel : ViewModel() {
    private val operationLiveDataMut = MutableLiveData<AppState>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO +
            SupervisorJob() +
            CoroutineExceptionHandler { _, throwable ->
                handleError(throwable)
            }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun getOperationLiveData() = operationLiveDataMut

    abstract fun saveMarker(position: Point): Job
    abstract fun getMarkers(): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}
