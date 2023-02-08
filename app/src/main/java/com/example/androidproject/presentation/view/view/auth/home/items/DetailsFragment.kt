package com.example.androidproject.presentation.view.view.auth.home.items

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentDetailsBinding
import com.example.androidproject.utils.BundelConstants.description
import com.example.androidproject.utils.BundelConstants.image
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundel = arguments
        bundel?.let { safeBundlt ->
            val name = safeBundlt.getString(description)
            val date = safeBundlt.getString(image)

            binding.detailsName.text = description
            Picasso.get().load(Uri.parse(image)).into(binding.detailsImage)
        }

        binding.btnLogout.setOnClickListener {
            binding.btnLogout.isPressed = !it.isPressed
         //   viewModel.logoutUser()
        }

        viewModel.nav.observe(viewLifecycleOwner) {
            if (it!= null){
                val navGraph = findNavController().navInflater.inflate(
                    it
                )
                navGraph.startDestination = R.id.loginFragment
                findNavController().graph = navGraph
            }
        }
    }

}

