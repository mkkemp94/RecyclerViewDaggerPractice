package com.quectel.recyclerviewdaggerpractice.di.modules;

import android.content.Context;

import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ApplicationContext;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule
{
    private Context mContext;
    
    public ContextModule(Context context)
    {
        mContext = context;
    }
    
    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext()
    {
        return mContext;
    }
}
