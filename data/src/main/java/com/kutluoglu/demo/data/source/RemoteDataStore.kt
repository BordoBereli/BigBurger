package com.kutluoglu.demo.data.source

import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.data.model.ShoppingCartProduct
import com.kutluoglu.demo.data.repository.DataStore
import com.kutluoglu.demo.data.repository.Remote
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

open class RemoteDataStore @Inject constructor(
    private val remote: Remote
) : DataStore {

    /**
     * Retrieve a [List] of [Catalog] from the remote API Service
     */
    override fun getCatalogs(): Single<List<Catalog>> {
        return remote.getCatalogs()
    }

    override fun saveCatalogs(catalogs: List<Catalog>): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support saveCatalogs() operation!!!")
    }

    override fun addProduct(product: ShoppingCartProduct): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support addProduct() operation!!!")
    }

    override fun updateProduct(product: ShoppingCartProduct): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support updateProduct() operation!!!")
    }

    override fun removeProduct(product: ShoppingCartProduct): Single<Boolean> {
//    override fun removeProduct(product: ShoppingCartProduct): Completable {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support removeProduct() operation!!!")
    }

    override fun getProducts(): Flowable<List<ShoppingCartProduct>> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support getProducts() operation!!!")
    }

    override fun isExpired(): Single<Boolean> {
        throw UnsupportedOperationException("RemoteDataStore doesn`t support isExpired() operation!!!")
    }
}