package com.quectel.recyclerviewdaggerpractice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quectel.recyclerviewdaggerpractice.R;
import com.quectel.recyclerviewdaggerpractice.pojo.StarWars;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private List<StarWars.People> mData;
    private RecyclerViewAdapter.ClickListener mClickListener;
    
    @Inject
    public RecyclerViewAdapter(ClickListener clickListener)
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
        private TextView txtName;
        private TextView txtBirthYear;
        private ConstraintLayout constraintLayoutContainer;
        
        ViewHolder(View itemView)
        {
            super(itemView);
            
            txtName = itemView.findViewById(R.id.txtName);
            txtBirthYear = itemView.findViewById(R.id.txtBirthYear);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);
            
            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    mClickListener.launchIntent(mData.get(getAdapterPosition()).films.get(0));
                }
            });
        }
    }
    
    public interface ClickListener {
        void launchIntent(String fileName);
    }
    
    public void setData(List<StarWars.People> data)
    {
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
