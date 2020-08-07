package com.jadhao3726.tedxjnec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AudiancePole extends AppCompatActivity {

    ProgressDialog progressDialog;
   static Button aoption;
   static Button boption;
   static Button coption;
   static Button doption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiance_pole);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        aoption = findViewById(R.id.aoption);
        boption = findViewById(R.id.boption);
        coption = findViewById(R.id.coption);
        doption = findViewById(R.id.doption);
        if(login.isPole == 1){

            aoption.setEnabled(false);
            boption.setEnabled(false);
            coption.setEnabled(false);
            doption.setEnabled(false);
        }
    }


    public void submit(View view){



        progressDialog.setMessage("Submitting your response...");
        progressDialog.show();
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Pole");
        reference1.child(view.getTag().toString()).push().setValue(System.currentTimeMillis()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Toast.makeText(AudiancePole.this,"Done",Toast.LENGTH_SHORT).show();
                    finish();
                    progressDialog.dismiss();
                    login.isPole = 1;

                }else{
                    Toast.makeText(AudiancePole.this,"Failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }



    }


