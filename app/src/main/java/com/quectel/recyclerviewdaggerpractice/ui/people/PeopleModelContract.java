package com.quectel.recyclerviewdaggerpractice.ui.people;

import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;

import java.util.List;

public interface PeopleModelContract
{
    interface PeopleCallback
    {
        void onSuccess(List<StarWars.People> people);
        
        void onFailure(String error);
    }
    
    void loadPeople(PeopleCallback peopleCallback);
}
