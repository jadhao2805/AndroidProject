package com.example.magickit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class deviceToggle extends AppCompatActivity {



    ProgressBar progressBar;
    View viewDevice;
    Map<Integer,Integer> map;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_device_toggle);
//        DataModel.bt1 = 0;
//        DataModel.bt2 = 0;
//        DataModel.bt3 = 0;
//        DataModel.bt4 = 0;
//        DataModel.bt5 = 0;
//        DataModel.bt6 = 0;
//        DataModel.bt7 = 0;
//        DataModel.bt8 = 0;

        progressBar = findViewById (R.id.Device);
        viewDevice = findViewById (R.id.DeviceView);
        int i = 0;
        for(i=0;i<DataModel.buttonArray.length;i++){

            DataModel.buttonArray[i] = 0;

        }
//        final DatabaseReference reference2 = FirebaseDatabase.getInstance ().getReference ().child ("StatusUpdate");
//        reference2.addValueEventListener (new ValueEventListener ( ) {
//            @Override
//            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {
//
//                DataModel.buttonArray = (int[])dataSnapshot.getValue ();
//
//
//            }
//
//            @Override
//            public void onCancelled( @NonNull DatabaseError databaseError ) {
//
//            }
//        });



        map = new HashMap <> ();
        map.put (R.id.light,1);
        map.put (R.id.fan,2);
        map.put (R.id.bulb,3);
        map.put (R.id.switc,4);


    }

    public void home( View view){
        super.onBackPressed ();
    }



    //Method to toogle all Switch
    public void toggle( final View view1){
        progressBar.setVisibility (View.VISIBLE);
        viewDevice.setVisibility (View.VISIBLE);
        DatabaseReference reference = FirebaseDatabase.getInstance ( ).getReference ( ).child (view1.getTag ( ).toString ( ));
        final DatabaseReference reference2 = FirebaseDatabase.getInstance ().getReference ().child ("StatusUpdate");
        final int index = map.get(view1.getId ());
        if(DataModel.buttonArray[index] == 0) {
            reference.setValue (1).addOnCompleteListener (new OnCompleteListener <Void> ( ) {
                @Override
                public void onComplete( @NonNull Task<Void> task ) {

                    if(task.isSuccessful ()){

                        progressBar.setVisibility (View.INVISIBLE);
                        viewDevice.setVisibility (View.INVISIBLE);
                        Button button = findViewById (view1.getId ());
                        button.setBackgroundResource (R.drawable.butt1);
                        DataModel.buttonArray[index] = 1;
//                        reference2.setValue (DataModel.buttonArray);




                    }else{
                        Toast.makeText (deviceToggle.this,task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                        progressBar.setVisibility (View.INVISIBLE);
                        viewDevice.setVisibility (View.INVISIBLE);


                    }


                }
            });
        }else{

            reference.setValue (0).addOnCompleteListener (new OnCompleteListener <Void> ( ) {
                @Override
                public void onComplete( @NonNull Task <Void> task ) {
                    if(task.isSuccessful ()){
                        progressBar.setVisibility (View.INVISIBLE);
                        viewDevice.setVisibility (View.INVISIBLE);
                        Button button = findViewById (view1.getId ());
                        button.setBackgroundResource (R.drawable.buttonwhite1);
                        DataModel.buttonArray[index] = 0;


                    }else{

                        Toast.makeText (deviceToggle.this,task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                        progressBar.setVisibility (View.INVISIBLE);
                        viewDevice.setVisibility (View.INVISIBLE);

                    }
                }
            });


        }





    }



}
