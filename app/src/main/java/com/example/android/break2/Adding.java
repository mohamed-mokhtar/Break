package com.example.android.break2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Adding extends AppCompatActivity {
    int ID,Priv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        Bundle info = getIntent().getExtras();
        ID=info.getInt("ID");
        Priv=info.getInt("Privileges");
        //
    }
    public void addActorActive(View view) {
        Intent i = new Intent(this, AddActor.class);
        startActivity(i);

    }

    public void addDirectorActive(View view) {
        Intent i = new Intent(this, AddDirector.class);
        startActivity(i);

    }

    public void addAuthorActive(View view) {
        Intent i = new Intent(this, AddAuthor.class);
        startActivity(i);

    }

    public void addCinemaActive(View view) {
        Intent i = new Intent(this, AddCinema.class);
        startActivity(i);

    }

    public void addAdvActive(View view) {
        Intent i = new Intent(this, Add_Ads.class);
        i.putExtra("adminID",ID);
        startActivity(i);

    }
    public void newAdminActive(View view)
    {
        Intent i = new Intent(this,AddAdmin.class);
        startActivity(i);
    }
    public void addMovieActive(View view)
    {
        Intent i = new Intent(this,AddMovie.class);
        startActivity(i);
    }
    public void addMovieInCin(View view)
    {
        Intent i = new Intent(this,AddMovieInCinema.class);
        startActivity(i);

    }
}
