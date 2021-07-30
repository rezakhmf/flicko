package com.farahaniconsulting.flicko.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.farahanconsulting.flicko.R
import com.farahaniconsulting.flicko.ui.photocollection.list.PhotoCollectionListFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector,
PhotoCollectionListFragment.OnFragmentInteractionListener {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    override fun androidInjector() = androidInjector

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.nav_fragment).navigateUp()


}