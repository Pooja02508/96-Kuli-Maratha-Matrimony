package com.kuli.marathamatrimony.RegistrationActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.kuli.marathamatrimony.Activity.PremiumActivity;
import com.kuli.marathamatrimony.R;


public class CreateProfile extends AppCompatActivity {

    CardView self,relative,son,daughter,brother,sister,client,friend,men,women;
    TextView selfText,relativeText,sonText,daughterText,brotherText,sisterText,clientText,friendText,menText,womenText;
    LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    Button purchase_premium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

        getSupportActionBar().setTitle("Create Profile For");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        self=findViewById(R.id.cv1);
        relative=findViewById(R.id.cv2);
        son=findViewById(R.id.cv3);
        daughter=findViewById(R.id.cv4);
        brother=findViewById(R.id.cv5);
        sister=findViewById(R.id.cv6);
        client=findViewById(R.id.cv7);
        friend=findViewById(R.id.cv8);
        men=findViewById(R.id.cv9);
        women=findViewById(R.id.cv10);
        selfText=findViewById(R.id.selfText);
        relativeText=findViewById(R.id.relativeText);
        sonText=findViewById(R.id.sonText);
        daughterText=findViewById(R.id.daughterText);
        brotherText=findViewById(R.id.brotherText);
        sisterText=findViewById(R.id.sisterText);
        clientText=findViewById(R.id.clientText);
        friendText=findViewById(R.id.friendText);
        menText=findViewById(R.id.menText);
        womenText=findViewById(R.id.womenText);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        img5=findViewById(R.id.img5);
        img6=findViewById(R.id.img6);
        img7=findViewById(R.id.img7);
        img8=findViewById(R.id.img8);
        img9=findViewById(R.id.img9);
        img10=findViewById(R.id.img10);
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l4=findViewById(R.id.l4);
        l5=findViewById(R.id.l5);
        l6=findViewById(R.id.l6);
        l7=findViewById(R.id.l7);
        l8=findViewById(R.id.l8);
        l9=findViewById(R.id.l9);
        l10=findViewById(R.id.l10);
        purchase_premium=findViewById(R.id.purchase_premium);

        purchase_premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PremiumActivity.class));
            }
        });

        self.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Self",selfText.getText().toString());
                startActivity(intent);

                l1.setBackgroundColor(Color.RED);
                selfText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));


                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Relative",relativeText.getText().toString());
                startActivity(intent);

                l2.setBackgroundColor(Color.RED);
                relativeText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));

            }
        });
        son.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Son",sonText.getText().toString());
                startActivity(intent);

                l3.setBackgroundColor(Color.RED);
                sonText.setTextColor(Color.BLACK);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        daughter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Daughter",daughterText.getText().toString());
                startActivity(intent);

                l4.setBackgroundColor(Color.RED);
                daughterText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        brother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Brother",brotherText.getText().toString());
                startActivity(intent);

                l5.setBackgroundColor(Color.RED);
                brotherText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        sister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Sister",sisterText.getText().toString());
                startActivity(intent);

                l6.setBackgroundColor(Color.RED);
                sisterText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Client",clientText.getText().toString());
                startActivity(intent);

                l7.setBackgroundColor(Color.RED);
                clientText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Friend",friendText.getText().toString());
                startActivity(intent);

                l8.setBackgroundColor(Color.RED);
                friendText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));


            }
        });
        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Second Marriage Men",menText.getText().toString());
                startActivity(intent);

                l9.setBackgroundColor(Color.RED);
                menText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l10.setBackgroundColor(Color.WHITE);
                womenText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));

            }
        });
        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PersonalDetails.class);
                intent.putExtra("Second Marriage Women",womenText.getText().toString());
                startActivity(intent);

                l10.setBackgroundColor(Color.RED);
                womenText.setTextColor(Color.WHITE);
                DrawableCompat.setTint(img10.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));

                l1.setBackgroundColor(Color.WHITE);
                selfText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img1.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l2.setBackgroundColor(Color.WHITE);
                relativeText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img2.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l3.setBackgroundColor(Color.WHITE);
                sonText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img3.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l4.setBackgroundColor(Color.WHITE);
                daughterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img4.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l5.setBackgroundColor(Color.WHITE);
                brotherText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img5.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l6.setBackgroundColor(Color.WHITE);
                sisterText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img6.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l7.setBackgroundColor(Color.WHITE);
                clientText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img7.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l8.setBackgroundColor(Color.WHITE);
                friendText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img8.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
                l9.setBackgroundColor(Color.WHITE);
                menText.setTextColor(Color.DKGRAY);
                DrawableCompat.setTint(img9.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}