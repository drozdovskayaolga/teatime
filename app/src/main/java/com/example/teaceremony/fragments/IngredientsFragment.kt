package com.example.teaceremony.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drozdovskaya.teaceremony.R
import com.example.teaceremony.adapter.IngredientsAdapter
import com.example.teaceremony.application.Application
import com.example.teaceremony.viewmodel.IngredientsViewModel
import com.example.teaceremony.viewmodel.IngredientsViewModelFactory
import kotlinx.android.synthetic.main.toolbar.*

class IngredientsFragment : Fragment(R.layout.fragment_ingredients) {

    private val searchButton: Button by lazy { requireView().findViewById<Button>(R.id.b_ingredients) }

    private val adapter = IngredientsAdapter{
        onItemCheck()
    }

    private val ingredientsViewModel: IngredientsViewModel by viewModels {
        IngredientsViewModelFactory((requireActivity().application as Application).repository)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ingredientsViewModel.ingredients.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar_title.text = "Поиск по ингредиентам"

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_ingredients)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchButton.setOnClickListener {
            val resultList = adapter.currentList.filter { it.isChecked }.map { it.id }
            setFragmentResult("requestKey", bundleOf("bundleKey" to resultList))
            findNavController().popBackStack()
        }
    }

    private fun onItemCheck(){
        searchButton.isVisible = adapter.currentList.any { it.isChecked }
    }
}