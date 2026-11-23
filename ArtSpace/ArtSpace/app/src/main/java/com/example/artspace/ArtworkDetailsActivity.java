package com.example.artspace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ArtworkDetailsActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_artwork_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView detailsTv = findViewById(R.id.details_tv);
        Button websiteBtn = findViewById(R.id.website_btn);
        Button backBtn = findViewById(R.id.back_btn);

        Intent intent = getIntent();
        String title = intent.getStringExtra("ARTWORK_TITLE");
        String artist = intent.getStringExtra("ARTIST_NAME");
        String year = intent.getStringExtra("CREATION_YEAR");

        String detailsText = "Title: " + title + "\n"
                + "Artist: " + artist + "\n"
                + "Year: " + year;
        detailsTv.setText(detailsText);

        websiteBtn.setOnClickListener(v -> {
            String query = ((title != null ? title : "") + " " + (artist != null ? artist : "")).trim();
            Uri webpage = Uri.parse("https://www.google.com/search?q=" + Uri.encode(query));
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });

        backBtn.setOnClickListener(v -> finish());
    }
}

