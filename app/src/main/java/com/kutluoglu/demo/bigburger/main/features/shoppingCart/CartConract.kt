package com.kutluoglu.demo.bigburger.main.features.shoppingCart

import android.view.View
import com.kutluoglu.demo.bigburger.base.ViewContract
import com.kutluoglu.demo.presentation.models.CartItemView

/**
 * Created by F.K. on 2019-05-05
 *
 */
interface CartConract : ViewContract {
    fun setCartRV(adapter: CartRvAdapter)
    fun quantityItemClick(cartItem: CartItemView, view: View)
    fun placeOrder()
}