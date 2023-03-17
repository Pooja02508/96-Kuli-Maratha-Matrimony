package com.kuli.marathamatrimony.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.kuli.marathamatrimony.NavigationDrawer;
import com.kuli.marathamatrimony.R;
import com.kuli.marathamatrimony.RegistrationActivities.CreateProfile;
import com.kuli.marathamatrimony.RegistrationActivities.LoginActivity;
import com.kuli.marathamatrimony.ViewPagerFragments.StepOneFragment;
import com.kuli.marathamatrimony.ViewPagerFragments.StepThreeFragment;
import com.kuli.marathamatrimony.ViewPagerFragments.StepTwoFragment;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class Onboarding extends AppCompatActivity {

    Button register,explore,login,premium;
    FirebaseAuth mAuth;
    GifImageView getPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getSupportActionBar().hide();

        // ViewPager Code
        ViewPager viewPager = findViewById(R.id.viewPagerOnBoarding);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new StepOneFragment());
        viewPagerAdapter.addFragment(new StepTwoFragment());
        viewPagerAdapter.addFragment(new StepThreeFragment());
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayoutIndicator);
        tabLayout.setupWithViewPager(viewPager);
        register=findViewById(R.id.register);
        explore=findViewById(R.id.explore);
        login=findViewById(R.id.login);
        premium=findViewById(R.id.premium);
        getPremium=findViewById(R.id.getPremium);

        mAuth = FirebaseAuth.getInstance();

            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null){
                startActivity(new Intent(Onboarding.this, NavigationDrawer.class));
            }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, CreateProfile.class));
            }
        });
        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, PremiumActivity.class));
            }
        });
        getPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, PremiumActivity.class));
            }
        });
        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, PremiumActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Onboarding.this, LoginActivity.class));
            }
        });


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }
        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        public void addFragment(Fragment fragment) {
            mList.add(fragment);
        }
    }

}