package com.kutluoglu.demo.presentation.viewModels.addProduct

import com.kutluoglu.demo.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-05
 *
 */
interface ProductContract {
    fun addProduct(catalogItemView: CatalogItemView)
}