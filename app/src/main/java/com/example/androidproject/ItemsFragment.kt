package com.example.androidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.adapter.ItemsAdapter
import com.example.androidproject.adapter.model.ItemsModel
import com.example.androidproject.listener.ItemListener


class ItemsFragment : Fragment(), ItemListener {

    private lateinit var itemsAdapter: ItemsAdapter

  //  private val itemsAdapter2: ItemsAdapter = ItemsAdapter()// так делать можно, но не нужно)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_items, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        itemsAdapter = ItemsAdapter(this)
        val recucleView = view.findViewById<RecyclerView>(R.id.RecyclerView)
        recucleView.layoutManager = LinearLayoutManager(context)
        recucleView.adapter = itemsAdapter

        val listItems = listOf<ItemsModel>(
            ItemsModel(R.drawable.fruit,
            "Android",
            "26.02.2023"),
            ItemsModel(
                R.drawable.fruit,
                "C++",
                "26.02.2023"),
            ItemsModel(
                R.drawable.img,
                "C",
                "26.02.2024"),
            ItemsModel(
                R.drawable.img_1,
                "Java",
                "26.02.2026"),
            ItemsModel(
                R.drawable.fruit,
                "IOS",
                "26.04.2020"),
            ItemsModel(
                R.drawable.fruit,
                "PHP",
                "26.02.2023"),
            ItemsModel(
                R.drawable.fruit,
                "JS",
                "22.02.2021")
        )
        itemsAdapter.submitList(listItems.toList())

    }

    override fun onClick() {
       Toast.makeText(context, " ImageView clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("date", date)
        bundle.putInt("imageView", imageView)
        detailsFragment.arguments = bundle

         // TODO add метод мы больше не используем
        // теперь мы всегда будем использовать replace
        // replace всегда будет иметь или addToBackstack, чтобы
        // мочь вернуться назад или же не будет, чтобы
        // мы вернулись назад.
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, DetailsFragment())
            .addToBackStack("Onboarding")
            .commit()
    }

}