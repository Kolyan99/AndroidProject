package com.example.androidproject.presentation.view.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentLoginBinding
import com.example.androidproject.presentation.view.view.LoginPresenter
import com.example.androidproject.presentation.view.view.LoginView
import com.example.androidproject.utils.NavigationFragment.Repit
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment(), LoginView {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var loginPresenter: LoginPresenter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginPresenter.setView(this)

        binding.btnShowCreads.setOnClickListener {
            loginPresenter.loginUser(
                binding.edtUserName.text.toString(),
                binding.edtUserPassword.text.toString()
            )
        }
    }

    override fun userLoggedIn() {
        //Repit(parentFragmentManager, OnBoardingFragment())
        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_container, HomeFragment())
            .commit()
    }
}






