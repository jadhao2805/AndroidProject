package com.example.magickit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class DashBoardMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList arrayList = new ArrayList ();
    GridView gridView;
    String[] roomsNames2 = {"BedRoom","workshp","Kitchen","computerLab","Living Room"};
    int tempIndex;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_dash_board_main);
        Toolbar toolbar = ( Toolbar ) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        gridView = findViewById (R.id.grideView);

        FloatingActionButton fab = ( FloatingActionButton ) findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick( View view ) {
                Snackbar.make (view , "Replace with your own action" , Snackbar.LENGTH_LONG)
                        .setAction ("Action" , null).show ( );
            }
        });

        DrawerLayout drawer = ( DrawerLayout ) findViewById (R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this , drawer , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer.addDrawerListener (toggle);
        toggle.syncState ( );

        NavigationView navigationView = ( NavigationView ) findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);



        //Adding Element in array List
//        arrayList.add (R.drawable.bedroom);
//        arrayList.add (R.drawable.workshop);
//        arrayList.add (R.drawable.computerlab);
//        arrayList.add (R.drawable.kithchen);
//        arrayList.add (R.drawable.stairs);
//

        DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ().child ("Rooms");
        reference.addValueEventListener ( new ValueEventListener ( ) {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                try {

                    DataModel.roomsIndex = (ArrayList<Integer>)dataSnapshot.getValue ();
                    Integer[] tempArray = ( Integer[] ) DataModel.roomsIndex.toArray ();


                    int i;
                    for (i = 0; i < tempArray.length; i++) {

                        arrayList.add ( DataModel.RoomsIcons[tempArray[i]] );

                    }

                    gridView.setAdapter ( new ImageAdapter ( DashBoardMainActivity.this ) );


                }catch (Exception e){

                    System.out.println (e);

                }

            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError ) {

            }
        } );



//        gridView.setAdapter (new ImageAdapter (this));
        gridView.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick( AdapterView <?> parent , View view , int position , long id ) {
                Toast.makeText (DashBoardMainActivity.this,String.valueOf (position),Toast.LENGTH_SHORT).show ();
            }
        });







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = ( DrawerLayout ) findViewById (R.id.drawer_layout);
        if (drawer.isDrawerOpen (GravityCompat.START)) {
            drawer.closeDrawer (GravityCompat.START);
        } else {
            super.onBackPressed ( );
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate (R.menu.dash_board_main , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ( );

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected( MenuItem item ) {
        // Handle navigation view item clicks here.
        int id = item.getItemId ( );

        if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.configureKit) {

            Intent intent = new Intent (DashBoardMainActivity.this,ConfigurePage.class);
            startActivity (intent);

        } else if (id == R.id.logOut) {

            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance ();
            firebaseAuth.signOut ();
            Intent intent = new Intent (DashBoardMainActivity.this, MainActivity.class);
            startActivity (intent);

        }

        DrawerLayout drawer = ( DrawerLayout ) findViewById (R.id.drawer_layout);
        drawer.closeDrawer (GravityCompat.START);
        return true;
    }

    public void lab1(View view){
        Intent intent = new Intent (DashBoardMainActivity.this,deviceToggle.class);
        startActivity (intent);

    }

//Method to control all button

    public void enter(View view){

       DataModel.nameOfLab = view.getTag ().toString ();
       Intent intent = new Intent (DashBoardMainActivity.this,deviceToggle.class);
       startActivity (intent);
       Toast.makeText (DashBoardMainActivity.this,DataModel.nameOfLab,Toast.LENGTH_SHORT).show ();


    }

//Function to add gride view

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter( Context c ) {
            mContext = c;
        }

        public int getCount() {
            return arrayList.size ();
        }

        public Object getItem( int position ) {
            return null;
        }

        public long getItemId( int position ) {
            return 0;
        }

        public View getView( int position , View convertView , ViewGroup parent ) {

            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView (mContext);
                imageView.setLayoutParams (new GridView.LayoutParams (187 , 187));
                imageView.setScaleType (ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding (5 , 5 , 5 , 5);
            } else {
                imageView = ( ImageView ) convertView;
            }
            /* Change Here in your code  */
            imageView.setImageResource (( Integer ) arrayList.get (position));

            return imageView;
        }
    }

    //Button to add Rooms
    public void addRooms(View view){

        final DatabaseReference reference = FirebaseDatabase.getInstance ().getReference ().child ("Romms");
        AlertDialog.Builder alertBox = new AlertDialog.Builder (DashBoardMainActivity.this);
        alertBox.setTitle ("Enter room");
        alertBox.setMessage ("Please select room");
        final Spinner input = new Spinner (DashBoardMainActivity.this);
        ArrayAdapter arrayAdapter = new ArrayAdapter ( this,android.R.layout.simple_spinner_item,roomsNames2);
        arrayAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        input.setAdapter ( arrayAdapter );
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertBox.setView(input);


        alertBox.setPositiveButton ( "yes" , new DialogInterface.OnClickListener ( ) {
                    @Override
                    public void onClick( final DialogInterface dialog , int which ) {

                        DataModel.roomsIndex.add ( tempIndex);
                        reference.setValue (DataModel.roomsIndex).addOnCompleteListener ( new OnCompleteListener <Void> ( ) {
                            @Override
                            public void onComplete( @NonNull Task<Void> task ) {
                                if(task.isSuccessful ()){

                                    Toast.makeText ( DashBoardMainActivity.this,"Room aded",Toast.LENGTH_SHORT ).show ();
                                    dialog.cancel ();

                                }else {
                                    Toast.makeText ( DashBoardMainActivity.this,"Failed",Toast.LENGTH_SHORT ).show ();
                                    dialog.cancel ();
                                }
                            }
                        } );

                    }
                }


        );


        alertBox.setNegativeButton ( "No" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick( DialogInterface dialog , int which ) {

dialog.cancel ();


            }
        } );

        input.setOnItemSelectedListener ( new AdapterView.OnItemSelectedListener ( ) {
            @Override
            public void onItemSelected( AdapterView <?> parent , View view , int position , long id ) {

           tempIndex = position;

            }

            @Override
            public void onNothingSelected( AdapterView <?> parent ) {

            }
        } );
        AlertDialog alert11 = alertBox.create();

        alert11.show ();



    }


}
