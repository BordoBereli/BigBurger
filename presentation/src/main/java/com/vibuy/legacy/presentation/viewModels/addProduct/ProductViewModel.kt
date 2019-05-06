package com.vibuy.legacy.presentation.viewModels.addProduct

import androidx.lifecycle.ViewModel
import com.vibuy.legacy.domain.interactor.AddProductUseCase
import com.vibuy.legacy.presentation.mapper.CartItemMapperFromCatalog
import com.vibuy.legacy.presentation.models.CatalogItemView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class ProductViewModel @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val cartItemMapper: CartItemMapperFromCatalog
) : ViewModel(), ProductContract {
    override fun addProduct(catalogItemView: CatalogItemView) {
        addProductUseCase.execute(ProductSubscriber(), cartItemMapper.mapFromView(catalogItemView))
    }

    override fun onCleared() {
        super.onCleared()
        addProductUseCase.dispose()
    }
}