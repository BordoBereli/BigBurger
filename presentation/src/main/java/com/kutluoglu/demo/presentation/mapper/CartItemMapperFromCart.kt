package com.kutluoglu.demo.presentation.mapper

import com.kutluoglu.demo.domain.model.CartItem
import com.kutluoglu.demo.presentation.models.CartItemView
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