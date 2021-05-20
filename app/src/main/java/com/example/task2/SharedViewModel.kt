package com.example.task2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val listOfNames = MutableLiveData<ArrayList<String>>()
    val getListOfNames : LiveData<ArrayList<String>>
    get() = listOfNames

    val wantListOfCheckedItems = MutableLiveData<Boolean>()

    init {
        wantListOfCheckedItems.value = false
        listOfNames.value = arrayListOf()

    }


    fun addNameToList(name: String){
        val oldList = listOfNames.value
        oldList!!.add(name)
        listOfNames.value = oldList!!
    }
    fun removeSelectedIndexesFromList(indexes : ArrayList<Int>){
        val oldList = listOfNames.value
        for(i in indexes.asReversed()){
            oldList!!.removeAt(i)
        }
        listOfNames.value = oldList!!
    }

}