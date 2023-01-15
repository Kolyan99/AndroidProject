package com.example.androidproject.presentation.view.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidproject.databinding.FragmentOnboardingBinding
import com.example.androidproject.utils.NavHelp.navigateWithDeleteBackStack
import dagger.hilt.android.AndroidEntryPoint


class OnBoardingFragment : Fragment() {

    private val viewModel: OnBoardingViewModel by viewModels()
    private var _binding: FragmentOnboardingBinding? = null
    private val binding: FragmentOnboardingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it != null) {
                navigateWithDeleteBackStack(
                    it.destinationId,
                    it.removeFragment
                )
                viewModel.finishPerformed()
            }
        }
    }
}



