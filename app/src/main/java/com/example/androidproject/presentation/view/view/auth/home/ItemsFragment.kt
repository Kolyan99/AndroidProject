package com.example.androidproject.presentation.view.view.auth.home

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
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentItemsBinding
import com.example.androidproject.databinding.FragmentOnboardingBinding
import com.example.androidproject.domain.model.ItemsModel
import com.example.androidproject.presentation.view.view.ItemsPresenter
import com.example.androidproject.presentation.view.view.ItemsView
import com.example.androidproject.presentation.view.view.NavigateWithBundel
import com.example.androidproject.presentation.view.view.adapter.ItemsAdapter
import com.example.androidproject.presentation.view.view.adapter.listener.ItemListener
import com.example.androidproject.utils.BundelConstants.DATA
import com.example.androidproject.utils.BundelConstants.NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemsFragment : Fragment(), ItemListener, ItemsView {

    private var _binding: FragmentItemsBinding? = null
    private val binding: FragmentItemsBinding get() = _binding!!

    private lateinit var itemsAdapter: ItemsAdapter

    @Inject
    lateinit var itemsPresenter: ItemsPresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsPresenter.setView(this)


        itemsAdapter = ItemsAdapter(this)
        //val recucleView = view.findViewById<RecyclerView>(R.id.RecyclerView)
        //recucleView.adapter = itemsAdapter
        binding.RecyclerView.adapter = itemsAdapter

        itemsPresenter.getItems()
    }


    override fun onClick() {
        itemsPresenter.imageViewClicked()
    }

    override fun onElementSelected(name: String, date: String, imageView: Int) {
        itemsPresenter.itemClicked(name, date, imageView)
    }

    override fun itemsReceived(itemsList: List<ItemsModel>) {
        itemsAdapter.submitList(itemsList)
    }

    override fun imageViewClicked(msg: Int) {
        Toast.makeText(context, getString(msg), Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(navigationData: NavigateWithBundel) {
        if (navigationData != null) {
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(NAME, navigationData.name)
            bundle.putString(DATA, navigationData.data.toString())
            bundle.putString(IMAGE_VIEW, navigationData.image)
            detailsFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activity_container, DetailsFragment())
                .addToBackStack(getString(R.string.OnBoarding))
                .commit()
        }
    }
}
