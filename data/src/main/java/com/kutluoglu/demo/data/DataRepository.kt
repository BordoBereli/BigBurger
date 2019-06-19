package com.kutluoglu.demo.data

import com.kutluoglu.demo.data.mapper.CartMapper
import com.kutluoglu.demo.data.mapper.CatalogMapper
import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.data.source.DataStoreFactory
import com.kutluoglu.demo.domain.model.CartItem
import com.kutluoglu.demo.domain.model.CatalogItem
import com.kutluoglu.demo.domain.repository.BigBurgerRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-01
 *
 */
class DataRepository @Inject constructor(
    private val factory: DataStoreFactory,
    private val mapper: CatalogMapper,
    private val cartMapper: CartMapper
) : BigBurgerRepository {
    override fun getCatalogs(): Single<List<CatalogItem>> {
        return factory.retreiveCacheDataStore().isExpired()
            .flatMap {expired ->
                if (expired) factory.retreiveRemoteDataStore().getCatalogs()
                else factory.retreiveCacheDataStore().getCatalogs()
            }.map {
                saveCatalogs(it).subscribe()
                val itemList =  mutableListOf<CatalogItem>()
                it.map {catalog ->
                    itemList.add(mapper.mapFromEntityToDomainModel(catalog))
                }
                itemList
            }
    }

    private fun saveCatalogs(list: List<Catalog>) : Single<Boolean> {
        return factory.retreiveCacheDataStore().isExpired()
            .flatMap { expired ->
                if(expired) factory.retreiveCacheDataStore().saveCatalogs(list)
                else Single.just(true)
            }
    }

    override fun addProduct(product: CartItem): Single<Boolean> {
        return factory.retreiveCacheDataStore().addProduct(cartMapper.mapToEntityFromDomainModel(product))
    }

    override fun updateProduct(product: CartItem): Single<Boolean> {
        return factory.retreiveCacheDataStore().updateProduct(cartMapper.mapToEntityFromDomainModel(product))
    }

    override fun removeProduct(product: CartItem): Single<Boolean> {
        return factory.retreiveCacheDataStore().removeProduct(cartMapper.mapToEntityFromDomainModel(product))
    }

    override fun getProducts(): Flowable<List<CartItem>> {
        return factory.retreiveCacheDataStore().getProducts()
            .map { list ->
                val cartItems = mutableListOf<CartItem>()
                list.forEach {
                    cartItems.add(cartMapper.mapFromEntityToDomainModel(it))
                }
                cartItems
            }
    }
}