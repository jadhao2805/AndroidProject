package com.example.magickit;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfigurePage extends AppCompatActivity {



    EditText name;
    EditText password;
    View changeView;
    ProgressBar progressBarWifi;
    String status;
    TextView wifiName;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_configure_page);
        name = findViewById (R.id.name);
        password = findViewById (R.id.wifiPassword);
        progressBarWifi = findViewById (R.id.changeWifiProgressBar);
        changeView = findViewById (R.id.changeWifiView);
        wifiName = findViewById (R.id.wifiName);

        status = "OF";

        final DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ().child ("STATUS");
        reference.addValueEventListener (new ValueEventListener ( ) {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                status = dataSnapshot.getValue ().toString ();
                if(status.equals ("ON")){

                    progressBarWifi.setVisibility (View.INVISIBLE);
                    changeView.setVisibility (View.INVISIBLE);


                }else {


                    progressBarWifi.setVisibility (View.VISIBLE);
                    changeView.setVisibility (View.VISIBLE);
                }


            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        });


        DatabaseReference reference1 = FirebaseDatabase.getInstance ().getReference ().child ("name");
        reference1.addValueEventListener (new ValueEventListener ( ) {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
                String wifiName2 = dataSnapshot.getValue().toString ();
                wifiName.setText ("Current Wifi "+ wifiName2);

            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        });





    }


    public void changeWifi( View view3){
        DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ().child ("password");
        DatabaseReference reference1 = FirebaseDatabase.getInstance ().getReference ().child ("name");
        DatabaseReference reference2 = FirebaseDatabase.getInstance ().getReference ().child ("isConnected");
        reference2.setValue (1);
        progressBarWifi.setVisibility (View.VISIBLE);
        changeView.setVisibility (View.VISIBLE);
        reference.setValue (password.getText ().toString ());
        reference1.setValue (name.getText ().toString ()).addOnCompleteListener (new OnCompleteListener<Void> ( ) {
            @Override
            public void onComplete( @NonNull Task<Void> task ) {
                if(task.isSuccessful ()){

                    Toast.makeText (ConfigurePage.this,"Done",Toast.LENGTH_SHORT).show ();
                    progressBarWifi.setVisibility (View.INVISIBLE);
                    changeView.setVisibility (View.INVISIBLE);


                }else{

                    Toast.makeText (ConfigurePage.this,"Failed",Toast.LENGTH_SHORT).show ();
                    progressBarWifi.setVisibility (View.INVISIBLE);
                    changeView.setVisibility (View.INVISIBLE);


                }
            }
        });


    }

    }
