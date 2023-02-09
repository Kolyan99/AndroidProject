package com.example.androidproject.presentation.view.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.App
import com.example.androidproject.databinding.FragmentLoginBinding
import com.example.androidproject.utils.BaseFragment
import com.example.androidproject.utils.NavHelp.navigateWithDeleteBackStack
import javax.inject.Inject


class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels{viewModelFactory}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity().applicationContext as App).provideAppComponent().inject(this)

        binding.btnShowCreads.setOnClickListener {
            viewModel.loginUser(
                binding.edtUserName.text.toString(),
                binding.edtUserName.text.toString()
            )
        }
        viewModel.nav.observe(viewLifecycleOwner){
            if(it!=null){
                navigateWithDeleteBackStack(
                    it.destinationId,
                    it.removeFragment
                )
                viewModel.userNavigate()
            }
        }
    }
}





