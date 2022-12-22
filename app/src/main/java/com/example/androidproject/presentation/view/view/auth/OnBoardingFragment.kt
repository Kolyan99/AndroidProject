package com.example.androidproject.presentation.view.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentOnboardingBinding
import com.example.androidproject.presentation.view.view.OnBoardingPresenter
import com.example.androidproject.presentation.view.view.OnBoardingView
import com.example.androidproject.presentation.view.view.auth.home.ItemsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : Fragment(), OnBoardingView {


    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    @Inject
    lateinit var onBoardingPresenter: OnBoardingPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBoardingPresenter.setView(this)

        binding.btnFinish.setOnClickListener {
            onBoardingPresenter.goToItemsFragment()
        }
    }

    override fun goToItemsFragment() {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, ItemsFragment())
            .commit()
    }
}