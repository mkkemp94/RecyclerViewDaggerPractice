package com.quectel.recyclerviewdaggerpractice.di.modules;

import android.content.Context;

import com.quectel.recyclerviewdaggerpractice.ui.MainActivity;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ActivityContext;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule
{
    private MainActivity mMainActivity;
    
    public Context mContext;
    
    public MainActivityContextModule(MainActivity mainActivity)
    {
        mMainActivity = mainActivity;
        mContext = mainActivity;
    }
    
    @Provides
    @ActivityScope
    public MainActivity provideMainActivity()
    {
        return mMainActivity;
    }
    
    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext()
    {
        return mContext;
    }
}