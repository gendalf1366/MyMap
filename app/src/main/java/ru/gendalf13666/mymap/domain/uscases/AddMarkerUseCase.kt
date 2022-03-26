package ru.gendalf13666.mymap.domain.uscases

import ru.gendalf13666.mymap.domain.AppState
import ru.gendalf13666.mymap.domain.models.MarkerDomain
import ru.gendalf13666.mymap.domain.repository.CacheRepository

class AddMarkerUseCase(private val repository: CacheRepository) {
    suspend fun execute(marker: MarkerDomain): AppState =
        repository.addMarker(marker = marker)
}
