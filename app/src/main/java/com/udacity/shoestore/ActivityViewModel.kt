package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ActivityViewModel: ViewModel() {

    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf(
            Shoe("Air Sneaker 146", 43.0, "Nayke", ""),
            Shoe("Rando 3000", 42.5, "ProHiker", ""),
            Shoe("BoolderMax", 41.0, "SlimyGeiko", ""))
    }

    fun addNewShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }
}