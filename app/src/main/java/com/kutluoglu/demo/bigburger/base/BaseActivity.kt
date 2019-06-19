package com.kutluoglu.demo.bigburger.base

import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.kutluoglu.demo.bigburger.R
import com.kutluoglu.demo.bigburger.utils.BadgeDrawable
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity(), DrawerLocker {

    override fun setDrawerEnabled(enabled: Boolean) {
        val lockMode = if (enabled) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        drawer_layout.setDrawerLockMode(lockMode)
    }

    fun setBadgeCount(icon: LayerDrawable, count: Int) {
        val reuse = icon.findDrawableByLayerId(R.id.ic_badge)
        val badge = if (reuse != null && reuse is BadgeDrawable) reuse
        else BadgeDrawable(applicationContext)

        badge.setCount(count.toString())
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_badge, badge)
    }
}
