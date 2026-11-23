package com.example.artspace;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.NonNull;

import com.example.artspace.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;
    private final int[] images = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    private final String[] titles = {
            "The Starry Night",
            "Mona Lisa",
            "The Persistence of Memory",
            "Girl with a Pearl Earring",
            "The Great Wave off Kanagawa",
            "Water Lilies"
    };

    private final String[] artists = {
            "Vincent van Gogh",
            "Leonardo da Vinci",
            "Salvador Dalí",
            "Johannes Vermeer",
            "Hokusai",
            "Claude Monet"
    };

    private final String[] years = {
            "1889",
            "1503",
            "1931",
            "1665",
            "1831",
            "1906"
    };

    private int currentIndex = 0;
    private static final String KEY_INDEX = "current_artwork_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateArtworkDisplay();

        mainBinding.next.setOnClickListener(v -> {
            if (currentIndex < images.length - 1) {
                currentIndex++;
                updateArtworkDisplay();
            }
        });

        mainBinding.pre.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtworkDisplay();
            }
        });

        mainBinding.image1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("ARTWORK_TITLE", titles[currentIndex]);
            intent.putExtra("ARTIST_NAME", artists[currentIndex]);
            intent.putExtra("CREATION_YEAR", years[currentIndex]);
            startActivity(intent);
        });
    }

    private void updateArtworkDisplay() {
        mainBinding.counter.setText((currentIndex + 1) + " / " + images.length);
        mainBinding.image1.setImageResource(images[currentIndex]);
        mainBinding.title.setText(titles[currentIndex]);
        mainBinding.para.setText(artists[currentIndex] + " (" + years[currentIndex] + ")");

        mainBinding.pre.setEnabled(currentIndex > 0);
        mainBinding.next.setEnabled(currentIndex < images.length - 1);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }
}
