package com.vibuy.legacy.bigburger.base.injection.component

import android.app.Application
import com.vibuy.legacy.bigburger.BBApp
import com.vibuy.legacy.bigburger.base.injection.module.AppModule
import com.vibuy.legacy.bigburger.base.injection.module.MainActivityModule
import com.vibuy.legacy.bigburger.base.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by F.K. on 2019-05-02
 *
 */

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class, // Because of using AndroidX fragment
        AppModule::class,
        MainActivityModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(bbApp: BBApp)
}