package com.kutluoglu.demo.bigburger.base.injection.module

import com.kutluoglu.demo.bigburger.main.MainActivity

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
