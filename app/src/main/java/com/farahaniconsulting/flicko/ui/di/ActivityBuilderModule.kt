package com.farahaniconsulting.flicko.ui.di

import com.farahaniconsulting.flicko.ui.main.MainActivity
import com.farahaniconsulting.flicko.ui.main.di.MainFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun bindMain() : MainActivity
}