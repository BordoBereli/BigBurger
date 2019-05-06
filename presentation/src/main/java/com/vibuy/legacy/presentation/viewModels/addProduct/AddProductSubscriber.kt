package com.vibuy.legacy.presentation.viewModels.addProduct

import androidx.lifecycle.MutableLiveData
import com.vibuy.legacy.presentation.data.Resource
import com.vibuy.legacy.presentation.data.ResourceState
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-05-06
 *
 */

val addProductLiveData: MutableLiveData<Resource<Boolean>> = MutableLiveData()
class ProductSubscriber : DisposableSingleObserver<Boolean>() {
    override fun onSuccess(value: Boolean) {
        handleSuccessCase(value)
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(value: Boolean) {
        if(value) addProductLiveData.postValue(Resource(ResourceState.SUCCESS, value, null))
        else {
            handleErrorCase(Throwable("The Cart Item was not added to DB!!!"))
        }
    }

    private fun handleErrorCase(error: Throwable) {
        addProductLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }

}
