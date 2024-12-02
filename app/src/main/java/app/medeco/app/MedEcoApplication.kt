package app.medeco.app

import android.app.Application
import app.medeco.data.di.DataModule
import app.medeco.domain.di.DomainModule
import app.medeco.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MedEcoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MedEcoApplication)
            androidLogger()
            modules(
                DataModule().module,
                PresentationModule().module,
                DomainModule().module
            )
        }
    }
}