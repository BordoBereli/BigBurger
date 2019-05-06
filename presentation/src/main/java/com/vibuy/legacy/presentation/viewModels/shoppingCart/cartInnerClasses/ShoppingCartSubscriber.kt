package com.vibuy.legacy.presentation.viewModels.shoppingCart.cartInnerClasses

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.vibuy.legacy.domain.model.CartItem
import com.vibuy.legacy.presentation.data.Resource
import com.vibuy.legacy.presentation.data.ResourceState
import com.vibuy.legacy.presentation.mapper.CartItemViewMapper
import com.vibuy.legacy.presentation.models.CartItemView
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by F.K. on 2019-05-06
 *
 */

val cartLiveData: MutableLiveData<Resource<List<CartItemView>>> = MutableLiveData()
val totalAmountLiveData: MutableLiveData<Resource<String>> = MutableLiveData()
class ShoppingCartSubscriber(private val mapper: CartItemViewMapper) : DisposableSubscriber<List<CartItem>>() {
    override fun onNext(list: List<CartItem>?) =
        if(list != null && list.isNotEmpty()) handleSuccessCase(list)
        else handleErrorCase(Throwable("List is Empty!"))

    override fun onError(error: Throwable?) = handleErrorCase(error)

    override fun onComplete() = handleCompleteCase()

    private fun handleSuccessCase(list: List<CartItem>) {
        cartLiveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(list), null))
        totalAmountLiveData.postValue(Resource(ResourceState.SUCCESS, getTotalAmount(list), null))
    }

    private fun getTotalAmount(list: List<CartItem>): String {
        var amount = 0f
        list.forEach {
            amount += (it.price * it.quantity)
        }

        return mapper.getCurrency(amount)
    }

    private fun handleErrorCase(error: Throwable?) {
        cartLiveData.postValue(Resource(ResourceState.ERROR, null, error?.message))
        totalAmountLiveData.postValue(Resource(ResourceState.ERROR, getTotalAmount(emptyList()), null))
    }

    private fun handleCompleteCase() {
        Log.e("ShoppingCartSubscriber", "ShoppingCartSubscriber is completed...")
    }

}