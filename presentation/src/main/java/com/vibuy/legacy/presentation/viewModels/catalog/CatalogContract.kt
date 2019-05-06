package com.vibuy.legacy.presentation.viewModels.catalog

import androidx.lifecycle.LiveData
import com.vibuy.legacy.presentation.data.Resource
import com.vibuy.legacy.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-02
 *
 */
interface CatalogContract {
    fun getCatalogLiveData() : LiveData<Resource<List<CatalogItemView>>>
    fun showCatalogs()
}