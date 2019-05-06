package com.vibuy.legacy.presentation.viewModels.addProduct

import com.vibuy.legacy.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-05
 *
 */
interface ProductContract {
    fun addProduct(catalogItemView: CatalogItemView)
}