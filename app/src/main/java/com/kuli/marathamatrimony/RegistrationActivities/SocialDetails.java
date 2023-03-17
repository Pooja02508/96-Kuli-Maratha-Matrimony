package com.kuli.marathamatrimony.RegistrationActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.kuli.marathamatrimony.Activity.PremiumActivity;
import com.kuli.marathamatrimony.R;

import java.util.ArrayList;
import java.util.List;

public class SocialDetails extends AppCompatActivity {

    private UnifiedNativeAd nativeAd;
    Spinner maritalStatus,motherTongue,religion;
    Button next;
    Button purchase_premium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_details);

        getSupportActionBar().setTitle("Social Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        maritalStatus=findViewById(R.id.maritalStatus);
        motherTongue=findViewById(R.id.motherTongue);
        religion=findViewById(R.id.religion);
        next=findViewById(R.id.next);
        purchase_premium=findViewById(R.id.purchase_premium);
        next.setEnabled(false);

        List<String> maritalStatus_array = new ArrayList<String>();
        maritalStatus_array.add("Marital Status");
        maritalStatus_array.add("Never Married");
        maritalStatus_array.add("Awaiting Divorce");
        maritalStatus_array.add("Divorced");
        maritalStatus_array.add("Widowed");
        maritalStatus_array.add("Annulled");
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, maritalStatus_array) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 )  {
                    return false;
                } else {
                    return true;
                }
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==0 ) {
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        maritalStatusAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        maritalStatus.setAdapter(maritalStatusAdapter);

        List<String> motherTongue_array = new ArrayList<String>();
        motherTongue_array.add("Mother Tongue");//0
        motherTongue_array.add("North");//1
        motherTongue_array.add("Hindi-Delhi");
        motherTongue_array.add("Hindi-MP/CG");
        motherTongue_array.add("Hindi-UP/UK");
        motherTongue_array.add("Punjabi");
        motherTongue_array.add("Hindi-Bihar/Jharkhand");
        motherTongue_array.add("Hindi-Rajasthan");
        motherTongue_array.add("Haryanvi");
        motherTongue_array.add("Himachali");
        motherTongue_array.add("Kashmiri");
        motherTongue_array.add("Sindhu");
        motherTongue_array.add("Urdu");
        motherTongue_array.add("West");//13
        motherTongue_array.add("Marathi");
        motherTongue_array.add("Gujrati");
        motherTongue_array.add("Kutchi");
        motherTongue_array.add("Hindi-MP/CG");
        motherTongue_array.add("Konkani");
        motherTongue_array.add("Sindhi");
        motherTongue_array.add("South");//20
        motherTongue_array.add("Tamil");
        motherTongue_array.add("Telugu");
        motherTongue_array.add("Kannada");
        motherTongue_array.add("Malayalam");
        motherTongue_array.add("Tulu");
        motherTongue_array.add("Urdu");
        motherTongue_array.add("East");//27
        motherTongue_array.add("Bengali");
        motherTongue_array.add("Oriya");
        motherTongue_array.add("Assamese");
        motherTongue_array.add("Sikkim/Nepali");
        motherTongue_array.add("Others");//32
        motherTongue_array.add("English");
        ArrayAdapter<String> motherTongueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, motherTongue_array) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 ||position==1||position==13||position==20||position==27||position==32)  {
                    return false;
                } else {
                    return true;
                }
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==0 ||position==1||position==13||position==20||position==27||position==32) {
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        motherTongueAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        motherTongue.setAdapter(motherTongueAdapter);

        List<String> religion_array = new ArrayList<String>();
        religion_array.add("Religion");
        religion_array.add("Hindu");//1
        religion_array.add("Muslim");//2
        religion_array.add("Sikh");//3
        religion_array.add("Christian");//4
        religion_array.add("Buddhist");//5
        religion_array.add("Jain");//6
        religion_array.add("Parsi");//7
        religion_array.add("Jewish");//8
        religion_array.add("Bahai");//9
        ArrayAdapter<String> religionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, religion_array) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 )  {
                    return false;
                } else {
                    return true;
                }
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==0 ) {
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                next.setEnabled(true);
                return view;
            }
        };
        religionAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        religion.setAdapter(religionAdapter);


        String MF=getIntent().getStringExtra("MF");
        String DOB=getIntent().getStringExtra("DOB");
        String Height=getIntent().getStringExtra("Height");
        String Country=getIntent().getStringExtra("Country");
        String State=getIntent().getStringExtra("State");
        String City=getIntent().getStringExtra("City");
        String highestEd=getIntent().getStringExtra("Highest Education");
        String degreeEd=getIntent().getStringExtra("Degree Education");
        String employed=getIntent().getStringExtra("Employed In");
        String occu=getIntent().getStringExtra("Occupation");
        String incm=getIntent().getStringExtra("Income");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SocialDetails.this,LoginDetails.class);
                String ms = maritalStatus.getSelectedItem().toString();
                String mt = motherTongue.getSelectedItem().toString();
                String rlgn = religion.getSelectedItem().toString();

                intent.putExtra("Marital Status",ms);
                intent.putExtra("Mother Tongue",mt);
                intent.putExtra("Religion",rlgn);

                intent.putExtra("Highest Education",highestEd);
                intent.putExtra("Degree Education",degreeEd);
                intent.putExtra("Employed In",employed);
                intent.putExtra("Occupation",occu);
                intent.putExtra("Income",incm);
                intent.putExtra("MF",MF);
                intent.putExtra("DOB",DOB);
                intent.putExtra("Height",Height);
                intent.putExtra("Country",Country);
                intent.putExtra("State",State);
                intent.putExtra("City",City);
                startActivity(intent);
            }
        });
        purchase_premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PremiumActivity.class));
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
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
}