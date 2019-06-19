package com.kutluoglu.demo.presentation.viewModels.catalog

import androidx.lifecycle.LiveData
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-02
 *
 */
interface CatalogContract {
    fun getCatalogLiveData() : LiveData<Resource<List<CatalogItemView>>>
    fun showCatalogs()
}