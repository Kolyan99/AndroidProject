package com.example.androidproject.presentation.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.androidproject.utils.BundelConstants.IMAGE_VIEW
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentDetailsBinding
import com.example.androidproject.databinding.FragmentOnboardingBinding
import com.example.androidproject.presentation.View.ItemsFragment.Companion.NAME


class DetailsFragment : Fragment() {

    private var _viewBinding: FragmentDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _viewBinding = FragmentDetailsBinding.inflate(inflater)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundel = arguments
        bundel?.let { safeBundlt ->
            val name = bundel.getString(NAME)
            val date = bundel.getString(ItemsFragment.DATA)
            val image = bundel.getInt(IMAGE_VIEW)

            viewBinding.detailsName.text = name
          //  viewBinding.detailsDate.text = date
            viewBinding.detailsImage.setBackgroundResource(image)
        }

    }

}

