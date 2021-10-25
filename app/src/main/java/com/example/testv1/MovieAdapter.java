package com.example.testv1;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {
    private List<movie> movies;
    private MovieAdapter.movieListener listener;
    private List<movie> filterList;
    CustomFilter filter;

    public MovieAdapter(List<movie> movies) {
        this.movies = movies;
        this.filterList = movies;
    }

    public void setListener(MovieAdapter.movieListener listener) { this.listener = listener; }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter();
        }
        return filter;
    }

    public List<movie> getMovies(){
        return movies;
    }

    public interface movieListener{
        void movieClicked(int position, View view);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView year;

        public MovieViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_card);
            year = itemView.findViewById(R.id.year_card);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.movieClicked(getAdapterPosition(),itemView);
                }
            });


        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);
        MovieAdapter.MovieViewHolder movieViewHolder = new MovieAdapter.MovieViewHolder(view);
        return movieViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        movie movie = movies.get(position);
        holder.name.setText(movie.getName());
        holder.year.setText(String.valueOf(movie.getYear()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<movie> filters = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getName().toUpperCase().contains(constraint)) {
                        filters.add(filterList.get(i));
                    }
                }

                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            movies = (ArrayList<movie>) results.values;
            notifyDataSetChanged();
        }
    }

}


