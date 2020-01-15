package com.quectel.recyclerviewdaggerpractice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quectel.recyclerviewdaggerpractice.R;
import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;
import com.quectel.recyclerviewdaggerpractice.ui.MainActivityContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private List<StarWars.People> mData;
    private MainActivityContract.View.RecyclerViewItemClickListener mClickListener;
    
    @Inject
    public RecyclerViewAdapter(MainActivityContract.View.RecyclerViewItemClickListener clickListener)
    {
        mClickListener = clickListener;
        mData = new ArrayList<>();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.txtName.setText(mData.get(position).name);
        holder.txtBirthYear.setText(mData.get(position).birthYear);
    }
    
    @Override
    public int getItemCount()
    {
        return mData.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.txtName)
        TextView txtName;
        
        @BindView(R.id.txtBirthYear)
        TextView txtBirthYear;
    
        @BindView(R.id.recycler_view_list_row)
        LinearLayout layoutContainer;
        
        ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            layoutContainer.setOnClickListener(
                    (View view) ->
                    {
                        StarWars.People person = mData.get(getAdapterPosition());
                        String data = person.films.get(0);
                        mClickListener.itemClicked(data);
                    });
        }
    }
    
    public void setData(List<StarWars.People> data)
    {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
