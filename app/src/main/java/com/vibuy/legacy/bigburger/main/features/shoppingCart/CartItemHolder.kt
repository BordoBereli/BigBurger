package com.vibuy.legacy.bigburger.main.features.shoppingCart

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vibuy.legacy.bigburger.R
import com.vibuy.legacy.bigburger.utils.extensions.setImageWithUrl
import com.vibuy.legacy.presentation.models.CartItemView
import kotlinx.android.synthetic.main.checkout_item.view.*

/**
 * Created by F.K. on 2019-05-05
 *
 */
open class CartItemHolder(private val item: View) : RecyclerView.ViewHolder(item) {
    private val thumbnail: ImageView = item.findViewById(R.id.cart_thumbnail)
    private val title: TextView = item.findViewById(R.id.title)
    private val subTotalAmount: TextView = item.findViewById(R.id.tv_subTotal_amount)
    private val quantity: TextView = item.findViewById(R.id.quantity)
    private val increaseQuantity: Button = item.findViewById(R.id.btn_plus)
    private val decreaseQuantity: Button = item.findViewById(R.id.btn_minus)

    fun bindViewData(cartItemView: CartItemView, cartListener: (CartItemView, View) -> Unit) {
        thumbnail.setImageWithUrl(cartItemView.thumbnail)
        title.text = cartItemView.title
        subTotalAmount.text = cartItemView.subTotal
        quantity.text = cartItemView.quantity.toString()
        increaseQuantity.setOnClickListener {
            val increasedQuantity = ++cartItemView.quantity
            cartListener(cartItemView.copy(quantity = increasedQuantity), item)
        }
        decreaseQuantity.setOnClickListener {
            if (cartItemView.quantity > 0) {
                val decreasedQuantity = --cartItemView.quantity
                cartListener(cartItemView.copy(quantity = decreasedQuantity), item)
            }
        }
    }

    fun getForegroundView() : View {
        return item.checkout_view_foreground
    }
}