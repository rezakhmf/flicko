package com.farahaniconsulting.flicko.ui.main.di

import com.farahaniconsulting.flicko.ui.photocollection.list.PhotoCollectionListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun provideMainFragmentFactory(): PhotoCollectionListFragment
}