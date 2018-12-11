package com.example.android.break2;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp extends Activity {
    EditText nametxt;
    EditText usernametxt;
    EditText emailtxt;
    EditText agetxt;
    EditText passtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void GoHome(View v)
    {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
    public void RegisterFunction(View view) throws SQLException {
        Controller c = new Controller() ;
         nametxt = findViewById(R.id.your_full_name);
        String name = nametxt.getText().toString() ;

         usernametxt =  findViewById(R.id.usernameID);
        String username = usernametxt.getText().toString() ;

         emailtxt =  findViewById(R.id.your_email_address);
        String email = emailtxt.getText().toString() ;

         agetxt =  findViewById(R.id.ageID);
        int age ;
         if(! agetxt.getText().toString().isEmpty()) {
              age = Integer.parseInt(agetxt.getText().toString());
         }
         else {age = 20;} // default value
         passtxt =  findViewById(R.id.create_new_password);
        String pass =  passtxt.getText().toString() ;
        //


       if(pass.isEmpty() || email.isEmpty() || agetxt.getText().toString().isEmpty() || name.isEmpty() || username.isEmpty())
       {
           Toast.makeText(SignUp.this,"Registration failed . Please re-check your input information ",Toast.LENGTH_SHORT).show();

       }
       else
       {
        int f_InsertionResult =  c.InsertNewFollower(name,age,email,pass,username);
       if (f_InsertionResult==1)
       {
           Toast.makeText(SignUp.this,"Successfully  Registered ",Toast.LENGTH_SHORT).show();
           Intent i = new Intent(this, Home.class);
           startActivity(i);
           this.finish();

       }

       else if(f_InsertionResult==404)
       {
           Toast.makeText(SignUp.this,"This Email address is used before with another account .",Toast.LENGTH_SHORT).show();
       }
       else if(f_InsertionResult == -1)
       {
           Toast.makeText(SignUp.this,"This Username is taken please choose another one .",Toast.LENGTH_LONG).show();
       }
        else {Toast.makeText(SignUp.this,"Failed there is invalid information please recheck it ",Toast.LENGTH_SHORT).show();}
       }
    }
}
