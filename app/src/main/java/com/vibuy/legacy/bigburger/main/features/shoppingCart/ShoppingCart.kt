package com.vibuy.legacy.bigburger.main.features.shoppingCart


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.vibuy.legacy.bigburger.R
import com.vibuy.legacy.bigburger.base.BaseFragment
import com.vibuy.legacy.bigburger.utils.RvItemTouchHelper
import com.vibuy.legacy.presentation.data.ResourceState
import com.vibuy.legacy.presentation.models.CartItemView
import com.vibuy.legacy.presentation.viewModels.shoppingCart.CartViewModel
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCart : BaseFragment(), CartConract {
    private lateinit var cartViewModel: CartViewModel
    private lateinit var rvAdapter: CartRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setDrawerEnabled(false)
        initializeUI()
        initializeViewModel()
    }

    override fun initializeUI() {
        rvAdapter = CartRvAdapter{cartItem: CartItemView, view: View -> quantityItemClick(cartItem, view)}
        setCartRV(rvAdapter)
        place_order.setOnClickListener {
            placeOrder()
        }
    }

    override fun quantityItemClick(cartItem: CartItemView, view: View) {
        cartViewModel.updateCartItemQuantity(cartItem)
    }

    override fun placeOrder() {
        Toast.makeText(context, "Thank you for placing your order.", Toast.LENGTH_SHORT).show()
    }

    override fun setCartRV(adapter: CartRvAdapter){
        rv_cart.setHasFixedSize(true)
        rv_cart.layoutManager = LinearLayoutManager(activity)
        rv_cart.adapter = adapter

        rv_cart.itemAnimator = DefaultItemAnimator()

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        val itemTouchHelperCallback = RvItemTouchHelper(0, ItemTouchHelper.LEFT, touchListener)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_cart)
    }

    private val touchListener = object : RvItemTouchHelper.RecyclerItemTouchHelperListener {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
            val cartAdapter = rv_cart.adapter as CartRvAdapter

            val cartView = cartAdapter.cartList[position]

            // Remove cartItemView from the list
            cartAdapter.removeItem(position)

            //Show snack bar
            val snackbar = Snackbar.make(shoppingcart_coordinator, "${cartView.title} deleted.",
                Snackbar.LENGTH_SHORT)
            snackbar.setAction("Undo") {
                cartAdapter.restoreItem(cartView, position)
            }
            snackbar.setActionTextColor(Color.MAGENTA)
            snackbar.addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    // Delete the swiped item if it is not restored
                    if (event != DISMISS_EVENT_ACTION) {
                        cartViewModel.removeCartItem(cartView)
                    }
                }
            })
            snackbar.show()
        }
    }

    override fun initializeViewModel() {
        cartViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CartViewModel::class.java)

        observeShoppingCartLiveData()
        observeTotalAmountChanges()
    }

    private fun observeShoppingCartLiveData() {
        cartViewModel.getShoppingCartLiveData().observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                ResourceState.LOADING -> {
                    showSpinner()
                }
                ResourceState.SUCCESS -> {
                    dismissSpinner()
                    resource.data?.let {
                        rvAdapter.setRvData(it)
                        changeBadgeQuantity(it.size)
                    }
                }
                ResourceState.ERROR -> {
                    dismissSpinner()
                }
            }
        })
        cartViewModel.showCartItems()
    }

    private fun observeTotalAmountChanges() {
        cartViewModel.getTotalAmount().observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    resource.data?.let {
                        tv_total_amount.text = it
                    }
                }
                ResourceState.ERROR -> {
                    resource.data?.let {
                        tv_total_amount.text = it
                    }
                }
            }
        })
    }

    // Hide Shopping Cart menu item
    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        menu?.clear()
    }

}
