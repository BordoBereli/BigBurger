package com.kutluoglu.demo.presentation.viewModels.shoppingCart.cartInnerClasses

import androidx.lifecycle.MutableLiveData
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.data.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-05-06
 *
 */

val updateProductLiveData: MutableLiveData<Resource<Boolean>> = MutableLiveData()
class UpdateProductSubscriber : DisposableSingleObserver<Boolean>() {
    override fun onSuccess(value: Boolean) {
        handleSuccessCase(value)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(value: Boolean) {
        if(value) updateProductLiveData.postValue(Resource(ResourceState.SUCCESS, value, null))
        else {
            handleErrorCase(Throwable("The Cart Item was not added to DB!!!"))
        }
    }

    private fun handleErrorCase(error: Throwable) {
        updateProductLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }
}
