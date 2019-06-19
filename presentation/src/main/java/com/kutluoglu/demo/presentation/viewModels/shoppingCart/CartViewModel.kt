package com.kutluoglu.demo.presentation.viewModels.shoppingCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.demo.domain.interactor.GetCartUseCase
import com.kutluoglu.demo.domain.interactor.RemoveCartUseCase
import com.kutluoglu.demo.domain.interactor.UpdateCartUseCase
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.data.ResourceState
import com.kutluoglu.demo.presentation.mapper.CartItemMapperFromCart
import com.kutluoglu.demo.presentation.mapper.CartItemViewMapper
import com.kutluoglu.demo.presentation.models.CartItemView
import com.kutluoglu.demo.presentation.viewModels.shoppingCart.cartInnerClasses.*
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class CartViewModel @Inject constructor(
    private val cartUseCase: GetCartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val removeCartUseCase: RemoveCartUseCase,
    private val mapperFromCatalog: CartItemViewMapper,
    private val mapperFromCart: CartItemMapperFromCart
) : ViewModel(), ShoppingCartContract {
    override fun showCartItems() {
        cartLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        cartUseCase.execute(ShoppingCartSubscriber(mapperFromCatalog))
    }

    override fun removeCartItem(cartItemView: CartItemView) {
        removeCartUseCase.execute(RemoveProductSubscriber(), mapperFromCart.mapFromView(cartItemView))
    }

    override fun updateCartItemQuantity(cartItemView: CartItemView) {
        return updateCartUseCase.execute(UpdateProductSubscriber(), mapperFromCart.mapFromView(cartItemView))
    }

    override fun processCheckout(): LiveData<Resource<Boolean>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getShoppingCartLiveData(): LiveData<Resource<List<CartItemView>>> {
        return cartLiveData
    }

    override fun getTotalAmount(): LiveData<Resource<String>> {
        return totalAmountLiveData
    }

    override fun onCleared() {
        super.onCleared()
        cartUseCase.dispose()
    }

}