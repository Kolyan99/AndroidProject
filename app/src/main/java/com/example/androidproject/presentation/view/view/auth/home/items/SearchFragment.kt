package com.example.androidproject.presentation.view.view.auth.home.items

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.App
import com.example.androidproject.R
import com.example.androidproject.databinding.FragmentSearchBinding
import com.example.androidproject.presentation.view.view.auth.home.items.service.MusicPlayer
import com.example.androidproject.utils.BaseFragment
import com.squareup.picasso.Picasso
import javax.inject.Inject


class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            (requireActivity().applicationContext as App).provideAppComponent().inject(this)

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

            animationSet.playTogether(x, y)
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
                requireActivity().startForegroundService(
                    Intent(
                        requireContext(),
                        MusicPlayer::class.java
                    )
                )

            }


            binding.btnStop.setOnClickListener {
                requireActivity().stopService(Intent(requireContext(), MusicPlayer::class.java))


            }

            binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    viewModel.findItem(p0 ?: "")
                    return false
                }

            })

            viewModel.item.observe(viewLifecycleOwner) {
                binding.description.text = it.description
                Picasso.get().load(Uri.parse(it.image)).into(binding.image)
            }
            runHandler()

        }

    private fun runHandler(){
        var backgroundHandler: Handler? = null

        //Creating a background thread.
        val backgroundThread = Thread {
            //Creating a Looper for this thread.
            Looper.prepare()

            //Looper.myLooper() gives you Looper for current Thread.
            val myLooper = Looper.myLooper()!!

            //Creating a Handler for given Looper object.
            backgroundHandler = Handler(myLooper) { msg ->

                //Processing incoming messages for this Handler.
                //Receiving extras from Message
                val bundle: Bundle? = msg.data

                Log.d("", "Handler:: Extras: ${bundle}")

                Log.d("", "Handler:: Background Thread ID ${Thread.currentThread().id}")

                //myLooper.quit()
                true
            }

            Looper.loop()
        }
        backgroundThread.start()


        //Click listener on a Button
        binding.btnStart.setOnClickListener {
            Log.d("", "Handler:: UI Thread ID ${Thread.currentThread().id}")

            //Executing code on backgroundThread using Handler.
            backgroundHandler!!.post {
                //Here, you'll note that Thread's ID is of backgroundThread.
                Log.d("", "Handler:: Background Thread ID ${Thread.currentThread().id}")
            }

            // Now, sending data on backgroundThread using Message object. Handler's handleMessage(msg: Message?) method will receive this Message and perform appropriate action.
            val extras = Bundle()
            extras.putInt("PRICE", 100)
            extras.putString("PRODUCT_NAME", "Table Lamp")

            val message = Message.obtain(backgroundHandler)
            message.data = extras

            backgroundHandler?.sendMessage(message)
        }
    }
}

