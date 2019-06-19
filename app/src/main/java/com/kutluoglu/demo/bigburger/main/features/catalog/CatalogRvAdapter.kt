package com.kutluoglu.demo.bigburger.main.features.catalog

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutluoglu.demo.bigburger.R
import com.kutluoglu.demo.bigburger.utils.extensions.inflate
import com.kutluoglu.demo.presentation.models.CatalogItemView

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