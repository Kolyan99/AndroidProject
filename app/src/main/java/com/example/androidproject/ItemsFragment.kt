package com.example.androidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.adapter.ItemsAdapter
import com.example.androidproject.adapter.model.ItemsModel
import com.example.androidproject.listener.ItemListener


class ItemsFragment : Fragment(), ItemListener {

    private lateinit var itemsAdapter: ItemsAdapter

    private val viewModel: ItemsViewModel by viewModels()

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

        viewModel.getData()

        viewModel.items.observe(viewLifecycleOwner){ listItems ->
            itemsAdapter.submitList(listItems)
        }

        viewModel.msg.observe(viewLifecycleOwner){msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }

        viewModel.bundel.observe(viewLifecycleOwner){navBundle ->
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(getString(R.string.name), navBundle.name)
            bundle.putString(getString(R.string.date), navBundle.data)
            bundle.putInt(getString(R.string.imageView), navBundle.image)
            detailsFragment.arguments = bundle

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, DetailsFragment())
                .addToBackStack(getString(R.string.OnBoarding))
                .commit()
        }
    }

    override fun onClick() {
       viewModel.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
     viewModel.elementClicked(name,date,imageView)
    }
}