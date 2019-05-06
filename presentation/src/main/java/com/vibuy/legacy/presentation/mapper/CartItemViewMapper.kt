package com.vibuy.legacy.presentation.mapper

import com.vibuy.legacy.domain.model.CartItem
import com.vibuy.legacy.presentation.models.CartItemView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class CartItemViewMapper @Inject constructor()
    : AbstractFormatter(), ViewMapper<List<CartItem>, List<CartItemView>>{
    override fun mapToView(types: List<CartItem>): List<CartItemView> {
        val viewList = mutableListOf<CartItemView>()
        types.forEach {
            viewList
                .add(CartItemView(it.ref, it.title, it.description, it.thumbnail, it.price, it.quantity, getSubtotal(it)))
        }

        return viewList
    }

    private fun getSubtotal(cartItem: CartItem): String {
        return getCurrency(cartItem.price * cartItem.quantity)
    }

    fun getCurrency(price: Float) : String {
        // Return value as ###,###.## ₺
        return formatter.format(price) + " " + currency.getSymbol(turkish)
    }
}