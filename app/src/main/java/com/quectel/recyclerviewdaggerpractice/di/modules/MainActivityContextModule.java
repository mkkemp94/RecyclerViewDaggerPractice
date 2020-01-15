package com.quectel.recyclerviewdaggerpractice.di.modules;

import android.content.Context;

import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ActivityContext;
import com.quectel.recyclerviewdaggerpractice.di.scopes.ActivityScope;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivity;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivityContract;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivityPresenter;
import com.quectel.recyclerviewdaggerpractice.ui.people.PeopleModelContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule
{
    private MainActivity mMainActivity;
    private Context mContext;
    
    public MainActivityContextModule(MainActivity mainActivity)
    {
        mMainActivity = mainActivity;
        mContext = mainActivity;
    }
    
    @Provides
    @ActivityScope
    MainActivity provideMainActivity()
    {
        return mMainActivity;
    }

    @Provides
    @ActivityScope
    MainActivityContract.Presenter providePresenter(PeopleModelContract peopleInteractor) {
        return new MainActivityPresenter(peopleInteractor);
    }
    
    @Provides
    @ActivityScope
    @ActivityContext
    Context provideContext()
    {
        return mContext;
    }
}