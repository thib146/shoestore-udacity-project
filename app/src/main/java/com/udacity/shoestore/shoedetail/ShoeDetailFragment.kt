package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.home.ShoeListFragmentArgs
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment: Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentShoeDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]

        // TODO: remove this line and get data from Input fields
        val newShoe = Shoe("TestName" + Math.random().toString(), 45.0, "TestCompany", "")

        // Get args using by navArgs property delegate
        val shoeListFragmentArgs by navArgs<ShoeListFragmentArgs>()
        val shoeList: Array<Shoe> = shoeListFragmentArgs.shoeList
        viewModel.addNewShoe(shoeList)

        binding.shoeDetailCancelButton.setOnClickListener {
            val latestShoeList: Array<Shoe> = viewModel.shoeList.value?.toTypedArray() ?: arrayOf()
            view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(latestShoeList))
        }

        binding.shoeDetailSaveButton.setOnClickListener {
            var latestShoeList: Array<Shoe> = viewModel.shoeList.value?.toTypedArray() ?: arrayOf()
            latestShoeList = latestShoeList.plus(newShoe)
            view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(latestShoeList))
        }

        return binding.root
    }
}