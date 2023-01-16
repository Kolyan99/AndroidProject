package com.example.androidproject.presentation.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.androidproject.R
import com.example.androidproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        viewModel.checkUserExists()

         navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment


        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener(this)

        binding.bottomNavigation.setupWithNavController(navController)

        val btnav = AppBarConfiguration(
            setOf(R.id.onBoardingFragment, R.id.itemsFragment)
        )

        NavigationUI.setupActionBarWithNavController(this, navController, btnav)


        viewModel.userExsits.observe(this) {
            navController.graph = getNavGraph()
      }



        viewModel.visibility.observe(this){
            binding.bottomNavigation.visibility = it
        }


    }

    private fun getNavGraph(): NavGraph{
        val navGraph = navHostFragment.navController.navInflater.inflate(
            R.navigation.auth_graph
        )
        val random = (1..2).random()
        if (random==1 ){
            navGraph.startDestination = R.id.loginFragment
        }else{
            navGraph.startDestination = R.id.homeFragment
        }
        return navGraph
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        viewModel.destinationChanged(destination)
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(this)
    }
}