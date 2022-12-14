package com.example.androidproject.presentation.view.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.presentation.view.view.adapter.model.ItemsModel
import com.example.androidproject.presentation.view.view.adapter.listener.ItemListener

class ItemsViewHolder( private val view: View, private val itemsListener: ItemListener
): RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel){
        val name = view.findViewById<TextView>(R.id.tv_name)
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        val date = view.findViewById<TextView>(R.id.tv_date)


        name.text = itemsModel.name
        imageView.setBackgroundResource(itemsModel.image)
        date.text = itemsModel.date

        imageView.setOnClickListener{
            itemsListener.onClick()
        }

        imageView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }

    }
}