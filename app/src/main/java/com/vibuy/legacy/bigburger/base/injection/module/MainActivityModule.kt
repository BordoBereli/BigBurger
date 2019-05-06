package com.vibuy.legacy.bigburger.base.injection.module

import com.vibuy.legacy.bigburger.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module used to provide dependencies at an activity-level.
 *
 */

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity() : MainActivity
}
