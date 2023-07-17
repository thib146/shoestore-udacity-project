package com.udacity.shoestore.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment: Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[ShoeListViewModel::class.java]

        setupMenu()

        binding.shoelistFab.setOnClickListener {
            view?.findNavController()?.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.shoeList.observe(this.viewLifecycleOwner, Observer { shoeList ->
            inflateShoeList()
        })

        return binding.root
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoelist_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_item_logout -> {
                        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun inflateShoeList() {
        for (shoeItem in viewModel.shoeList.value!!.toMutableList()) {
            val newShoeItem = createNewShoeItem(shoeItem.name, shoeItem.company, shoeItem.size)
            binding.shoelistItem.addView(newShoeItem)
        }
    }

    private fun createNewShoeItem(shoeName: String, shoeCompany: String, shoeSize: Double): View {
        val view = layoutInflater.inflate(R.layout.view_shoe_item, null)

        view.findViewById<TextView>(R.id.shoe_name).text = shoeName
        view.findViewById<TextView>(R.id.shoe_company).text = shoeCompany
        view.findViewById<TextView>(R.id.shoe_size).text = shoeSize.toString()

        return view
    }

}