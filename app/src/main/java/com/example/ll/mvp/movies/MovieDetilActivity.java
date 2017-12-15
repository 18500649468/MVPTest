package com.example.ll.mvp.movies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ll.mvp.R;
import com.example.ll.mvp.beans.Movie;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetilActivity extends AppCompatActivity{
    @BindView(R.id.back)
    ImageButton back;

    private ImageView title_iamge;
    private CollapsingToolbarLayout toolbar_layout;
    private TextView content_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detil2);
        ButterKnife.bind(this);
        initview();

    }

    private void initview() {
        title_iamge = (ImageView) findViewById(R.id.boolImage);
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        content_tv = (TextView) findViewById(R.id.content_tv);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movie");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toolbar_layout.setTitle(movie.getTitle());
        Picasso.with(this)
                .load(movie.getImages().getLarge())
                .placeholder(this.getResources().getDrawable(R.mipmap.ic_launcher))
                .into(title_iamge);
        content_tv.setText(movie.getAlt());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
