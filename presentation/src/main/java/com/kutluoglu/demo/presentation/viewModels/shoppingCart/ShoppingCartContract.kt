package com.kutluoglu.demo.presentation.viewModels.shoppingCart

import androidx.lifecycle.LiveData
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.models.CartItemView

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