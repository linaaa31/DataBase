package com.example.cinemadatabase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemadatabase.db.CinemaEntity;
import com.example.cinemadatabase.db.FilmEntity;

import java.util.List;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.MyViewHolder>
{

    private Context context;
    private List<FilmEntity> filmList;
    private List<CinemaEntity> cinemaList;

    public FilmListAdapter(Context context) {
        this.context = context;
    }

    public void setFilmList (List < FilmEntity > filmList, List < CinemaEntity > cinemaList){
        this.filmList = filmList;
        this.cinemaList = cinemaList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmListAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent,
                                                            int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder,int position){
        holder.tvFilmName.setText(this.filmList.get(position).filmName);
        holder.tvDuration.setText(this.filmList.get(position).duration);
        holder.tvCinemaAddress.setText(this.cinemaList.get(position).address);
    }

    @Override
    public int getItemCount () {
        return this.filmList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFilmName;
        TextView tvDuration;
        TextView tvCinemaAddress;

        public MyViewHolder(View view) {
            super(view);
            tvFilmName = view.findViewById(R.id.tvFilmName);
            tvDuration = view.findViewById(R.id.tvDuration);
            tvCinemaAddress= view.findViewById(R.id.tvCinemaAddress);
        }
    }
}
