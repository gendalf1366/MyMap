package ru.gendalf13666.mymap.domain

import ru.gendalf13666.mymap.domain.models.DataResult

sealed class AppState {
    data class Success(val data: DataResult) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
