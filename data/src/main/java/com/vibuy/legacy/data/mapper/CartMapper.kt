package com.vibuy.legacy.data.mapper

import com.vibuy.legacy.data.model.ShoppingCartProduct
import com.vibuy.legacy.domain.model.CartItem
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
class CartMapper @Inject constructor() : Mapper<ShoppingCartProduct, CartItem> {
    override fun mapFromEntityToDomainModel(type: ShoppingCartProduct): CartItem {
        return CartItem(type.ref, type.title, type.description, type.thumbnail, type.price, type.quantity)
    }

    override fun mapToEntityFromDomainModel(type: CartItem): ShoppingCartProduct {
        return ShoppingCartProduct(type.ref, type.title, type.description, type.thumbnail, type.price, type.quantity)
    }
}