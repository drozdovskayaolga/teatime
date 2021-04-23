package com.example.teaceremony.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.Analytics
import com.example.teaceremony.application.Application
import com.example.teaceremony.viewmodel.DetailsViewModel
import com.example.teaceremony.viewmodel.DetailsViewModelFactory
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_drinks.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*


class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val detailsViewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory((requireActivity().application as Application).repository)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailsViewModel.details.observe(viewLifecycleOwner) {
            val tv = requireView().findViewById<TextView>(R.id.tv_details)
            tv.text = it.info

            val tb = requireView().findViewById<TextView>(R.id.toolbar_title)
            tb.text = it.name
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val details = requireArguments().getInt("details",0)
        detailsViewModel.typeOfData(details)

        val type = requireArguments().getInt("type",0)

        when (type) {
            1 -> {
                iv_details.setImageResource(R.drawable.cocktailmainphoto)
            }
            2 -> {
                iv_details.setImageResource(R.drawable.teamainphoto)
            }
            else -> {
                iv_details.setImageResource(R.drawable.coffeemainphoto)
            }
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, Analytics.Events.details_screen)
        }
    }

}

