package com.quectel.recyclerviewdaggerpractice.ui.people;

import android.util.Log;

import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleModel implements PeopleModelContract
{
    private APIInterface mApiInterface;
    
    @Inject
    public PeopleModel(APIInterface apiInterface)
    {
        mApiInterface = apiInterface;
    }
    
    @Override
    public void loadPeople(final PeopleCallback peopleCallback)
    {
        mApiInterface
                .getPeople("json")
                .enqueue(new Callback<StarWars>()
                {
                    @Override
                    public void onResponse(@NonNull Call<StarWars> call,
                                           @NonNull Response<StarWars> response)
                    {
                        Log.i("Interactor", "Response");
                        
                        if (response.isSuccessful())
                        {
                            Log.i("Interactor", "Success");
                            
                            if (response.body() != null)
                            {
                                peopleCallback.onSuccess(response.body().results);
                            }
                            else
                            {
                                peopleCallback.onFailure("Null body");
                                
                            }
                        }
                        else
                        {
                            Log.i("Interactor", "Fail");
                            
                            peopleCallback.onFailure("Unsuccessful");
                        }
                    }
                    
                    @Override
                    public void onFailure(@NonNull Call<StarWars> call,
                                          @NonNull Throwable t)
                    {
                        Log.i("Interactor", "Failure: " + t.toString());
                        t.printStackTrace();
                        
                        peopleCallback.onFailure("Failure: " + t.toString());
                    }
                });
    }
}
