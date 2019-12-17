package com.quectel.recyclerviewdaggerpractice.di.components;

import android.content.Context;

import com.quectel.recyclerviewdaggerpractice.MyApplication;
import com.quectel.recyclerviewdaggerpractice.di.modules.ContextModule;
import com.quectel.recyclerviewdaggerpractice.di.modules.RetrofitModule;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ApplicationContext;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ApplicationScope;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;

import dagger.Component;

/**
 * Fields used in our Activity/Application.
 */
@ApplicationScope
@Component(modules = { ContextModule.class, RetrofitModule.class })
public interface ApplicationComponent
{
    public APIInterface getApiInterface();
    
    @ApplicationContext
    public Context getContext();
    
    public void injectApplication(MyApplication myApplication);
}
