package com.kutluoglu.demo.bigburger.base

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.kutluoglu.demo.bigburger.base.injection.Injectable
import com.kutluoglu.demo.bigburger.main.MainActivity
import com.kutluoglu.demo.bigburger.utils.extensions.showProgressSpinner
import com.kutluoglu.demo.presentation.viewModels.BBViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject lateinit var viewModelFactory: BBViewModelFactory
    private var spinnerProgress: AlertDialog? = null
    lateinit var act: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        act  = activity as MainActivity
    }

    fun changeBadgeQuantity(count: Int) {
        act.setBadgeCount(act.cartMenuItem?.icon as LayerDrawable, count)
    }

    fun setDrawerEnabled(enabled: Boolean) {
        act.setDrawerEnabled(enabled)
    }

    fun showSpinner() {
        if(spinnerProgress == null) {
            spinnerProgress = showProgressSpinner()
        }

        spinnerProgress?.let {
            if(!it.isShowing) it.show()
        }

    }

    fun dismissSpinner() {
        spinnerProgress?.let {
            if(it.isShowing) it.dismiss()
        }
    }


    override fun onDestroy() {
        dismissSpinner()
        super.onDestroy()
    }
}
