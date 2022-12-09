package com.example.androidproject.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.ItemsFruitBinding
import com.example.androidproject.presentation.adapter.model.ItemsModel
import com.example.androidproject.presentation.adapter.listener.ItemListener

class ItemsViewHolder(
    private val viewBinding: ItemsFruitBinding,
    private val itemsListener: ItemListener
): RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(itemsModel: ItemsModel){

        viewBinding.tvName.text = itemsModel.name
        viewBinding.ivImage.setBackgroundResource(itemsModel.image)
        viewBinding.tvDate.text = itemsModel.date

        viewBinding.ivImage.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener {
            itemsListener.onElementSelected(
                itemsModel.name,
                itemsModel.date,
                itemsModel.image
            )
        }

    }
}