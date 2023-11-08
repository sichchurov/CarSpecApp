package com.shchurovsi.carspecapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, AppSubcomponentModule::class])
interface ApplicationComponent {

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

    fun activityComponent(): ActivityComponent.Factory
}
