package ru.gendalf13666.mymap.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.gendalf13666.mymap.di.Di

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    Di.viewModelModule(),
                    Di.useCasesModule(),
                    Di.repositoryModule(),
                    Di.storageModule(),
                )
            )
        }
    }
}
