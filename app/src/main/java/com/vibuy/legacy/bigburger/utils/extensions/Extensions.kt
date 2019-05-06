package com.vibuy.legacy.bigburger.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.vibuy.legacy.bigburger.base.BaseFragment
import com.vibuy.legacy.bigburger.utils.ProgressSpinner
import kotlinx.android.synthetic.main.app_bar_main.view.*

/**
 * Created by F.K. on 2019-05-04.
 *
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachtoRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachtoRoot)
}

/**
 * Toolbar Extentions
 */

fun Toolbar.setBBTitle(title: String) { toolbar.title= title }

/**
 * Show Spinner Progress Dialog
 */
fun BaseFragment.showProgressSpinner(): AlertDialog = ProgressSpinner(
    this.context!!
).create()

/**
 * ImageView to set Drawable by Resource ID
 */

fun ImageView.setImageWithUrl(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}

/**
 * Set any View visible or gone state
 */

fun View.visibileOrGone(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}




