package com.kutluoglu.demo.bigburger.base.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kutluoglu.demo.bigburger.base.injection.ViewModelKey
import com.kutluoglu.demo.presentation.viewModels.BBViewModelFactory
import com.kutluoglu.demo.presentation.viewModels.addProduct.ProductViewModel
import com.kutluoglu.demo.presentation.viewModels.catalog.CatalogViewModel
import com.kutluoglu.demo.presentation.viewModels.shoppingCart.CartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by F.K. on 2019-05-02
 *
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: BBViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    abstract fun bindCatalogViewModel(catalogViewModel: CatalogViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(cartViewModel: CartViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViwModel(productViewModel: ProductViewModel) : ViewModel

}