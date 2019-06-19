package com.kutluoglu.demo.presentation.viewModels.shoppingCart.cartInnerClasses

import androidx.lifecycle.MutableLiveData
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.data.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-05-06
 *
 */

val removeProductLiveData: MutableLiveData<Resource<Boolean>> = MutableLiveData()
class RemoveProductSubscriber : DisposableSingleObserver<Boolean>() {
    override fun onSuccess(value: Boolean) {
        handleSuccessCase(value)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(value: Boolean) {
        if(value) removeProductLiveData.postValue(Resource(ResourceState.SUCCESS, value, null))
        else {
            handleErrorCase(Throwable("The Cart Item was not removed to DB!!!"))
        }
    }

    private fun handleErrorCase(error: Throwable) {
        removeProductLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }
}
