package com.vibuy.legacy.presentation.viewModels.shoppingCart

import androidx.lifecycle.LiveData
import com.vibuy.legacy.presentation.data.Resource
import com.vibuy.legacy.presentation.models.CartItemView

/**
 * Created by F.K. on 2019-05-05
 *
 */
interface ShoppingCartContract {
    fun showCartItems()
    fun removeCartItem(cartItemView: CartItemView)
    fun updateCartItemQuantity(cartItemView: CartItemView)
    fun processCheckout() : LiveData<Resource<Boolean>>
    fun getShoppingCartLiveData() : LiveData<Resource<List<CartItemView>>>
    fun getTotalAmount() : LiveData<Resource<String>>
}