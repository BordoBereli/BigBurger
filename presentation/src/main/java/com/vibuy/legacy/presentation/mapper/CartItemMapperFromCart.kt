package com.vibuy.legacy.presentation.mapper

import com.vibuy.legacy.domain.model.CartItem
import com.vibuy.legacy.presentation.models.CartItemView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class CartItemMapperFromCart @Inject constructor()
    : DomainMapper<CartItemView, CartItem> {
    override fun mapFromView(type: CartItemView): CartItem {
        return CartItem(type.ref, type.title, type.description, type.thumbnail, type.price, type.quantity)
    }
}