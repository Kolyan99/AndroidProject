package com.example.androidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


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
            val name = bundel.getString("name")
            val date = bundel.getString("date")
            val image = bundel.getInt("imageView")

            detailsName.text = name
            detailsDate.text = date
            detailsImage.setBackgroundResource(image)
        }

    }

}

