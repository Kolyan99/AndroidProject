package com.example.androidproject.presentation.view.view.auth.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidproject.utils.BundelConstants.IMAGE_VIEW
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentDetailsBinding
import com.example.androidproject.utils.BundelConstants.DATA
import com.example.androidproject.utils.BundelConstants.NAME
import com.example.androidproject.utils.NavHelp.replaceGraph
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

        val detailsName = view.findViewById<TextView>(R.id.tv_name)
        val detailsDate = view.findViewById<TextView>(R.id.tv_date)
        val detailsImage = view.findViewById<ImageView>(R.id.detailsImage)

        val bundel = arguments
        bundel?.let { safeBundlt ->
            val name = safeBundlt.getString(NAME)
            val date = safeBundlt.getString(DATA)
            val image = safeBundlt.getInt(IMAGE_VIEW)

            detailsName.text = name
            detailsDate.text = date
            detailsImage.setBackgroundResource(image)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logoutUser()
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

