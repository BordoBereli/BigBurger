package com.vibuy.legacy.bigburger.main.features.catalog

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vibuy.legacy.bigburger.R
import com.vibuy.legacy.bigburger.utils.extensions.inflate
import com.vibuy.legacy.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-04
 *
 */
class CatalogRvAdapter (
    private val catalogListener: (CatalogItemView, View) -> Unit
) : RecyclerView.Adapter<CatalogItemHolder>() {

    private val catalogList = mutableListOf<CatalogItemView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemHolder {
        return CatalogItemHolder(parent.inflate(R.layout.catalog_item))
    }

    override fun getItemCount() = catalogList.size

    override fun onBindViewHolder(holder: CatalogItemHolder, position: Int) {
        holder.bindViewData(catalogList[position], catalogListener)
    }

    fun setRvData(list: List<CatalogItemView>) {
        catalogList.clear()
        catalogList.addAll(list)
        notifyDataSetChanged()
    }
}