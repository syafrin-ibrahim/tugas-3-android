package com.example.mysubmission3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mysubmission3.R;
import com.example.mysubmission3.model.Movie;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> Movies = new ArrayList<>();
    private onItemClickCallBack ocb;

    public void setData(ArrayList<Movie> items){
        Movies.clear();
        Movies.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {

       Movie m = Movies.get(position);
        holder.txtJudul.setText(m.getJudul());
        holder.txtSinopsis.setText(m.getDesc());
        String url_image = "https://image.tmdb.org/t/p/w185" + m.getImg_path();
        Glide.with(holder.itemView.getContext())
                .load(url_image)
                .placeholder(R.color.colorAccent)
                .dontAnimate()
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocb.onItemClicked(Movies.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJudul, txtSinopsis;
        private ImageView imgPhoto;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            txtJudul = itemView.findViewById(R.id.item_title);
            txtSinopsis = itemView.findViewById(R.id.item_description);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }


    }

    public void filterMovie(ArrayList<Movie> newMovie){
        Movies = new ArrayList<>();
        Movies.addAll(newMovie);
        notifyDataSetChanged();
    }

    public void setOnItemClickCallBack(onItemClickCallBack onitemclickcallback){
        this.ocb = onitemclickcallback;
    }
    public interface onItemClickCallBack{
        void onItemClicked(Movie data);
    }
}