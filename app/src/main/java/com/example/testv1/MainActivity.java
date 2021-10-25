package com.example.testv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<movie> movies;
    private List<moviesArray> arr;
    private static final String ENDPOINT ="https://x-mode.co.il/exam/allMovies/allMovies.txt";
    private RequestQueue requestQueue;
    GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    MovieAdapter adapter;
    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();
        arr = new ArrayList<>();

        sv= (SearchView) findViewById(R.id.mSearch);
        //arr..add(new ArrayList<>());
        request();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    public void request(){
        requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("PostActivity", response);
                arr = Arrays.asList(gson.fromJson(response, moviesArray.class));
                RecyclerView recyclerView = findViewById(R.id.recycler);
                Collections.sort(arr.get(0).getMovies());
                adapter = new MovieAdapter(arr.get(0).getMovies());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplication().getApplicationContext()));
                recyclerView.setHasFixedSize(false);
                recyclerView.setAdapter(adapter);
                adapter.setListener(new MovieAdapter.movieListener() {
                    @Override
                    public void movieClicked(int position, View view) {
                        Intent i=new Intent(MainActivity.this,SecondActivity.class);
                        i.putExtra("movie",adapter.getMovies().get(position));
                        startActivity(i);
                    }
                });

            }

        };

        Response.ErrorListener onPostsError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PostActivity", error.toString());
            }
        };

        StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT, onPostsLoaded, onPostsError);
        requestQueue.add(request);
    }

}