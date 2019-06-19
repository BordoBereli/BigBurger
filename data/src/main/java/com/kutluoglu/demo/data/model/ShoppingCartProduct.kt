package com.kutluoglu.demo.data.model


import com.google.gson.annotations.SerializedName

data class ShoppingCartProduct (
    @SerializedName("ref") val ref: String, // 13
    @SerializedName("title") val title: String, // Apple
    @SerializedName("description") val description: String, // Eat apples!
    @SerializedName("thumbnail") val thumbnail: String, // http://legacy.vibuy.com/images/13.png
    @SerializedName("price") val price: Float, // 70
    @SerializedName("quantity") var quantity: Int // 1
)