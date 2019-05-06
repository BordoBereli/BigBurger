package com.vibuy.legacy.presentation.viewModels.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vibuy.legacy.domain.interactor.GetCatalogsUseCase
import com.vibuy.legacy.presentation.data.Resource
import com.vibuy.legacy.presentation.data.ResourceState
import com.vibuy.legacy.presentation.mapper.CatalogViewMapper
import com.vibuy.legacy.presentation.models.CatalogItemView
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