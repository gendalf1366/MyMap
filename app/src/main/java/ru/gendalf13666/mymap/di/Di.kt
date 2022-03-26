package ru.gendalf13666.mymap.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gendalf13666.mymap.data.repository.CacheRepositoryImpl
import ru.gendalf13666.mymap.data.repository.datasource.CacheDataSource
import ru.gendalf13666.mymap.data.repository.datasource.CacheDataSourceImpl
import ru.gendalf13666.mymap.data.storage.Storage
import ru.gendalf13666.mymap.domain.repository.CacheRepository
import ru.gendalf13666.mymap.domain.uscases.*
import ru.gendalf13666.mymap.ui.edit.EditViewModel
import ru.gendalf13666.mymap.ui.map.MapViewModel
import ru.gendalf13666.mymap.ui.markers.MarkersViewModel

object Di {

    private const val PERSISTED = "Persisted"
    private const val IN_MEMORY = "InMemory"
    private const val DB_NAME = "MapDataBase"

    fun viewModelModule() = module {
        viewModel() {
            MapViewModel(
                addMarkerUseCase = get(),
                getMarkersUseCase = get()
            )
        }

        viewModel() {
            MarkersViewModel(
                getMarkersUseCase = get(),
                removeMarkerUseCase = get()
            )
        }

        viewModel() {
            EditViewModel(
                updateMarkerUseCase = get(),
                getMarkerByIdUseCase = get()
            )
        }
    }

    fun useCasesModule() = module {
        factory {
            AddMarkerUseCase(repository = get())
        }

        factory {
            GetMarkersUseCase(repository = get())
        }

        factory {
            GetMarkerByIdUseCase(repository = get())
        }

        factory {
            RemoveMarkerUseCase(repository = get())
        }

        factory {
            UpdateMarkerUseCase(repository = get())
        }
    }

    fun repositoryModule() = module {
        single<CacheRepository>() {
            CacheRepositoryImpl(dataSource = get())
        }

        single<CacheDataSource> {
            CacheDataSourceImpl(storage = get(qualifier = named(PERSISTED)))
        }
    }

    fun storageModule() = module {
        single(qualifier = named(PERSISTED)) {
            Room.databaseBuilder(androidContext(), Storage::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        single(qualifier = named(IN_MEMORY)) {
            Room.inMemoryDatabaseBuilder(androidContext(), Storage::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
