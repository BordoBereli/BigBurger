package com.vibuy.legacy.domain.model

/**
 * Created by F.K. on 2019-05-01
 *
 */

/**
 * Representation for a [CatalogItem] fetched from an external layer data source
 *
 */

data class CatalogItem (
    val ref: String, // 13
    val title: String, // Apple
    val description: String, // Eat apples!
    val thumbnail: String, // http://legacy.vibuy.com/images/13.png
    val price: Int // 70
)