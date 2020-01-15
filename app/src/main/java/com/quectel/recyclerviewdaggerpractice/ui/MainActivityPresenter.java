package com.quectel.recyclerviewdaggerpractice.ui;

import android.util.Log;

import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;
import com.quectel.recyclerviewdaggerpractice.ui.people.PeopleModelContract;

import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter
{
    private MainActivityContract.View mView;
    private PeopleModelContract mPeopleInteractor;
    
    public MainActivityPresenter(PeopleModelContract peopleInteractor) {
        mPeopleInteractor = peopleInteractor;
    }
    
    @Override
    public void attachView(MainActivityContract.View mainActivity)
    {
        Log.i("Presenter", "Attach View");
        mView = mainActivity;
        loadPeople();
    }
    
    private void loadPeople()
    {
        mPeopleInteractor.loadPeople(new PeopleModelContract.PeopleCallback() {
            @Override
            public void onSuccess(List<StarWars.People> people)
            {
                mView.peopleLoaded(people);
            }
    
            @Override
            public void onFailure(String error)
            {
                mView.failedToLoadPeople(error);
            }
        });
    }
}
