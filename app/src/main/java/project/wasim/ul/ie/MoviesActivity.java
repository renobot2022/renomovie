/*
name: Wasim Ghazal Aswad
ID: 17193559
Last update: 13/05/2020
 */

package project.wasim.ul.ie;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class MoviesActivity extends AppCompatActivity {

    TextView director;
    TextView year;
    TextView genre;
    ImageView imageView;
    TextView rating;
    Button trailer;
    Button searchButton;
    TextView movieStory;
    TextView awards;
    EditText searchField;


    String[] movieNames = {"Test"};
    Spinner movieSpinner;

    movieDB MovieDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        awards = (TextView) findViewById(R.id.Awards_text);
        movieSpinner = (Spinner) findViewById(R.id.moviespinner);
        trailer = (Button) findViewById(R.id.button);
        MovieDB = new movieDB(getApplicationContext());
        rating = (TextView) findViewById(R.id.rateText);
        director = (TextView) findViewById(R.id.director);
        year = (TextView) findViewById(R.id.year);
        genre = (TextView) findViewById(R.id.genretext);
        imageView = (ImageView) findViewById(R.id.imageView);
        movieStory = (TextView) findViewById(R.id.movieDetails);
        movieNames = MovieDB.getMovieNames();
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.list_movie, movieNames);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movieSpinner.setAdapter(movieAdapter);
        searchButton = (Button) findViewById(R.id.Search);
        searchField = (EditText) findViewById(R.id.search_text);

        movieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       final int position, long arg3) {
                if (movieNames.length > position) {
                    print(movieNames[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isAvailable()) {
                    print(searchField.getText().toString());
                }

            }

        });

    }

    private boolean isAvailable() {
        if (searchField.getText().toString().contentEquals("")) return false;
        return true;
    }


    private void print(final String movieName) {
        director.setText(MovieDB.getDirector(movieName));
        year.setText(MovieDB.getYear(movieName).toString());
        genre.setText(MovieDB.getGenre(movieName));
        imageView.setImageResource(MovieDB.getPhoto(movieName));
        rating.setText(((Float) MovieDB.getRate(movieName)).toString());
        movieStory.setText(MovieDB.getStory(movieName));
        awards.setText(MovieDB.getAwards(movieName));


        // I put my question and get help from here https://stackoverflow.com/questions/60982150/how-i-can-change-the-value-of-the-button-depending-on-spinner-which-takes-the-da
        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(MovieDB.geturl(movieName)));
                try {
                    MoviesActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });


    }




}