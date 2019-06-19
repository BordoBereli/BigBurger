package com.kutluoglu.demo.presentation.models

/**
 * Created by F.K. on 2019-05-05
 *
 */

/**
 * Representation for a [CartItemView] instance for this layers Model representation
 */

data class CartItemView (
    val ref: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val price: Float,
    var quantity: Int,
    var subTotal: String
)