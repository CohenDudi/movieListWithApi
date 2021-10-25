package com.example.testv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    private String URL;
    movie m;
    TextView nameTv;
    TextView descriptionTv;
    ImageView imgIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameTv = findViewById(R.id.movie_name);
        descriptionTv = findViewById(R.id.movie_description);
        imgIv = findViewById(R.id.movie_img);


        Intent intent = getIntent();
        m = (movie)intent.getSerializableExtra("movie");
        URL = "https://x-mode.co.il/exam/descriptionMovies/"+m.getId()+".txt";
        request();



    }

    public void request(){
        requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("PostActivity", response);
                m = gson.fromJson(response, movie.class);
                nameTv.setText(m.getName());
                descriptionTv.setText(m.getDescription());
                String temp = "https"+m.getImageUrl().substring(4);
                Glide.with(getApplication().getApplicationContext()).load(temp).into(imgIv);
            }
        };

        Response.ErrorListener onPostsError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PostActivity", error.toString());
            }
        };

        StringRequest request = new StringRequest(Request.Method.GET, URL, onPostsLoaded, onPostsError);
        requestQueue.add(request);
    }

}