package com.kutluoglu.demo.cache.mapper

import com.kutluoglu.demo.cache.database.entity.DbShoppingCartEntity
import com.kutluoglu.demo.data.model.ShoppingCartProduct
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
class ShoppingChartMapper @Inject constructor() : EntityMapper<ShoppingCartProduct , DbShoppingCartEntity> {
    override fun mapToCached(type: ShoppingCartProduct): DbShoppingCartEntity {
        return DbShoppingCartEntity(type.ref, type.title, type.description, type.thumbnail, type.price, type.quantity)
    }

    override fun mapFromCached(type: DbShoppingCartEntity): ShoppingCartProduct {
        return ShoppingCartProduct(type.ref, type.title, type.description, type.thumbnail, type.price, type.quantity)
    }
}