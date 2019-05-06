package com.vibuy.legacy.data.repository

import com.vibuy.legacy.data.model.Catalog
import com.vibuy.legacy.data.model.ShoppingCartProduct
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-05-01
 *
 */

/**
 * Interface defining methods for the data operations related to BigBurger.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface DataStore {
    fun saveCatalogs(catalogs: List<Catalog>) : Single<Boolean>
    fun getCatalogs() : Single<List<Catalog>>
    fun addProduct(product: ShoppingCartProduct) : Single<Boolean>
    fun updateProduct(product: ShoppingCartProduct): Single<Boolean>
    fun removeProduct(product: ShoppingCartProduct): Single<Boolean>
//    fun removeProduct(product: ShoppingCartProduct): Completable
    fun getProducts() : Flowable<List<ShoppingCartProduct>>
    fun isExpired() : Single<Boolean>
}