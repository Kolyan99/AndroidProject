package com.example.androidproject.presentation.view.view.auth.home.items

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentSearchBinding
import com.example.androidproject.presentation.view.view.auth.home.items.service.MusicPlayer
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val btn = Button(context)
            btn.background = context?.getDrawable(R.color.purple_500)
            btn.text = context?.getString(R.string.app_name)
            binding.root.addView(btn)


            AnimationUtils.loadAnimation(context, R.anim.rotete_anim).also {
                binding.btnStart.startAnimation(it)
            }

            val animationSet = AnimatorSet()
            val y = ObjectAnimator.ofFloat(binding.btnStart, "scaleY", 5f, 1.5f)
            val x = ObjectAnimator.ofFloat(binding.btnStart, "scaleX", 5f, 1.5f)

            animationSet.playTogether(x,y)
            animationSet.start()


            val translate = ValueAnimator.ofFloat(2f, 1.2f)
            translate.addUpdateListener { animation ->
                val scale = animation.animatedValue.toString().toFloat()
                binding.btnStop.setScaleX(scale)
                binding.btnStop.setScaleY(scale)
            }
            translate.repeatCount = 2
            translate.start()




            binding.btnStart.setOnClickListener {
            requireActivity().startForegroundService(Intent(requireContext(),MusicPlayer::class.java))

        }


        binding.btnStop.setOnClickListener {
            requireActivity().stopService(Intent(requireContext(),MusicPlayer::class.java))


        }

        binding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.findItem(p0?:"")
                return false
            }

        })

        viewModel.item.observe(viewLifecycleOwner){
            binding.description.text = it.description
            Picasso.get().load(Uri.parse(it.image)).into(binding.image)
        }

    }


    }
