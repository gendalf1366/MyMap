package ru.gendalf13666.mymap.ui.edit.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain

abstract class BaseEditViewModel : ViewModel() {
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

    abstract fun updateMarker(marker: MarkerDomain): Job
    abstract fun getMarker(markerId: Int): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}
