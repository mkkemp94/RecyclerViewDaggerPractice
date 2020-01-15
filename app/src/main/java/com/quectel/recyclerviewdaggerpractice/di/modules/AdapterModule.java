package com.quectel.recyclerviewdaggerpractice.di.modules;

import com.quectel.recyclerviewdaggerpractice.adapter.RecyclerViewAdapter;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ActivityScope;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivity;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivityContract;

import dagger.Module;
import dagger.Provides;

/**
 * Used to create the RecyclerViewAdapter from the POJO data.
 */
@Module(includes = {MainActivityContextModule.class})
public class AdapterModule
{
    @Provides
    @ActivityScope
    RecyclerViewAdapter getStarWarsPeopleList(MainActivityContract.View.RecyclerViewItemClickListener clickListener)
    {
        return new RecyclerViewAdapter(clickListener);
    }
    
    @Provides
    @ActivityScope
    MainActivityContract.View.RecyclerViewItemClickListener getClickListener(MainActivity mainActivity)
    {
        return mainActivity;
    }
}
