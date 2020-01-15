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

import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        MainActivityContract.View,
        MainActivityContract.View.RecyclerViewItemClickListener
{
    MainActivityComponent mMainActivityComponent;
    
    @Inject
    public MainActivityContract.Presenter mPresenter;
    
//    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    
    @Inject
    public RecyclerViewAdapter mRecyclerViewAdapter;
    
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
        ButterKnife.bind(this);
        
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                                                            .mainActivityContextModule(new MainActivityContextModule(this))
                                                            .applicationComponent(applicationComponent)
                                                            .build();
        
        mMainActivityComponent.injectMainActivity(this);
    
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPresenter.attachView(this);
    }
    
    @Override
    public void itemClicked(String url)
    {
        Toast.makeText(mContext, "RecyclerView Row selected", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(mActivityContext, DetailActivity.class).putExtra("url", url));
    }
    
    @Override
    public void peopleLoaded(List<StarWars.People> people)
    {
        populateRecyclerView(people);
    }
    
    private void populateRecyclerView(List<StarWars.People> response)
    {
        mRecyclerViewAdapter.setData(response);
    }
    
    @Override
    public void failedToLoadPeople(String error)
    {
        Toast.makeText(mActivityContext, error, Toast.LENGTH_SHORT).show();
    }
}
