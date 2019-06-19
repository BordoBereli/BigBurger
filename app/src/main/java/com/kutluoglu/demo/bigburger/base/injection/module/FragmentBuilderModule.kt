package com.kutluoglu.demo.bigburger.base.injection.module

import com.kutluoglu.demo.bigburger.main.features.catalog.Catalog
import com.kutluoglu.demo.bigburger.main.features.shoppingCart.ShoppingCart
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by F.K. on 2019-05-02
 *
 * --> contributeCatalogFragment() added by F.K. on 2019-05-04
 * --> contributeShoppingCart() added by F.K. on 2019-05-04
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeCatalogFragment() : Catalog

    @ContributesAndroidInjector
    abstract fun contributeShoppingCart() : ShoppingCart
}