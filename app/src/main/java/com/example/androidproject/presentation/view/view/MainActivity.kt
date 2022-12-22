package com.example.androidproject.presentation.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.androidproject.R
import com.example.androidproject.databinding.ActivityMainBinding
import com.example.androidproject.presentation.view.view.auth.HomeFragment
import com.example.androidproject.presentation.view.view.auth.LoginFragment
import com.example.androidproject.presentation.view.view.auth.home.MainPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null

    @Inject
    lateinit var mainPresenter: MainPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(_binding!!.root)

        mainPresenter.setView(this)

        mainPresenter.checkUserExists()
    }

    override fun userExistsResalt(userExists: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()//полезно для дз(вложенность)
        fragmentTransaction.add(R.id.activity_container,
            when(userExists){
                true -> HomeFragment()
                false ->LoginFragment()
            }
        )
        fragmentTransaction.commit()
    }
    }

