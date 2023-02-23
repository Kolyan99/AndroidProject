package com.example.androidproject.presentation.view.view.auth.home.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.presentation.view.view.auth.home.adapter.ItemsAdapter
import com.example.androidproject.presentation.view.view.auth.home.adapter.listener.ItemListener
import com.example.androidproject.utils.BundelConstants.description
import com.example.androidproject.utils.BundelConstants.image
import com.example.androidproject.utils.NavHelp.navigateWithBundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemListener {

    private lateinit var itemsAdapter: ItemsAdapter
    private val viewModel: ItemsViewModel by viewModels()

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



//        viewModel.items.observe(viewLifecycleOwner) { listItems ->
//            itemsAdapter.submitList(listItems)
//        }

        // Способ 1
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
          //  viewModel.getData.collect()
        }

        // Способ 2
        viewModel.getData()
        viewModel.trigger.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            //    it.collect()
            }
        }

        // Способ 3
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getDataSimple()
        }

        viewModel.showData()
        viewModel.items.observe(viewLifecycleOwner){listItems ->
            itemsAdapter.submitList(listItems)
        }




        viewModel.msg.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
        }

        viewModel.bundel.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(description,navBundle.description)
                bundle.putString(image, navBundle.image)

                Toast.makeText(context, getString(R.string.called), Toast.LENGTH_SHORT).show()

                navigateWithBundle(
                    navBundle.destinationId,
                    bundle
                )
                viewModel.userNavigated()
            }
        }

        viewModel.error.observe(viewLifecycleOwner){
           Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick() {
        viewModel.imageViewClicked()
    }

    override fun onElementSelected(description: String, image: String) {
        viewModel.elementClicked(description, image)
    }

    override fun onDeleteClicked(description: String) {
        viewModel.deleteItem(description)
    }

    override fun onFavClicked(description: String) {
        viewModel.onFavClicked(description)
    }


}
