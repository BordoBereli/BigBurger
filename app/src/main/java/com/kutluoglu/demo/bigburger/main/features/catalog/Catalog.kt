package com.kutluoglu.demo.bigburger.main.features.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kutluoglu.demo.bigburger.R
import com.kutluoglu.demo.bigburger.base.BaseFragment
import com.kutluoglu.demo.bigburger.utils.extensions.setBBTitle
import com.kutluoglu.demo.presentation.data.ResourceState
import com.kutluoglu.demo.presentation.models.CatalogItemView
import com.kutluoglu.demo.presentation.viewModels.addProduct.ProductViewModel
import com.kutluoglu.demo.presentation.viewModels.catalog.CatalogViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_catalog.*

class Catalog : BaseFragment(), CatalogContract {
    private lateinit var catalogViewModel: CatalogViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var rvAdapter: CatalogRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setDrawerEnabled(true)
        initializeUI()
        initializeViewModel()
    }

    override fun initializeUI() {
        rvAdapter = CatalogRvAdapter { catalog: CatalogItemView, view: View -> catalogClicked(catalog, view)}
        setCatalogRv(rvAdapter)
    }

    override fun catalogClicked(catalog: CatalogItemView, view: View) {
        productViewModel.addProduct(catalog)
        Toast.makeText(context, "${catalog.title} added to the cart.", Toast.LENGTH_SHORT).show()
    }

    override fun setCatalogRv(adapter: CatalogRvAdapter){
        rv_catalogs.setHasFixedSize(true)
        rv_catalogs.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_catalogs.adapter = adapter
    }

    override fun initializeViewModel() {
        catalogViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CatalogViewModel::class.java)

        productViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductViewModel::class.java)

        observeCatalogLiveData()

    }

    private fun observeCatalogLiveData() {
        catalogViewModel.getCatalogLiveData().observe(this, Observer { resource ->
            when (resource.status) {
                ResourceState.LOADING -> {
                    showSpinner()
                }
                ResourceState.SUCCESS -> {
                    dismissSpinner()
                    Log.e("Data", resource.data.toString())
                    resource.data?.let {
                        rvAdapter.setRvData(it)
                    }
                }
                ResourceState.ERROR -> {
                    dismissSpinner()
                    Log.e("Data", resource.message)
                }
            }
        })

        catalogViewModel.showCatalogs()
    }

    override fun onResume() {
        super.onResume()
        act.toolbar.setBBTitle(getString(R.string.catalog))
    }
}
