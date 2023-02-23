package com.example.androidproject.presentation.view.view.auth.home.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.ItemsFruitBinding
import com.example.androidproject.domain.model.ItemsModel
import com.example.androidproject.presentation.view.view.auth.home.adapter.listener.ItemListener
import com.squareup.picasso.Picasso

class ItemsViewHolder(
    private val view: View,
    private val itemsListener: ItemListener,
) : RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel) {
        val name = view.findViewById<TextView>(R.id.tv_name)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        val deleteView = view.findViewById<ImageView>(R.id.delete)
        val fav = view.findViewById<ImageView>(R.id.btnFav)



        Picasso.get().load(Uri.parse(itemsModel.image)).into(imageView)


        name.text = itemsModel.description
        //   imageView.setBackgroundResource(itemsModel.image)
        //   date.text = itemsModel.date

        imageView.setOnClickListener {
            itemsListener.onClick()
            itemsModel.description
            itemsModel.image
        }

        imageView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.description,
                "itemsModel.date",
            )
        }
        deleteView.setOnClickListener {
            itemsListener.onDeleteClicked(itemsModel.description)
        }

        fav.setOnClickListener {
            fav.isSelected = !it.isSelected
            itemsListener.onFavClicked(itemsModel.description)
        }

    }
}