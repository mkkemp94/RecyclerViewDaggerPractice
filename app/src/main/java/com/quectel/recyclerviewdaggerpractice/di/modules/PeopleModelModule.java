package com.quectel.recyclerviewdaggerpractice.di.modules;

import com.quectel.recyclerviewdaggerpractice.di.scopes.ApplicationScope;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;
import com.quectel.recyclerviewdaggerpractice.ui.people.PeopleModel;
import com.quectel.recyclerviewdaggerpractice.ui.people.PeopleModelContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PeopleModelModule
{
    @Provides
    @ApplicationScope
    PeopleModelContract providePeopleModel(APIInterface apiInterface)
    {
        return new PeopleModel(apiInterface);
    }
}
