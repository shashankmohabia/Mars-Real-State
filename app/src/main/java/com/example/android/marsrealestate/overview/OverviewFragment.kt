package com.example.android.marsrealestate.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.databinding.FragmentOverviewBinding
import com.example.android.marsrealestate.network.MarsApiFilter

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    private val overviewViewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentOverviewBinding.inflate(inflater)

        setUpBinding()
        setHasOptionsMenu(true)
        setObservers()

        return binding.root
    }

    private fun setUpBinding() {
        binding.apply {
            lifecycleOwner = this@OverviewFragment
            viewModel = overviewViewModel
            photosGrid.adapter = PhotoGridAdapter(OnClickListener { overviewViewModel.displayPropertyDetails(it) })
        }
    }

    private fun setObservers() {
        overviewViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                        OverviewFragmentDirections.actionShowDetail(it))
                overviewViewModel.displayPropertyDetailsComplete()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        overviewViewModel.updateFilter(
                when (item.itemId) {
                    R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                    R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                    else -> MarsApiFilter.SHOW_ALL
                }
        )
        return true
    }
}
