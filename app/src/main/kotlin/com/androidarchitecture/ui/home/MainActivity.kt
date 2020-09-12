package com.androidarchitecture.ui.home

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.androidarchitecture.R
import com.androidarchitecture.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel>() {

    private lateinit var bottomNavView: BottomNavigationView

    override fun createViewModel() = MainActivityViewModel::class.java

    override fun getLayoutId() = R.layout.activity_main

    override fun subscribeToObservers() = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_content) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
    }
}
