package com.kuli.marathamatrimony.RegistrationActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.kuli.marathamatrimony.Activity.PremiumActivity;
import com.kuli.marathamatrimony.R;

import java.util.ArrayList;
import java.util.List;

public class LoginDetails extends AppCompatActivity {
    private UnifiedNativeAd nativeAd;
    Spinner setting;
    TextView setText;
    EditText FullName,EmailId,Password,Code,Phone;
    RadioButton show,hide,interest;
    Button next;
    FirebaseAuth mAuth;
    Button purchase_premium;
    RadioGroup radioGroup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_details);

        getSupportActionBar().setTitle("Login Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setting=findViewById(R.id.setting);
        setText=findViewById(R.id.setText);
        FullName=findViewById(R.id.FullName);
        EmailId=findViewById(R.id.EmailId);
        Password=findViewById(R.id.Password);
        Code=findViewById(R.id.Code);
        Phone=findViewById(R.id.Phone);
        show=findViewById(R.id.show);
        hide=findViewById(R.id.hide);
        interest=findViewById(R.id.interest);
        next=findViewById(R.id.next);
        purchase_premium=findViewById(R.id.purchase_premium);

        next.setEnabled(false);
        mAuth = FirebaseAuth.getInstance();

        List<String> setting_array = new ArrayList<String>();
        setting_array.add("Name Privacy Settings");
        setting_array.add("Show my name to all");
        setting_array.add("Don't show my name");
        ArrayAdapter<String> settingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, setting_array) {
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
        settingAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        setting.setAdapter(settingAdapter);

        setting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 1) {
                    setText.setText("Show to all");
                } else if (position == 2) {
                    setText.setText("Don't show");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                show.setBackgroundResource(R.drawable.btn);
////                hide.setBackgroundResource(R.drawable.button_border);
////                interest.setBackgroundResource(R.drawable.button_border);
//
//                next.setEnabled(true);
//            }
//        });
//        hide.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
////                hide.setBackgroundResource(R.drawable.btn);
////                show.setBackgroundResource(R.drawable.button_border);
////                interest.setBackgroundResource(R.drawable.button_border);
//                next.setEnabled(true);
//            }
//        });
//        interest.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
////                interest.setBackgroundResource(R.drawable.btn);
////                hide.setBackgroundResource(R.drawable.button_border);
////                show.setBackgroundResource(R.drawable.button_border);
//                next.setEnabled(true);
//            }
//        });

        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton)findViewById(checkedId);
                next.setEnabled(true);
            }
        });

        if(!FullName.getText().toString().isEmpty() && !EmailId.getText().toString().isEmpty() && !Password.getText().toString().isEmpty() &&
        !Code.getText().toString().isEmpty() &&!Phone.getText().toString().isEmpty() ){
            next.setEnabled(true);
        }

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
        String ms=getIntent().getStringExtra("Marital Status");
        String mt=getIntent().getStringExtra("Mother Tongue");
        String rlgn=getIntent().getStringExtra("Religion");

        next.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), VerifyNumber.class);
                intent.putExtra("Code",Code.getText().toString());
                intent.putExtra("Phone",Phone.getText().toString());
                intent.putExtra("Full Name",FullName.getText().toString());
                intent.putExtra("Email",EmailId.getText().toString());
                intent.putExtra("Password",Password.getText().toString());

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

              //  createUser();

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

//    private void createUser(){
//        String email = EmailId.getText().toString();
//        String password = Password.getText().toString();
//
//        if (TextUtils.isEmpty(email)){
//            EmailId.setError("Email cannot be empty");
//            EmailId.requestFocus();
//        }else if (TextUtils.isEmpty(password)){
//            Password.setError("Password cannot be empty");
//            Password.requestFocus();
//        }else{
//            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()){
//                        Toast.makeText(getApplicationContext(), "Email Registered", Toast.LENGTH_SHORT).show();
//                        //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }

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