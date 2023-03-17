package com.kuli.marathamatrimony;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.kuli.marathamatrimony.Activity.BrideActivity;
import com.kuli.marathamatrimony.Activity.ContactActivity;
import com.kuli.marathamatrimony.Activity.GroomActivity;
import com.kuli.marathamatrimony.Activity.HomeActivity;
import com.kuli.marathamatrimony.Activity.Onboarding;
import com.kuli.marathamatrimony.Activity.PremiumActivity;
import com.kuli.marathamatrimony.Activity.PremiumMemberActivity;
import com.kuli.marathamatrimony.Activity.TermsActivity;
import com.kuli.marathamatrimony.Activity.WhoViewedProfileActivity;
import com.kuli.marathamatrimony.RegistrationActivities.ProfileActivity;
import com.kuli.marathamatrimony.RegistrationActivities.CreateProfile;
import com.kuli.marathamatrimony.RegistrationActivities.LoginActivity;

public class NavigationDrawer extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headView= navigationView.getHeaderView(0);
        ImageView imgProfile=(ImageView) headView.findViewById(R.id.profileImage);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NavigationDrawer.this, Onboarding.class);
                startActivity(intent);
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_terms, R.id.nav_premiummemberships, R.id.nav_contactus, R.id.nav_premium, R.id.nav_viewprofile, R.id.nav_newprofile, R.id.nav_bride, R.id.nav_groom)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                switch (id){
                    case  R.id.nav_myprofile:
                        Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("Mobile Number",getIntent().getStringExtra("Mobile Number"));
                        startActivity(intent);
                        break;
                    case  R.id.nav_register:
                    case  R.id.nav_newprofile:
                        startActivity(new Intent(getApplicationContext(), CreateProfile.class));
                        break;
                    case  R.id.nav_login:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case R.id.nav_home: startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        break;
                    case R.id.nav_terms: startActivity(new Intent(getApplicationContext(), TermsActivity.class));
                        break;
                    case R.id.nav_premiummemberships: startActivity(new Intent(getApplicationContext(), PremiumMemberActivity.class));
                        break;
                    case R.id.nav_contactus: startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                        break;
                    case R.id.nav_premium: startActivity(new Intent(getApplicationContext(), PremiumActivity.class));
                        break;
                    case R.id.nav_bride: startActivity(new Intent(getApplicationContext(), BrideActivity.class));
                        break;
                    case R.id.nav_groom: startActivity(new Intent(getApplicationContext(), GroomActivity.class));
                        break;
                    case R.id.nav_viewprofile:startActivity(new Intent(getApplicationContext(), WhoViewedProfileActivity.class));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + id);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"Logout Successful",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NavigationDrawer.this, Onboarding.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void invitefriends(MenuItem menuItem) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void whatsapp(MenuItem menuItem)
    {
        Intent sendIntent = new Intent("android.intent.action.MAIN");
        sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("919561784269")+"@s.whatsapp.net");
        startActivity(sendIntent);
    }

    public void facebook(MenuItem menuItem)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/JeevansathiSearch/"));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}