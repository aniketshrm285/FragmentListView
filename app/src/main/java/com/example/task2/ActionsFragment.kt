package com.example.task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_actions.*

class ActionsFragment : Fragment(R.layout.fragment_actions) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : SharedViewModel by activityViewModels()
        btn_delete.setOnClickListener {
            viewModel.wantListOfCheckedItems.value = true
        }
        btn_add.setOnClickListener {
            val text = et_name.text.toString()
            if(text.isNotEmpty()){
                viewModel.addNameToList(text)
                et_name.setText("")
            }
            else{
                Toast.makeText(requireContext(), "Please enter a name.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}