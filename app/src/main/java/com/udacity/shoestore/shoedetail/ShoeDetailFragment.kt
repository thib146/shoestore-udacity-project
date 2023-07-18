package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.home.ShoeListFragmentArgs
import com.udacity.shoestore.models.Shoe
import java.lang.NumberFormatException

class ShoeDetailFragment: Fragment() {

    private lateinit var viewModel: ShoeDetailViewModel

    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]

        // Get args using by navArgs property delegate
        val shoeListFragmentArgs by navArgs<ShoeListFragmentArgs>()
        val shoeList: Array<Shoe> = shoeListFragmentArgs.shoeList
        viewModel.addNewShoe(shoeList)

        binding.shoeDetailCancelButton.setOnClickListener {
            val latestShoeList: Array<Shoe> = viewModel.shoeList.value?.toTypedArray() ?: arrayOf()
            view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(latestShoeList))
        }

        binding.shoeDetailSaveButton.setOnClickListener {
            saveShoeAndNavigateToShoeListScreen()
        }

        return binding.root
    }

    private fun saveShoeAndNavigateToShoeListScreen() {
        val shoeName = binding.shoeNameInput.text.toString()
        val shoeCompany = binding.shoeCompanyInput.text.toString()
        val shoeSizeString = binding.shoeSizeInput.text.toString()

        if (shoeName.isEmpty() ||  shoeCompany.isEmpty() || shoeSizeString.isEmpty()) {
            Toast.makeText(requireActivity(), getString(R.string.shoe_detail_fields_empty_error_message), Toast.LENGTH_LONG).show()
        } else {
            try {
                val shoeSizeDouble = shoeSizeString.toDouble()
                val newShoe = Shoe(shoeName, shoeSizeDouble, shoeCompany, "")
                var latestShoeList: Array<Shoe> = viewModel.shoeList.value?.toTypedArray() ?: arrayOf()
                latestShoeList = latestShoeList.plus(newShoe)

                view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(latestShoeList))
            } catch (e: NumberFormatException) {
                Toast.makeText(requireActivity(), getString(R.string.shoe_detail_size_field_wrong_format), Toast.LENGTH_LONG).show()
            }

        }
    }
}