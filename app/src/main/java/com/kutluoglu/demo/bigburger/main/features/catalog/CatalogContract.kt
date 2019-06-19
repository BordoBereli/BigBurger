package com.kutluoglu.demo.bigburger.main.features.catalog

import android.view.View
import com.kutluoglu.demo.bigburger.base.ViewContract
import com.kutluoglu.demo.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-04
 *
 */
interface CatalogContract : ViewContract {
    fun catalogClicked(catalog: CatalogItemView, view: View)
    fun setCatalogRv(adapter: CatalogRvAdapter)
}