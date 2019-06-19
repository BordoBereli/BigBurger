package com.kutluoglu.demo.bigburger.main

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.navigation.NavigationView
import com.kutluoglu.demo.bigburger.R
import com.kutluoglu.demo.bigburger.base.BaseActivity
import com.kutluoglu.demo.bigburger.utils.extensions.setToogle
import com.kutluoglu.demo.bigburger.utils.extensions.setupActionbar
import com.kutluoglu.demo.presentation.data.ResourceState
import com.kutluoglu.demo.presentation.viewModels.BBViewModelFactory
import com.kutluoglu.demo.presentation.viewModels.shoppingCart.CartViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector,
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    @Inject lateinit var viewModelFactory: BBViewModelFactory
    private lateinit var cartViewModel: CartViewModel

    var cartMenuItem: MenuItem? = null
    var cartMenuItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        setupNavigationEnvironment()
        initializeViewModel()

    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    private fun setupNavigationEnvironment() {
        setupActionbar(toolbar)
        setToogle(drawer_layout, toolbar)

        setupNavigation()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment)

        // Tie nav graph to items in nav drawer
        NavigationUI.setupWithNavController(nav_view, navController)

        // Tie actionbar/toolbar items to navController
        appBarConfig = AppBarConfiguration(navController.graph, drawer_layout)

        // Top Level Destinations
        appBarConfig.topLevelDestinations.add(R.id.catalog)

        // Tie all together
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)
    }

    private fun initializeViewModel() {
        cartViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CartViewModel::class.java)

        observeShoppingCartCount()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        cartMenuItem = menu.findItem(R.id.shoppingCart)
        setBadgeCount(cartMenuItem?.icon as LayerDrawable, cartMenuItemCount)
        cartViewModel.showCartItems()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.catalog -> {

            }
            R.id.shoppingCart -> {
                navController.navigate(R.id.action_catalogFragment_to_shoppingChart)
            }
        }
        item.isChecked = true
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun observeShoppingCartCount() {
        cartViewModel.getShoppingCartLiveData().observe(this, Observer { resource ->
            when (resource.status) {
                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    if(cartMenuItem != null) {
                        resource.data?.let {
                            setBadgeCount(cartMenuItem?.icon as LayerDrawable, it.size)
                        }
                    }
                }
                ResourceState.ERROR -> {}
            }
        })
    }
}
