package com.kuli.marathamatrimony.RegistrationActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.kuli.marathamatrimony.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VerifyNumber extends AppCompatActivity {
    private UnifiedNativeAd nativeAd;
    Button verify;
    EditText Code,Phone;
    private FirebaseAuth mAuth;
    private final String TAG="TAG";
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    DatabaseReference databaseReference;
    String OTP,PhoneNum,pNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_number);

        getSupportActionBar().setTitle("Verify Number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Code = findViewById(R.id.Code);
        Phone = findViewById(R.id.Phone);
        verify = findViewById(R.id.verify);
        mAuth = FirebaseAuth.getInstance();

        String no=getIntent().getStringExtra("Phone");
        String cd=getIntent().getStringExtra("Code");

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
        String fullname=getIntent().getStringExtra("Full Name");
        String email=getIntent().getStringExtra("Email");
        String pass=getIntent().getStringExtra("Password");

        Code.setText(cd);
        Phone.setText(no);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Phone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(VerifyNumber.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                } else if (Phone.getText().toString().trim().length() != 10) {
                    Toast.makeText(VerifyNumber.this, "Type valid Phone Number", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneNum = Phone.getText().toString();
                    OTP = generateOTP(6);

                    OkHttpClient client = new OkHttpClient();
                    String url = "https://jeevansathisearch.com/sms.php?name=" + fullname + "&otp=" + OTP + "&mobile=" + PhoneNum;
                    Request request = new Request.Builder().url(url).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Log.e("*** FAILED ***", "HTTP request failed ****", e);
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            if(response.isSuccessful()){
                                String str = response.body().string();

                                JSONObject jo = null;

                                try {
                                    jo = new JSONObject(str);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                JSONObject finalJo = jo;

                                Log.e("#### PASSED ####", str);
                                VerifyNumber.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        String status = "OK";
                                        try {

                                            status = finalJo.getString("status");

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        if (!status.equals("OK")) {
                                            Toast.makeText(getApplicationContext(), "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                                        }
                                        else {

                                            Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();

                                            pNum =Code.getText().toString() + Phone.getText().toString();

                                            Intent intent = new Intent(VerifyNumber.this, OTPVerify.class);
                                            intent.putExtra("phone",  Phone.getText().toString());
                                            intent.putExtra("verificationId", OTP);
                                            intent.putExtra("Full Name", fullname);
                                            intent.putExtra("Email", email);
                                            intent.putExtra("Password", pass);
                                            intent.putExtra("Marital Status", ms);
                                            intent.putExtra("Mother Tongue", mt);
                                            intent.putExtra("Religion", rlgn);
                                            intent.putExtra("Highest Education", highestEd);
                                            intent.putExtra("Degree Education", degreeEd);
                                            intent.putExtra("Employed In", employed);
                                            intent.putExtra("Occupation", occu);
                                            intent.putExtra("Income", incm);
                                            intent.putExtra("MF", MF);
                                            intent.putExtra("DOB", DOB);
                                            intent.putExtra("Height", Height);
                                            intent.putExtra("Country", Country);
                                            intent.putExtra("State", State);
                                            intent.putExtra("City", City);

                                            startActivity(intent);
                                        }

                                    }
                                });
                            }

                        }
                    });

//                    Toast.makeText(getApplicationContext(), "OTP Sent", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(VerifyNumber.this, OTPVerify.class);
//                    intent.putExtra("phone", pNum);
//                    intent.putExtra("verificationId", OTP);
//                    intent.putExtra("Full Name", fullname);
//                    intent.putExtra("Email", email);
//                    intent.putExtra("Password", pass);
//                    intent.putExtra("Marital Status", ms);
//                    intent.putExtra("Mother Tongue", mt);
//                    intent.putExtra("Religion", rlgn);
//                    intent.putExtra("Highest Education", highestEd);
//                    intent.putExtra("Degree Education", degreeEd);
//                    intent.putExtra("Employed In", employed);
//                    intent.putExtra("Occupation", occu);
//                    intent.putExtra("Income", incm);
//                    intent.putExtra("MF", MF);
//                    intent.putExtra("DOB", DOB);
//                    intent.putExtra("Height", Height);
//                    intent.putExtra("Country", Country);
//                    intent.putExtra("State", State);
//                    intent.putExtra("City", City);
//
//                    startActivity(intent);

                }
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
    static String generateOTP(int len) {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        char[] otp = new char[len];
        String randomOtp = "";
        for (int i = 0; i < len; i++) {
            randomOtp = randomOtp + numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        // System.out.println(randomOtp);
        return randomOtp;
    }




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
    }