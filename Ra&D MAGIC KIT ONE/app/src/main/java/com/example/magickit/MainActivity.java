package com.example.magickit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class MainActivity extends AppCompatActivity {


DatabaseReference reference;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        reference = FirebaseDatabase.getInstance ().getReference ();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance ();
        if(firebaseAuth.getCurrentUser () != null){
            Intent intent = new Intent (MainActivity.this,DashBoardMainActivity.class);
            startActivity (intent);
        }


    }

    public void logIn( View view){

        EditText username = findViewById (R.id.username);
        EditText password = findViewById (R.id.wifiPassword);
        final ProgressBar progressBar = findViewById (R.id.progressBarLogInPage);
        final View view1 = findViewById (R.id.logInPageView);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance ();


        if(username.getText () != null && password.getText () != null){

            progressBar.setVisibility (View.VISIBLE);
            view1.setVisibility (View.VISIBLE);

         firebaseAuth.signInWithEmailAndPassword (username.getText ().toString (),password.getText ().toString ()).addOnCompleteListener (new OnCompleteListener <AuthResult> ( ) {
             @Override
             public void onComplete( @NonNull Task<AuthResult> task ) {

                 if(task.isSuccessful ()){
                     Toast.makeText (MainActivity.this,"LogIn Succesful",Toast.LENGTH_SHORT).show ();
                     progressBar.setVisibility (View.INVISIBLE);
                     view1.setVisibility (View.INVISIBLE);
                     Intent intent = new Intent (MainActivity.this,DashBoardMainActivity.class);
                     startActivity (intent);
                 }else{

                     Toast.makeText (MainActivity.this,task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                     progressBar.setVisibility (View.INVISIBLE);
                     view1.setVisibility (View.INVISIBLE);
                 }
             }
         });


        }else{

            Toast.makeText (MainActivity.this,"Please enter username and Password!",Toast.LENGTH_SHORT).show ();

        }



    }


    public void newUser(View view){

        Intent intent = new Intent (MainActivity.this,NewUserActivity.class);
        startActivity (intent);

    }

}
