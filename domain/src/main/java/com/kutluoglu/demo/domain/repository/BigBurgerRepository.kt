package com.kutluoglu.demo.domain.repository

import com.kutluoglu.demo.domain.model.CartItem
import com.kutluoglu.demo.domain.model.CatalogItem
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by F.K. on 2019-05-01
 *
 */

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */

interface BigBurgerRepository {
    fun getCatalogs() : Single<List<CatalogItem>>
    fun addProduct(product: CartItem) : Single<Boolean>
    fun updateProduct(product: CartItem) : Single<Boolean>
    fun getProducts() : Flowable<List<CartItem>>
    fun removeProduct(product: CartItem) : Single<Boolean>
}