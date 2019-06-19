package com.kutluoglu.demo.data.mapper

import com.kutluoglu.demo.data.model.ShoppingCartProduct
import com.kutluoglu.demo.domain.model.CartItem
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