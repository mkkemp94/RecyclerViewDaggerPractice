package com.quectel.recyclerviewdaggerpractice.di.components;

import com.quectel.recyclerviewdaggerpractice.di.scopes.ActivityScope;
import com.quectel.recyclerviewdaggerpractice.ui.DetailActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface DetailActivityComponent
{
    void inject(DetailActivity detailActivity);
}
