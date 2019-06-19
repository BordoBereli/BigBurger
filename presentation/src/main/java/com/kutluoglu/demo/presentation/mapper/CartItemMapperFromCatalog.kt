package com.kutluoglu.demo.presentation.mapper

import com.kutluoglu.demo.domain.model.CartItem
import com.kutluoglu.demo.presentation.models.CatalogItemView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class CartItemMapperFromCatalog @Inject constructor()
    : AbstractFormatter(), DomainMapper<CatalogItemView, CartItem> {
    override fun mapFromView(type: CatalogItemView): CartItem {
        return CartItem(type.ref, type.title, type.description, type.thumbnail, getPrice(type.price), 1)
    }

    private fun getPrice(price: String): Float {
        val regex = """\s+""".toRegex()
        val regexValue = regex.split(price)[0]

        val amount = formatter.parse(regexValue).toFloat()
        return amount
    }
}