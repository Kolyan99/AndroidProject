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
import com.example.androidproject.BundelConstants.IMAGE_VIEW
import com.example.androidproject.adapter.ItemsAdapter
import com.example.androidproject.adapter.model.ItemsModel
import com.example.androidproject.listener.ItemListener

//not use
//const val NAME = "name"

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

        viewModel.items.observe(viewLifecycleOwner) { listItems ->
            itemsAdapter.submitList(listItems)
        }

        viewModel.msg.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
        }

        viewModel.bundel.observe(viewLifecycleOwner) { navBundle ->
            if(navBundle != null){
                val detailsFragment = DetailsFragment()
                val bundle = Bundle()
                bundle.putString(NAME, navBundle.name)
                bundle.putString(DATA, navBundle.data)
                bundle.putInt(IMAGE_VIEW, navBundle.image)
                detailsFragment.arguments = bundle

                Toast.makeText(context, "called", Toast.LENGTH_SHORT).show()

                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_container, DetailsFragment())
                    .addToBackStack(getString(R.string.OnBoarding))
                    .commit()
                // in the end of our action
                viewModel.userNavigated()
            }
        }
    }

    override fun onClick() {
        viewModel.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        viewModel.elementClicked(name, date, imageView)
    }


    companion object {
        // we can use it, because we see where we get it
        const val DATA = "data"
        const val NAME = "name"
    }
}