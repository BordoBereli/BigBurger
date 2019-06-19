package com.kutluoglu.demo.presentation.viewModels.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.demo.domain.interactor.GetCatalogsUseCase
import com.kutluoglu.demo.presentation.data.Resource
import com.kutluoglu.demo.presentation.data.ResourceState
import com.kutluoglu.demo.presentation.mapper.CatalogViewMapper
import com.kutluoglu.demo.presentation.models.CatalogItemView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 */
open class CatalogViewModel @Inject constructor (
    private val catalogsUseCase: GetCatalogsUseCase,
    private val mapper: CatalogViewMapper
) : ViewModel(), CatalogContract {

    override fun getCatalogLiveData(): LiveData<Resource<List<CatalogItemView>>> {
        return catalogLiveData
    }
    override fun showCatalogs() {
        catalogLiveData.postValue(Resource(ResourceState.LOADING, null,null))
        return catalogsUseCase.execute(CatalogSubscriber(mapper))
    }

    override fun onCleared() {
        super.onCleared()
        catalogsUseCase.dispose()
    }

}