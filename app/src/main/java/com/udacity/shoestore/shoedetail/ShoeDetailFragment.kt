package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.ActivityViewModel
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import java.lang.NumberFormatException

class ShoeDetailFragment: Fragment() {

    private lateinit var activityViewModel: ActivityViewModel

    private lateinit var binding: FragmentShoeDetailsBinding

    var shoeName: String = ""
    var shoeCompany: String = ""
    var shoeSizeString: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        binding.shoeDetailFragment = this

        activityViewModel = (activity as MainActivity).viewModel

        binding.shoeDetailCancelButton.setOnClickListener {
            view?.findNavController()?.navigateUp()
        }

        binding.shoeDetailSaveButton.setOnClickListener {
            saveShoeAndNavigateToShoeListScreen()
        }

        return binding.root
    }

    private fun saveShoeAndNavigateToShoeListScreen() {
        if (shoeName.isEmpty() ||  shoeCompany.isEmpty() || shoeSizeString.isEmpty()) {
            Toast.makeText(requireActivity(), getString(R.string.shoe_detail_fields_empty_error_message), Toast.LENGTH_LONG).show()
        } else {
            try {
                val shoeSizeDouble = shoeSizeString.toDouble()
                val newShoe = Shoe(shoeName, shoeSizeDouble, shoeCompany, "")
                activityViewModel.addNewShoe(newShoe)

                view?.findNavController()?.navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            } catch (e: NumberFormatException) {
                Toast.makeText(requireActivity(), getString(R.string.shoe_detail_size_field_wrong_format), Toast.LENGTH_LONG).show()
            }
        }
    }
}