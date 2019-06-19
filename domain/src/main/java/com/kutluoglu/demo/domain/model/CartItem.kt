package com.kutluoglu.demo.domain.model

/**
 * Created by F.K. on 2019-05-05
 *
 */

/**
 * Representation for a [CartItem] fetched from an external layer data source
 *
 */

data class CartItem (
    val ref: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val price: Float,
    var quantity: Int
)