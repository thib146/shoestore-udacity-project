package com.udacity.shoestore.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf()
    }

    fun addNewShoe(shoeList: Array<Shoe>) {
        _shoeList.value = mutableListOf()
        _shoeList.value?.addAll(shoeList)
    }

    fun addInitialShoeList() {
        _shoeList.value = mutableListOf(
            Shoe("Air Sneaker 146", 43.0, "Nayke", ""),
            Shoe("Rando 3000", 42.5, "ProHiker", ""),
            Shoe("BoolderMax", 41.0, "SlimyGeiko", ""))
    }

}