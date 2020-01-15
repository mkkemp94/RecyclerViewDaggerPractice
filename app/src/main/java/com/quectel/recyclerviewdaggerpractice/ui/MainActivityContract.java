package com.quectel.recyclerviewdaggerpractice.ui;

import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;

import java.util.List;

public interface MainActivityContract
{
    interface View
    {
        void peopleLoaded(List<StarWars.People> people);
        
        void failedToLoadPeople(String error);
        
        interface RecyclerViewItemClickListener
        {
            void itemClicked(String data);
        }
    }
    
    interface Presenter
    {
        void attachView(MainActivityContract.View mainActivity);
    }
}
