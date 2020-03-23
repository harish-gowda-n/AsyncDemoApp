package com.example.asyncdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button download;
    ProgressBar probar;
    ImageView iv;
    String url = "https://file-examples.com/wp-content/uploads/2017/10/file_example_PNG_1MB.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        probar = findViewById(R.id.progressBar);
        download = findViewById(R.id.download);
        download.setOnClickListener(this);
        iv = findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {

        DownloadImage down = new DownloadImage(probar, iv, this);
        down.execute(url);
    }
}
