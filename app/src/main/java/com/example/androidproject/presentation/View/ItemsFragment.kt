package com.example.androidproject.presentation.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.utils.BundelConstants.IMAGE_VIEW
import com.example.androidproject.ItemsViewModel
import com.example.androidproject.R
import com.example.androidproject.data.ItemsRepositoryImpl
import com.example.androidproject.databinding.FragmentItemsBinding
import com.example.androidproject.databinding.FragmentOnboardingBinding
import com.example.androidproject.domain.ItemsInteractor
import com.example.androidproject.presentation.adapter.ItemsAdapter
import com.example.androidproject.presentation.adapter.model.ItemsModel
import com.example.androidproject.presentation.adapter.listener.ItemListener

//not use
//const val NAME = "name"

class ItemsFragment : Fragment(), ItemListener, ItemsView {

    private var _viewBinding: FragmentItemsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var itemsAdapter: ItemsAdapter

    lateinit var itemsPresentler: ItemsPresentler

    private val viewModel: ItemsViewModel by viewModels()

    //  private val itemsAdapter2: ItemsAdapter = ItemsAdapter()// так делать можно, но не нужно)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       _viewBinding = FragmentItemsBinding.inflate(inflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresentler = ItemsPresentler(this, ItemsInteractor(ItemsRepositoryImpl()))


        itemsAdapter = ItemsAdapter(this)
        val recucleView = view.findViewById<RecyclerView>(R.id.RecyclerView)
        recucleView.adapter = itemsAdapter

        viewModel.getData()

        itemsPresentler.getData()


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
        itemsPresentler.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresentler.elementSelected(name, date, imageView)
       // viewModel.elementClicked(name, date, imageView)
    }


    override fun dateReceived(list: List<ItemsModel>) {
        itemsAdapter.submitList(list)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun gotoDetails(name: String, date: String, imageView: Int) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putString(NAME, name)
        bundle.putString(DATA, date)
        bundle.putInt(IMAGE_VIEW, imageView)
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

    companion object {
        // we can use it, because we see where we get it
        const val DATA = "data"
        const val NAME = "name"
    }

}