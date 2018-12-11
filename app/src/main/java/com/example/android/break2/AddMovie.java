package com.example.android.break2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {

    EditText movieName_ET,Lang_ET,RDate_ET,Des_ET,ageR_ET,url_ET,reviewersRating_ET,usersRating_ET,director_ET,author_ET,img_ET,duration_ET,Genre_ET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
    }
    public void AddMovieClick(View v)
    {
        movieName_ET= findViewById(R.id.movieName);
        Lang_ET=findViewById(R.id.movieLang);
        RDate_ET=findViewById(R.id.releaseDate);
        Des_ET=findViewById(R.id.movieDescription);
        ageR_ET=findViewById(R.id.ageRest);
        url_ET=findViewById(R.id.movieUrl);
        reviewersRating_ET=findViewById(R.id.reviewRating);
        usersRating_ET=findViewById(R.id.userRating);
        director_ET=findViewById(R.id.movieDirector);
        author_ET=findViewById(R.id.movieAuthor);
        duration_ET=findViewById(R.id.movieDuration);
        Genre_ET=findViewById(R.id.movieGenre);

        String name = movieName_ET.getText().toString();
        String language=Lang_ET.getText().toString();
        String rdate=RDate_ET.getText().toString();
        String description =Des_ET.getText().toString();
        String age_rest = ageR_ET.getText().toString();
        String url=url_ET.getText().toString();
        String reviewers = reviewersRating_ET.getText().toString();
        String users=usersRating_ET.getText().toString();
        String director=director_ET.getText().toString();
        String author =author_ET.getText().toString();
        String duration = duration_ET.getText().toString();
        String genre=Genre_ET.getText().toString();
        int authorID=0,directorID=0;
        if (    name.isEmpty() ||	language.isEmpty() ||
                rdate.isEmpty() ||description.isEmpty() ||
                age_rest.isEmpty() || url.isEmpty() ||
                reviewers.isEmpty() ||users.isEmpty() ||
                director.isEmpty() ||	author.isEmpty() ||
                duration.isEmpty() ||genre.isEmpty() )
            { Toast.makeText(AddMovie.this,"Adding failed. Please re-check your input information ",Toast.LENGTH_LONG).show(); }
        else {
                Controller c = new Controller();
                int res = c.InsertNewMovie(name,language,
                        rdate,description,
                        Integer.parseInt(age_rest),url,
                        Float.parseFloat(reviewers),Float.parseFloat(users),
                        directorID,authorID,
                        duration,genre);
             }
    }
}
