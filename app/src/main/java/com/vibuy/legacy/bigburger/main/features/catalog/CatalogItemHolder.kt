package com.vibuy.legacy.bigburger.main.features.catalog

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vibuy.legacy.bigburger.R
import com.vibuy.legacy.bigburger.utils.extensions.setImageWithUrl
import com.vibuy.legacy.presentation.models.CatalogItemView

/**
 * Created by F.K. on 2019-05-04
 *
 */
open class CatalogItemHolder(private val item: View) : RecyclerView.ViewHolder(item) {
    private val thumbnail: ImageView = item.findViewById(R.id.catalog_item_thumbnail)
    private val title: TextView = item.findViewById(R.id.catalog_item_title)
    private val description: TextView = item.findViewById(R.id.catalog_item_description)
    private val addButton: Button = item.findViewById(R.id.catalog_item_add)

    fun bindViewData(catalog: CatalogItemView, catalogListener: (CatalogItemView, View) -> Unit) {
        thumbnail.setImageWithUrl(catalog.thumbnail)
        title.text = catalog.title + " " + catalog.price
        description.text = catalog.description
        addButton.setOnClickListener { catalogListener(catalog, item) }
    }
}