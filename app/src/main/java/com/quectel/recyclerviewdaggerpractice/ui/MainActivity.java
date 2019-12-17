package com.quectel.recyclerviewdaggerpractice.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.quectel.recyclerviewdaggerpractice.MyApplication;
import com.quectel.recyclerviewdaggerpractice.R;
import com.quectel.recyclerviewdaggerpractice.adapter.RecyclerViewAdapter;
import com.quectel.recyclerviewdaggerpractice.di.components.ApplicationComponent;
import com.quectel.recyclerviewdaggerpractice.di.components.DaggerMainActivityComponent;
import com.quectel.recyclerviewdaggerpractice.di.components.MainActivityComponent;
import com.quectel.recyclerviewdaggerpractice.di.modules.MainActivityContextModule;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ActivityContext;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ApplicationContext;
import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener
{
    private RecyclerView mRecyclerView;
    
    MainActivityComponent mMainActivityComponent;
    
    @Inject
    public RecyclerViewAdapter mRecyclerViewAdapter;
    
    @Inject
    public APIInterface mAPIInterface;
    
    @Inject
    @ApplicationContext
    public Context mContext;
    
    @Inject
    @ActivityContext
    public Context mActivityContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                                                            .mainActivityContextModule(new MainActivityContextModule(this))
                                                            .applicationComponent(applicationComponent)
                                                            .build();
        
        mMainActivityComponent.injectMainActivity(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        
        mAPIInterface.getPeople("json").enqueue(new Callback<StarWars>() {
            @Override
            public void onResponse(Call<StarWars> call, Response<StarWars> response)
            {
                populateRecyclerView(response.body().results);
            }
    
            @Override
            public void onFailure(Call<StarWars> call, Throwable t)
            {
        
            }
        });
    }
    
    private void populateRecyclerView(List<StarWars.People> response)
    {
        mRecyclerViewAdapter.setData(response);
    }
    
    @Override
    public void launchIntent(String url)
    {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mActivityContext, DetailActivity.class).putExtra("url", url));
    }
}
