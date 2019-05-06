package com.vibuy.legacy.bigburger.base.injection.module

import com.vibuy.legacy.bigburger.main.features.catalog.Catalog
import com.vibuy.legacy.bigburger.main.features.shoppingCart.ShoppingCart
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