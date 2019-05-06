package com.vibuy.legacy.presentation.viewModels.shoppingCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vibuy.legacy.domain.interactor.GetCartUseCase
import com.vibuy.legacy.domain.interactor.RemoveCartUseCase
import com.vibuy.legacy.domain.interactor.UpdateCartUseCase
import com.vibuy.legacy.presentation.data.Resource
import com.vibuy.legacy.presentation.data.ResourceState
import com.vibuy.legacy.presentation.mapper.CartItemMapperFromCart
import com.vibuy.legacy.presentation.mapper.CartItemViewMapper
import com.vibuy.legacy.presentation.models.CartItemView
import com.vibuy.legacy.presentation.viewModels.shoppingCart.cartInnerClasses.*
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