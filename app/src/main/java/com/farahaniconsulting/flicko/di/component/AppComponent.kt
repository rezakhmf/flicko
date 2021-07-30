package com.farahaniconsulting.flicko.di.component

import android.app.Application
import com.farahaniconsulting.flicko.FlickoApplication
import com.farahaniconsulting.flicko.di.modules.*
import com.farahaniconsulting.flicko.ui.di.ActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        NetModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        UseCaseModule::class,
        RxJavaModule::class,
        ActivityBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<FlickoApplication> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application) : Builder
    }

    fun inject(application: Application)
}