package com.vibuy.legacy.bigburger.main.features.catalog

import android.view.View
import com.vibuy.legacy.bigburger.base.ViewContract
import com.vibuy.legacy.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-04
 *
 */
interface CatalogContract : ViewContract {
    fun catalogClicked(catalog: CatalogItemView, view: View)
    fun setCatalogRv(adapter: CatalogRvAdapter)
}