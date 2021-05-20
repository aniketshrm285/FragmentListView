package com.example.task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var listOfNames : ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : SharedViewModel by activityViewModels()

        setupListView()

        viewModel.wantListOfCheckedItems.observe(requireActivity(), {
            if(it){
                val indexes = ArrayList<Int>()
                for (i in 0..lv_names.count) {
                    if (lv_names.isItemChecked(i)) {
                        indexes.add(i)
                        lv_names.setItemChecked(i, false)
                    }
                }
                if (indexes.size > 0)
                    viewModel.removeSelectedIndexesFromList(indexes)
                else if(listOfNames.size > 0)
                    Toast.makeText(requireContext(), "Please select at least one name.", Toast.LENGTH_SHORT).show()
                viewModel.wantListOfCheckedItems.value = false
            }
        })

        viewModel.getListOfNames.observe(requireActivity(),{
            listOfNames.clear()
            for(el in it){
                listOfNames.add(el)
            }
            arrayAdapter.notifyDataSetChanged()
        })
    }

    private fun setupListView() {
        listOfNames = arrayListOf()
        arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_multiple_choice,listOfNames)
        lv_names.adapter = arrayAdapter
    }
}