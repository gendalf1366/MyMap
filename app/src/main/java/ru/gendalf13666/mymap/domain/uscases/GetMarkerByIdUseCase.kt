package ru.gendalf13666.mymap.domain.uscases

import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.repository.CacheRepository

class GetMarkerByIdUseCase(private val repository: CacheRepository) {
    suspend fun execute(markerId: Int): AppState =
        repository.getMarkerById(markerId = markerId)
}
