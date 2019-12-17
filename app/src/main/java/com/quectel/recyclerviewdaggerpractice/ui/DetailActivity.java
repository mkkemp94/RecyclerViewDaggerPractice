package com.quectel.recyclerviewdaggerpractice.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.quectel.recyclerviewdaggerpractice.MyApplication;
import com.quectel.recyclerviewdaggerpractice.R;
import com.quectel.recyclerviewdaggerpractice.di.components.ApplicationComponent;
import com.quectel.recyclerviewdaggerpractice.di.components.DaggerDetailActivityComponent;
import com.quectel.recyclerviewdaggerpractice.di.components.DetailActivityComponent;
import com.quectel.recyclerviewdaggerpractice.di.qualifiers.ApplicationContext;
import com.quectel.recyclerviewdaggerpractice.pojo.Film;
import com.quectel.recyclerviewdaggerpractice.retrofit.APIInterface;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity
{
    DetailActivityComponent mDetailActivityComponent;
    
    @Inject
    public APIInterface mAPIInterface;
    
    @Inject
    @ApplicationContext
    public Context mContext;
    
    TextView mTextView;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        
        mTextView = findViewById(R.id.textView);
        
        String url = getIntent().getStringExtra("url");
    
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mDetailActivityComponent = DaggerDetailActivityComponent.builder()
                                                                .applicationComponent(applicationComponent)
                                                                .build();
        
        mDetailActivityComponent.inject(this);
        
        mAPIInterface.getFilmData(url, "json").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response)
            {
                Film films = response.body();
                String text = "Film name:\n" + films.title + "\nDirector:\n" + films.director;
                mTextView.setText(text);
            }
    
            @Override
            public void onFailure(Call<Film> call, Throwable t)
            {
        
            }
        });
        
    }
}
