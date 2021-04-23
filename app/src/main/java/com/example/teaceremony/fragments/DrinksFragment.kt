package com.example.teaceremony.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.Analytics
import com.example.teaceremony.adapter.DrinksAdapter
import com.example.teaceremony.application.Application
import com.example.teaceremony.viewmodel.DrinksViewModel
import com.example.teaceremony.viewmodel.DrinksViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_drinks.*
import kotlinx.android.synthetic.main.toolbar.*

class DrinksFragment : Fragment(R.layout.fragment_drinks) {

    private val adapter = DrinksAdapter {

        Firebase.analytics.logEvent(Analytics.Events.drink_click) {
            param(Analytics.Params.drink_name, it.name)
        }

        findNavController().navigate(
            R.id.action_typesOfDrinkFragment_to_drinkDetailsFragment,
            bundleOf("details" to it.id, "type" to it.typeId)
        )
    }

    private val type by lazy { requireArguments().getInt("type", 0) }

    private val drinksViewModel: DrinksViewModel by viewModels {
        DrinksViewModelFactory(
            (requireActivity().application as Application).repository,
            type = type
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drinksViewModel.drinks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            val tb = requireView().findViewById<TextView>(R.id.toolbar_title)
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val search = view.findViewById<FloatingActionButton>(R.id.b_search)
        search.setOnClickListener {
            findNavController().navigate(R.id.ingredientsFragment)

            Firebase.analytics.logEvent(Analytics.Events.search_click, null)
        }

        when (type) {
            1 -> {
                iv_details_list.setImageResource(R.drawable.cocktailmainphoto)
            }
            2 -> {
                search.hide()
                iv_details_list.setImageResource(R.drawable.teamainphoto)
            }
            else -> {
                search.hide()
                iv_details_list.setImageResource(R.drawable.coffeemainphoto)
            }
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_drinks)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getIntegerArrayList("bundleKey") ?: return@setFragmentResultListener
            drinksViewModel.loadDetailsByIngredients(result)
        }
    }
}