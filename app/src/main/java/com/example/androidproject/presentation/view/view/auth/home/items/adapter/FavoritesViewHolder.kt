package com.example.androidproject.presentation.view.view.auth.home.items.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentFavoritesBinding
import com.example.androidproject.databinding.ItemFavoritesBinding
import com.example.androidproject.databinding.ItemsFruitBinding
import com.example.androidproject.domain.items.ItemsInteractor
import com.example.androidproject.domain.model.FavoritesModel
import com.example.androidproject.domain.model.ItemsModel
import com.example.androidproject.presentation.view.view.auth.home.adapter.listener.ItemListener
import com.squareup.picasso.Picasso

class FavoritesViewHolder(
    private val viewBinding: ItemFavoritesBinding,


) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(favItems: FavoritesModel) {

        viewBinding.tvName.text = favItems.description
        Picasso.get().load(Uri.parse(favItems.image)).into(viewBinding.ivImage)
    }
}
