package com.kutluoglu.demo.data.repository

import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.data.model.ShoppingCartProduct
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-05-01
 *
 */
interface Cache {
    /**
     * Save list of [Catalog] to the cache
     */
    fun saveCatalogs(catalogs: List<Catalog>) : Single<Boolean>

    /**
     * Retrieve a [List] of [Catalog] from the cache
     */
    fun getCatalogs() : Single<List<Catalog>>

    /**
     * Add a [ShoppingCartProduct] to shopping chart to cache it
     */
    fun addProduct(product: ShoppingCartProduct) : Single<Boolean>

    /**
     * Update a [ShoppingCartProduct] to shopping chart to cache it
     */
    fun updateProduct(product: ShoppingCartProduct) : Single<Boolean>

    /**
     * Remove a [ShoppingCartProduct] from shopping chart
     */
    fun removeProduct(product: ShoppingCartProduct) : Single<Boolean>
//    fun removeProduct(product: ShoppingCartProduct) : Completable

    /**
     * Retrieve a [List] of [ShoppingCartProduct] from shopping chart of the cache
     */
    fun getProducts() : Flowable<List<ShoppingCartProduct>>

    /**
     * Set a point in time at when the cache was last updated.
     */
    fun setLastCacheTime()

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time.
     */
    fun isExpired() : Boolean
}