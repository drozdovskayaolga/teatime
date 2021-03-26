package com.example.teaceremony.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.adapter.DetailsListAdapter
import com.example.teaceremony.application.Application
import com.example.teaceremony.viewmodel.DetailsListViewModel
import com.example.teaceremony.viewmodel.DetailsListViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_details_list.*
import kotlinx.coroutines.launch

class DetailsListFragment : Fragment(R.layout.fragment_details_list) {

    private val adapter = DetailsListAdapter {
        findNavController().navigate(
            R.id.action_typesOfDrinkFragment_to_drinkDetailsFragment,
            bundleOf("details" to it.id)
        )
    }

    private val type by lazy { requireArguments().getInt("type", 0) }

    private val detailsListViewModel: DetailsListViewModel by viewModels {
        DetailsListViewModelFactory(
            (requireActivity().application as Application).repository,
            type = type
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsListViewModel.detailsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val search = view.findViewById<FloatingActionButton>(R.id.b_search)
        search.setOnClickListener {
            findNavController().navigate(R.id.ingredientsFragment)
        }

        when (type) {
            1 -> {
                search.hide()
                iv_details_list.setImageResource(R.drawable.teamainphoto)
            }
            2 -> {
                search.hide()
                iv_details_list.setImageResource(R.drawable.coffeemainphoto)
            }
            else -> {
                iv_details_list.setImageResource(R.drawable.cocktailmainphoto)
            }
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_details_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setFragmentResultListener("requestKey") { key, bundle ->
            val result = bundle.getIntegerArrayList("bundleKey") ?: return@setFragmentResultListener
            detailsListViewModel.loadDetailsByIngredients(result)
        }
    }
}