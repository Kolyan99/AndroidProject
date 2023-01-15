package com.example.androidproject.presentation.view.view.auth.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentHomeBinding
import com.example.androidproject.utils.NavHelp.replaceGraph
import com.example.androidproject.utils.coroutines.CoroutensExample
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutensExample().testCoroutineCancel()

        viewModel.showUserData()
        viewModel.nav.observe(viewLifecycleOwner){
            if (it != null){
                replaceGraph(it)
            }
        }

        binding.btnGoOnBoarding.setOnClickListener {
            viewModel.goToOnBoarding()
        }


        viewModel.userCreds.observe(viewLifecycleOwner) {
            binding.tvUserCreds.text = "${it.userName}\n ${it.userPassword}"
        }
    }
}





