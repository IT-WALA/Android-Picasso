package com.example.picasso;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.StatsSnapshot;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity {

    Button btnLoadImage, btnClearImageview, btnLoadImageAsBitmap;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadImage = findViewById(R.id.btnLoadImage);
        btnClearImageview = findViewById(R.id.btnClearImageview);
        btnLoadImageAsBitmap = findViewById(R.id.btnLoadImageAsBitmap);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);

        btnLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                Picasso
                        .get()
                        .load("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                                "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                                "=format&fit=crop&w=750&q=80")
                        .resize(200, 200)
                        .centerCrop()
                        .placeholder(R.drawable.ic_baseline_account_circle)
                        .error(R.drawable.ic_baseline_error)
                        .tag("tag")
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_STORE)
                        .noFade()
                        .into(imageView, new Callback() {
                            @Override public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override public void onError(Exception e) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });

        btnLoadImageAsBitmap.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Target target = new Target() {
                    @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        imageView.setImageDrawable(errorDrawable);
                    }

                    @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
                        imageView.setImageDrawable(placeHolderDrawable);
                    }
                };

                Picasso
                        .get()
                        .load("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                                "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                                "=format&fit=crop&w=750&q=80")
                        .resize(200, 200)
                        .centerCrop()
                        .placeholder(R.drawable.ic_baseline_account_circle)
                        .error(R.drawable.ic_baseline_error)
                        .tag("tag")
                        .into(target);
            }
        });

        btnClearImageview.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                imageView.setImageDrawable(null);
            }
        });
    }
}