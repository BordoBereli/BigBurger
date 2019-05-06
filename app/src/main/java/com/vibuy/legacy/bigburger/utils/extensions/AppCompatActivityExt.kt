package com.vibuy.legacy.bigburger.utils.extensions

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.vibuy.legacy.bigburger.R

/**
 * Created by F.K. on 2019-05-06
 *
 */

/**
 * Actionbar Utils
 */
fun AppCompatActivity.setupActionbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
}

fun AppCompatActivity.setToogle(drawerLayout: DrawerLayout, toolbar: Toolbar) {
    val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close )
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
}