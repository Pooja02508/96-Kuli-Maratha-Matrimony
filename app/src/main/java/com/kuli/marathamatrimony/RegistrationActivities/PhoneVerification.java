package com.kuli.marathamatrimony.RegistrationActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.firebase.database.DatabaseReference;
import com.kuli.marathamatrimony.Activity.JavaMailAPI;
import com.kuli.marathamatrimony.R;


public class PhoneVerification extends AppCompatActivity {
    private UnifiedNativeAd nativeAd;
    TextView mobile;
    Button ok;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_verification);
        getSupportActionBar().setTitle("Verify Number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mobile = findViewById(R.id.mobile);
        ok = findViewById(R.id.ok);

        String phone = getIntent().getStringExtra("Mobile Number");
        mobile.setText(phone);

        String gender = getIntent().getStringExtra("MF");
        String DOB = getIntent().getStringExtra("DOB");
        String Height = getIntent().getStringExtra("Height");
        String Country = getIntent().getStringExtra("Country");
        String State = getIntent().getStringExtra("State");
        String City = getIntent().getStringExtra("City");
        String highesteducation = getIntent().getStringExtra("Highest Education");
        String degreeeducation = getIntent().getStringExtra("Degree Education");
        String employedin = getIntent().getStringExtra("Employed In");
        String occupation = getIntent().getStringExtra("Occupation");
        String income = getIntent().getStringExtra("Income");
        String maritalstatus = getIntent().getStringExtra("Marital Status");
        String mothertongue = getIntent().getStringExtra("Mother Tongue");
        String religion = getIntent().getStringExtra("Religion");
        String fullname = getIntent().getStringExtra("Full Name");
        String email = getIntent().getStringExtra("Email");
        String pass = getIntent().getStringExtra("Password");


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                updateData(phone, fullname, email, pass, maritalstatus, mothertongue, religion, highesteducation, degreeeducation, employedin, occupation, income, gender, DOB, Height, Country, State, City);

                JavaMailAPI javaMailAPI = new JavaMailAPI(getApplicationContext(), email, phone, fullname, pass, maritalstatus, mothertongue, religion, highesteducation, degreeeducation, employedin, occupation, income, gender, DOB, Height, Country, State, City);

                javaMailAPI.execute();
                Toast.makeText(PhoneVerification.this, "Data Sent to your email.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ProfilePicture.class);
                intent.putExtra("Full Name", fullname);
                intent.putExtra("Mobile Number", phone);
                intent.putExtra("Email", email);
                intent.putExtra("Password", pass);
                intent.putExtra("Marital Status", maritalstatus);
                intent.putExtra("Mother Tongue", mothertongue);
                intent.putExtra("Religion", religion);
                intent.putExtra("Highest Education", highesteducation);
                intent.putExtra("Degree Education", degreeeducation);
                intent.putExtra("Employed In", employedin);
                intent.putExtra("Occupation", occupation);
                intent.putExtra("Income", income);
                intent.putExtra("MF", gender);
                intent.putExtra("DOB", DOB);
                intent.putExtra("Height", Height);
                intent.putExtra("Country", Country);
                intent.putExtra("State", State);
                intent.putExtra("City", City);
                startActivity(intent);
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

        //        HashMap <String, String>map = new HashMap<String, String>();
//        HashMap <String, Object>User = new HashMap<String, Object>();
//        User.put("Mobile Number", phoneNum);
//        User.put("Full Name", fullname);
//        User.put("Email", email);
//        User.put("Password", pass);
//        User.put("Marital Status", ms);
//        User.put("Mother Tongue", mt);
//        User.put("Religion", rlgn);
//        User.put("Highest Education", highestEd);
//        User.put("Degree Education", degreeEd);
//        User.put("Employed In", employed);
//        User.put("Occupation", occu);
//        User.put("Income", incm);
//        User.put("Gender", mf);
//        User.put("Date of Birth", dob);
//        User.put("Height", height);
//        User.put("Country", country);
//        User.put("State", state);
//        User.put("City", city);

//        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        databaseReference.child(phoneNum).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
//            @Override
//            public void onComplete(@NonNull Task task) {
//
//                if (task.isSuccessful()) {
//                    Toast.makeText(PhoneVerification.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
//                    Intent regIntent = new Intent(PhoneVerification.this, ProfilePicture.class);
//                    startActivity(regIntent);
//                } else {
//                    Toast.makeText(PhoneVerification.this, "Failed to Update.Try again later.", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(PhoneVerification.this, PhoneVerification.class);
//                    startActivity(intent);
//                }
//            }
//        });

//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

//        MobileAds.initialize(getApplicationContext(), getString(R.string.ADMOB_ADS_UNIT_ID));
//
//        AdLoader.Builder builder = new AdLoader.Builder(getApplicationContext(), getString(R.string.ADMOB_ADS_UNIT_ID));
//        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
//            @Override
//            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
//                if (nativeAd != null) {
//                    nativeAd.destroy();
//                }
//                nativeAd = unifiedNativeAd;
//                FrameLayout frameLayout = findViewById(R.id.fl_adplaceholder);
//                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.ad_unified, null);
//
//                populateUnifiedNativeAdView(unifiedNativeAd, adView);
//                frameLayout.removeAllViews();
//                frameLayout.addView(adView);
//
//            }
//        });
//
//        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
//        builder.withNativeAdOptions(adOptions);
//        AdLoader adLoader = builder.withAdListener (new AdListener(){
//
//            @Override
//            public void onAdFailedToLoad(int i) {
//
//            }
//        }).build();
//        adLoader.loadAd(new AdRequest.Builder().build());

//    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
//
//        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
//        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//        adView.setBodyView(adView.findViewById(R.id.ad_body));
//        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
//        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
//        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
//        adView.setStoreView(adView.findViewById(R.id.ad_store));
//        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
//
//        ((TextView)adView.getHeadlineView()).setText(nativeAd.getHeadline());
//        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
//
//        if (nativeAd.getBody() == null) {
//            adView.getBodyView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getBodyView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//        }
//        if (nativeAd.getCallToAction() == null) {
//            adView.getCallToActionView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getCallToActionView().setVisibility(View.VISIBLE);
//            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
//        }
//        if (nativeAd.getIcon() == null) {
//            adView.getIconView().setVisibility(View.GONE);
//        } else {
//            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
//            adView.getIconView().setVisibility(View.VISIBLE);
//        }
//        if (nativeAd.getPrice() == null) {
//            adView.getPriceView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getPriceView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
//        }
//        if (nativeAd.getStore() == null) {
//            adView.getStoreView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getStoreView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
//
//        }
//        if (nativeAd.getStarRating() == null) {
//            adView.getStarRatingView().setVisibility(View.INVISIBLE);
//        } else {
//            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
//            adView.getStarRatingView().setVisibility(View.VISIBLE);
//        }
//        if (nativeAd.getAdvertiser() == null) {
//            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
//
//        } else {
//            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//            adView.getAdvertiserView().setVisibility(View.VISIBLE);
//        }
//        adView.setNativeAd(nativeAd);
//    }

