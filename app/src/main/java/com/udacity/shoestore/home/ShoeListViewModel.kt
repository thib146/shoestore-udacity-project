package com.udacity.shoestore.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = listOf(
            Shoe("Air Sneaker 146", 43.0, "Nayke", ""),
            Shoe("Rando 3000", 42.5, "ProHiker", ""),
            Shoe("BoolderMax", 41.0, "SlimyGeiko", ""))
    }

}