package com.quectel.recyclerviewdaggerpractice.di.modules;

import com.quectel.recyclerviewdaggerpractice.adapter.RecyclerViewAdapter;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ActivityScope;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivity;

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
    public RecyclerViewAdapter getStarWarsPeopleList(RecyclerViewAdapter.ClickListener clickListener)
    {
        return new RecyclerViewAdapter(clickListener);
    }
    
    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity)
    {
        return mainActivity;
    }
}
