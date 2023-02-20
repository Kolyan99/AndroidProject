package com.example.androidproject.presentation.view.view.auth.home.items

import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentFavoritesBinding
import com.example.androidproject.databinding.FragmentLoginBinding
import com.example.androidproject.presentation.view.view.auth.home.items.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favAdapter: FavoritesAdapter

    private val viewModel: FavoritesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favAdapter = FavoritesAdapter()
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyclerView.adapter = favAdapter

        viewModel.getFavorites()

        viewModel.favorites.observe(viewLifecycleOwner) {
            favAdapter.submitList(it)
        }


//        var locationManager: LocationManager? = null
//        locationManager = requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager?
//        try {
//            locationManager?.requestLocationUpdates(
//               GPS_PROVIDER,
//                0.0f,
//                0.0f,
//                locationListener
//            )
//        }catch (e: Exception){
//            Log.w("error", "While accessing location")
//        }
//
//    }
//
//    private val locationListener = LocationListener{
//        Toast.makeText(requireContext(), "log: ${it.longitude} lat: ${it.latitude}", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun setMessage(){
//        val intent = Intent("MY_ACTION")
//    }

    }
}
