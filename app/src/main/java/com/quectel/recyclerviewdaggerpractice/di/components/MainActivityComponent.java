package com.quectel.recyclerviewdaggerpractice.di.components;

import android.content.Context;

import com.quectel.recyclerviewdaggerpractice.ui.MainActivity;
import com.quectel.recyclerviewdaggerpractice.di.modules.AdapterModule;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ActivityContext;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ActivityScope;

import dagger.Component;

/**
 * This component has access to the ApplicationComponent dependencies too.
 */
@ActivityScope
@Component(modules = AdapterModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent
{
    @ActivityContext
    Context getContext();
    
    void injectMainActivity(MainActivity mainActivity);
}
