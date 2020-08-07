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

public class NewUserActivity extends AppCompatActivity {



    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_new_user);



    }
public void createUser( final View view){
    EditText username = findViewById (R.id.username2);
    EditText password = findViewById (R.id.password2);
    final ProgressBar progressBar2 = findViewById (R.id.newUserProgressBar);
    final View view1 = findViewById (R.id.newUserView);
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance ();
    progressBar2.setVisibility (View.VISIBLE);
    view1.setVisibility (View.VISIBLE);

    if(username.getText () != null && password.getText () != null){

        firebaseAuth.createUserWithEmailAndPassword(username.getText ().toString (),password.getText ().toString ()).addOnCompleteListener (new OnCompleteListener <AuthResult> ( ) {
            @Override
            public void onComplete( @NonNull Task<AuthResult> task ) {

                if(task.isSuccessful ()){

                    Toast.makeText (NewUserActivity.this,"New User Created",Toast.LENGTH_SHORT).show ();
                    progressBar2.setVisibility (View.INVISIBLE);
                    view1.setVisibility (View.INVISIBLE);
                }else{

                    Toast.makeText (NewUserActivity.this,task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                    progressBar2.setVisibility (View.INVISIBLE);
                    view1.setVisibility (View.INVISIBLE);

                }
            }
        });



    }else{

        Toast.makeText (NewUserActivity.this,"Please enter password and Username",Toast.LENGTH_SHORT).show ();

    }



}

public void logIn(View view){
    Intent intent = new Intent (NewUserActivity.this,aMainActivity.class);
    startActivity (intent);


}


}
