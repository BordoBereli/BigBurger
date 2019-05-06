package com.vibuy.legacy.presentation.mapper

import com.vibuy.legacy.domain.model.CatalogItem
import com.vibuy.legacy.presentation.models.CatalogItemView
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-02
 *
 * --> getCurrency() method added by F.K. on 2019-05-04
 */
open class CatalogViewMapper @Inject constructor()
    : AbstractFormatter(), ViewMapper<List<CatalogItem>, List<CatalogItemView>> {
    override fun mapToView(list: List<CatalogItem>): List<CatalogItemView> {
        val viewList = mutableListOf<CatalogItemView>()
        list.map {type ->
            viewList.add(CatalogItemView(type.ref, type.title, type.description, type.thumbnail, getCurrency(type.price)))
        }
        return viewList
    }

    private fun getCurrency(price: Int): String {
        // Return value as ###,###.## ₺
        val formattedPrice = BigDecimal(price).movePointLeft(2)
        return formatter.format(formattedPrice) + " " + currency.getSymbol(turkish)
    }
}