package com.kutluoglu.demo.cache

import com.kutluoglu.demo.cache.database.db.BigBurgerDb
import com.kutluoglu.demo.cache.database.entity.DbCatalogEntity
import com.kutluoglu.demo.cache.mapper.CatalogMapper
import com.kutluoglu.demo.cache.mapper.ShoppingChartMapper
import com.kutluoglu.demo.data.model.Catalog
import com.kutluoglu.demo.data.model.ShoppingCartProduct
import com.kutluoglu.demo.data.repository.Cache
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */

class BBCacheImp @Inject constructor (
    private val bigBurgerDb: BigBurgerDb,
    private val bbSharedPreference: BBSharedPreference,
    private val catalogMapper: CatalogMapper,
    private val shoppingChartMapper: ShoppingChartMapper
) : Cache {
    private val EXPIRATION_TIME = (24 * 60 * 60 * 1000).toLong() // 24 Hours

    override fun saveCatalogs(catalogs: List<Catalog>): Single<Boolean> {
        return Single.defer {
            val dbCatalogEntityList = mutableListOf<DbCatalogEntity>()
            catalogs.forEach {
                dbCatalogEntityList.add(catalogMapper.mapToCached(it))
            }

            val saved = bigBurgerDb.catalogDao().insertAllCatalogs(dbCatalogEntityList)
            setLastCacheTime()
            if (saved.isNotEmpty()) Single.just(true) else Single.just(false)
        }

    }

    override fun getCatalogs(): Single<List<Catalog>> {
        return bigBurgerDb.catalogDao().getCatalogs()
            .map {dbCatalogList ->
                val catalogList = mutableListOf<Catalog>()
                dbCatalogList.forEach { entity ->
                    catalogList.add(catalogMapper.mapFromCached(entity))
                }
                catalogList
            }
    }

    override fun addProduct(product: ShoppingCartProduct): Single<Boolean> {
        return Single.defer {
            val saved = bigBurgerDb.shoppingCartDao().insertProduct(shoppingChartMapper.mapToCached(product)) > 0
            Single.just(saved)
        }
    }

    override fun updateProduct(product: ShoppingCartProduct): Single<Boolean> {
        return Single.defer {
            val saved = bigBurgerDb.shoppingCartDao().updateProduct(shoppingChartMapper.mapToCached(product)) > 0
            Single.just(saved)
        }
    }

    override fun removeProduct(product: ShoppingCartProduct): Single<Boolean> {
        return Single.defer {
            val saved = bigBurgerDb.shoppingCartDao().deleteProduct(shoppingChartMapper.mapToCached(product)) > 0
            Single.just(saved)
        }
    }

    override fun getProducts(): Flowable<List<ShoppingCartProduct>> {
        return bigBurgerDb.shoppingCartDao().getAllProducts()
            .map { dbList ->
                val cartList = mutableListOf<ShoppingCartProduct>()
                dbList.forEach {
                    cartList.add(shoppingChartMapper.mapFromCached(it))
                }
                cartList
            }
    }

    override fun setLastCacheTime() {
        bbSharedPreference.lastCacheTime = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = bbSharedPreference.lastCacheTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }
}