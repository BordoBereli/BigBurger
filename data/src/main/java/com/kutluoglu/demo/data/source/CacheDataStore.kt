package com.kutluoglu.demo.data.source

import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.data.model.ShoppingCartProduct
import com.kutluoglu.demo.data.repository.Cache
import com.kutluoglu.demo.data.repository.DataStore
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-01
 *
 */

/**
 * Implementation of the [DataStore] interface to provide a means of communicating
 * with the remote data source
 */

open class CacheDataStore @Inject constructor(
    private val cache: Cache
) : DataStore {

    /**
     * Save list of [Catalog] to the cache
     */
    override fun saveCatalogs(catalogs: List<Catalog>): Single<Boolean> {
        return cache.saveCatalogs(catalogs)
    }

    /**
     * Retrieve a [List] of [Catalog] from the cache
     */
    override fun getCatalogs(): Single<List<Catalog>> {
        return cache.getCatalogs()
    }

    /**
     * Add a [ShoppingCartProduct] to shopping chart to cache it
     */
    override fun addProduct(product: ShoppingCartProduct): Single<Boolean> {
        return cache.addProduct(product)
    }

    /**
     * Update a [ShoppingCartProduct] to shopping chart to cache it
     */
    override fun updateProduct(product: ShoppingCartProduct): Single<Boolean> {
        return cache.updateProduct(product)
    }

    /**
     * Remove a [ShoppingCartProduct] from shopping chart
     */
    override fun removeProduct(product: ShoppingCartProduct): Single<Boolean> {
//    override fun removeProduct(product: ShoppingCartProduct): Completable {
        return cache.removeProduct(product)
    }

    /**
     * Retrieve a [List] of [ShoppingCartProduct] from shopping chart of the cache
     */
    override fun getProducts(): Flowable<List<ShoppingCartProduct>> {
        return cache.getProducts()
    }

    override fun isExpired() : Single<Boolean> {
        return Single.just(cache.isExpired())
    }

}