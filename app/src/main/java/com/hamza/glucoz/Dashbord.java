package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;

public class Dashbord extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView btntoReminder;
    TextView glycemie;
    TextView hba1c;
    TextView dispaly;
    Button hstr;
    FirebaseAuth fAuth;
    FirebaseUser user;
    TextView cetones;
    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        setSupportActionBar(findViewById(R.id.id_toolbar));
        drawerLayout = findViewById(R.id.dash);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, findViewById(R.id.id_toolbar), R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        btntoReminder =findViewById(R.id.toreminder);
        glycemie=findViewById(R.id.glucose_view);
        //hstr = findViewById(R.id.id_historique);

        fAuth=FirebaseAuth.getInstance();
        user=fAuth.getCurrentUser();

        //dispaly= findViewById(R.id.display);

//        hstr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Dashbord.this,DataGlycemie.class);
//                startActivity(intent);
//            }
//        });

        //glycemie

        glycemie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashbord.this,AddGlycemie.class);
                startActivity(intent);

                Toast.makeText(Dashbord.this, "ajouter une valeur glyc??mie",Toast.LENGTH_SHORT).show();
            }
        });
        //hb1ac
        hba1c=(TextView)findViewById(R.id.hba1c_view);
        hba1c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashbord.this,AddHbA1c.class);
                startActivity(intent);

                Toast.makeText(Dashbord.this, "ajouter une valeur hba1c",Toast.LENGTH_SHORT).show();
            }
        });

        cetones=(TextView)findViewById(R.id.cetones_view);
        cetones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashbord.this,AddCetones.class);
                startActivity(intent);

                Toast.makeText(Dashbord.this, "ajouter le pourcentage cetones",Toast.LENGTH_SHORT).show();
            }
        });

//navigation
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    /*caconcerne le bottom home desactiv??e
                    case R.id.content_Dash:
                        Intent intent=new Intent(Dashbord.this,Dashbord.class);
                        startActivity(intent);
                        Toast.makeText(Dashbord.this, "ggg" +
                                "", Toast.LENGTH_SHORT).show();
                        break;*/
                    case R.id.dataGlycemie:
                        Intent intenta=new Intent(Dashbord.this,DataGlycemie.class);
                        startActivity(intenta);
                        Toast.makeText(Dashbord.this, "voici votre historique", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });



        //alarm
        btntoReminder.setOnClickListener(view -> {
            Intent intent = new Intent(Dashbord.this,reminder.class);
           startActivity(intent);
       });




    }

    @Override
    protected void onStart() {
        super.onStart();
        checkuserstatus();
    }

    private void checkuserstatus() {
        SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
        Boolean counter=sharedPreferences.getBoolean("logincounter",Boolean.valueOf(String.valueOf(MODE_PRIVATE)));
        String Email=sharedPreferences.getString("useremail",String.valueOf(MODE_PRIVATE));
            if(counter){
                //Toast.makeText(Dashbord.this, "Yes" + Email , Toast.LENGTH_SHORT).show();
            }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_logout:
                FirebaseAuth.getInstance().signOut();
                //SharedPreferences sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
               // sharedPreferences.edit().clear().commit();
                //startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                dispalyAlert();

                break;
            case  R.id.id_profile:
                startActivity(new Intent(getApplicationContext(),Profile.class));
                break;
            case R.id.id_share:
                ApplicationInfo api = getApplicationContext().getApplicationInfo();
                String apk=api.sourceDir;
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apk)));
                //intent.putExtra(intent.EXTRA_SUBJECT,"cheek out this cool application");
                //intent.putExtra(intent.EXTRA_TEXT,"your application link here");
                startActivity(Intent.createChooser(intent,"Share via"));
                break;
            case R.id.id_about:
                startActivity(new Intent(getApplicationContext(),About.class));
                break;
            case R.id.id_calcul:
                startActivity(new Intent(getApplicationContext(),Calculator.class));
                break;

            default:
                Toast.makeText(this,"khedama",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void dispalyAlert() {
        AlertDialog.Builder warning = new AlertDialog.Builder(this)
                .setTitle("Are you sure !!")
                .setMessage("Click Yes if you want to disconnect !!")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(),Dashbord.class));

                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Dashbord.this,LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);



                        //startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        //finish();
                    }
                });

        warning.show();
    }


}