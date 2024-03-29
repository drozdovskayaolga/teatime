package com.example.teaceremony.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.Analytics
import com.example.teaceremony.adapter.TypesAdapter
import com.example.teaceremony.application.Application
import com.example.teaceremony.viewmodel.TypesViewModel
import com.example.teaceremony.viewmodel.TypesViewModelFactory
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator
import kotlinx.android.synthetic.main.fragment_types.*


class TypesFragment : Fragment(R.layout.fragment_types) {

    private val adapter = TypesAdapter {

        Firebase.analytics.logEvent(Analytics.Events.category_click) {
            param(Analytics.Params.type, it.name)
        }

        findNavController().navigate(
            R.id.action_drinkMainFragment_to_typesOfDrinkFragment,
            bundleOf("type" to it.id)
        )
    }

    private val typesViewModel: TypesViewModel by viewModels {
        TypesViewModelFactory((requireActivity().application as Application).repository)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        typesViewModel.allTypes.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_types)

        recyclerView.adapter = adapter

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        val indefinitePagerIndicator =
            view.findViewById<IndefinitePagerIndicator>(R.id.recyclerview_pager_indicator)
        indefinitePagerIndicator.attachToRecyclerView(recyclerView);

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }
}