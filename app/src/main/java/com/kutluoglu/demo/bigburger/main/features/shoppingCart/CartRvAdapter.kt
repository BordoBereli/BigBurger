package com.kutluoglu.demo.bigburger.main.features.shoppingCart

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutluoglu.demo.bigburger.R
import com.kutluoglu.demo.bigburger.utils.extensions.inflate
import com.kutluoglu.demo.presentation.models.CartItemView

/**
 * Created by F.K. on 2019-05-05
 *
 * --> remove() and restore() methods added by F.K. on 2019-05-06
 *
 */
class CartRvAdapter (
    private val cartListener: (CartItemView, View) -> Unit
) : RecyclerView.Adapter<CartItemHolder>() {
    val cartList = mutableListOf<CartItemView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        return CartItemHolder(parent.inflate(R.layout.checkout_item))
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        holder.bindViewData(cartList[position], cartListener)
    }

    fun setRvData(list: List<CartItemView>) {
        cartList.clear()
        cartList.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        cartList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(view: CartItemView, position: Int) {
        cartList.add(position, view)
        notifyItemInserted(position)
    }
}