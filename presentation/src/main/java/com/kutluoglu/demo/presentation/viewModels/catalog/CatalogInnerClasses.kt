package com.kutluoglu.demo.presentation.viewModels.catalog

import androidx.lifecycle.MutableLiveData
import com.kutluoglu.demo.domain.model.CatalogItem
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.data.ResourceState
import com.kutluoglu.demo.presentation.mapper.CatalogViewMapper
import com.kutluoglu.demo.presentation.models.CatalogItemView
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by F.K. on 2019-05-02
 *
 */

val catalogLiveData: MutableLiveData<Resource<List<CatalogItemView>>> = MutableLiveData()
class CatalogSubscriber(private val mapper: CatalogViewMapper) : DisposableSingleObserver<List<CatalogItem>>() {
    override fun onSuccess(catalogViews: List<CatalogItem>) {
        handleSuccessCase(mapper.mapToView(catalogViews))
    }

    override fun onError(error: Throwable) {
        handleErrorCase(error)
    }

    private fun handleSuccessCase(catalogViews: List<CatalogItemView>) {
        if(catalogViews.isEmpty()) handleErrorCase(Throwable("The Catalog List is Empty!!!"))
        else {
            catalogLiveData.postValue(Resource(ResourceState.SUCCESS, catalogViews,null))
        }
    }

    private fun handleErrorCase(error: Throwable) {
        catalogLiveData.postValue(Resource(ResourceState.ERROR, null, error.message))
    }

}
