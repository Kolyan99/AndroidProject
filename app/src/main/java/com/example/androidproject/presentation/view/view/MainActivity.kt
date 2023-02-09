package com.example.androidproject.presentation.view.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidproject.App
import com.example.androidproject.R
import com.example.androidproject.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels{viewModelFactory}

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        (applicationContext as App).provideAppComponent().inject(this)


        viewModel.checkUserExists()

         navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView
        ) as NavHostFragment


        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener(this)

        binding.bottomNavigation.setupWithNavController(navController)

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