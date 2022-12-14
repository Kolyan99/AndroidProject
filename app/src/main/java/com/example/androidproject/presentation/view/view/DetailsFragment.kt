package com.example.androidproject.presentation.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.androidproject.utils.BundelConstants.IMAGE_VIEW
import com.example.androidproject.R
import com.example.androidproject.utils.BundelConstants.DATA
import com.example.androidproject.utils.BundelConstants.NAME


class DetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailsName = view.findViewById<TextView>(R.id.tv_name)
        val detailsDate = view.findViewById<TextView>(R.id.tv_date)
        val detailsImage = view.findViewById<ImageView>(R.id.detailsImage)

        val bundel = arguments
        bundel?.let { safeBundlt ->
            val name = bundel.getString(NAME)
            val date = bundel.getString(DATA)
            val image = bundel.getInt(IMAGE_VIEW)

            detailsName.text = name
            detailsDate.text = date
            detailsImage.setBackgroundResource(image)
        }

    }

}

