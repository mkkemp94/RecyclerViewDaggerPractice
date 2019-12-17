package com.quectel.recyclerviewdaggerpractice;

import android.app.Activity;
import android.app.Application;

import com.quectel.recyclerviewdaggerpractice.di.components.ApplicationComponent;
import com.quectel.recyclerviewdaggerpractice.di.components.DaggerApplicationComponent;
import com.quectel.recyclerviewdaggerpractice.di.modules.ContextModule;

public class MyApplication extends Application
{
    ApplicationComponent mApplicationComponent;
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        
        mApplicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        mApplicationComponent.injectApplication(this);
    }
    
    public static MyApplication get(Activity activity)
    {
        return (MyApplication) activity.getApplication();
    }
    
    public ApplicationComponent getApplicationComponent()
    {
        return mApplicationComponent;
    }
}
