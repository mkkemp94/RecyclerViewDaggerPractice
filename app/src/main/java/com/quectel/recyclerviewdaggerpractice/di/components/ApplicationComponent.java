package com.quectel.recyclerviewdaggerpractice.di.components;

import android.content.Context;

import com.quectel.recyclerviewdaggerpractice.MyApplication;
import com.quectel.recyclerviewdaggerpractice.di.modules.ContextModule;
import com.quectel.recyclerviewdaggerpractice.di.modules.PeopleModelModule;
import com.quectel.recyclerviewdaggerpractice.di.modules.RetrofitModule;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ApplicationContext;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ApplicationScope;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;
import com.quectel.recyclerviewdaggerpractice.ui.people.PeopleModelContract;

import dagger.Component;

/**
 * Fields used in our Activity/Application.
 */
@ApplicationScope
@Component(modules = { ContextModule.class, RetrofitModule.class, PeopleModelModule.class })
public interface ApplicationComponent
{
    APIInterface getRetrofitAPIInterface();
    
    @ApplicationContext
    Context getApplicationContext();
    
    PeopleModelContract getPeopleModel();
    
    void injectApplication(MyApplication myApplication);
}
