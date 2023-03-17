package com.kuli.marathamatrimony.RegistrationActivities;


import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.kuli.marathamatrimony.R;
import com.vikktorn.picker.City;
import com.vikktorn.picker.CityPicker;
import com.vikktorn.picker.Country;
import com.vikktorn.picker.CountryPicker;
import com.vikktorn.picker.OnCityPickerListener;
import com.vikktorn.picker.OnCountryPickerListener;
import com.vikktorn.picker.OnStatePickerListener;
import com.vikktorn.picker.State;
import com.vikktorn.picker.StatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonalDetails extends AppCompatActivity implements OnStatePickerListener, OnCountryPickerListener, OnCityPickerListener, AdapterView.OnItemSelectedListener  {
    private UnifiedNativeAd nativeAd;
    public static int countryID, stateID;
    private CountryPicker countryPicker;
    private StatePicker statePicker;
    private CityPicker cityPicker;
    public static List<State> stateObject;
    public static List<City> cityObject;
    String date,MF;
    TextView pick_country,pick_state,pick_city,dob;
    TextView stateText,cityText;
    RadioGroup gender;
    RadioButton male,female, genderradioButton;;
    Button next;
    TextView ok,edit,Gender,birthDate;
    AlertDialog dialog;
    Spinner height;
    LinearLayout genderLayout;
    RelativeLayout RL;
    private Dialog dialog1;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_details);

        getSupportActionBar().setTitle("Create Profile For");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();
        try {
            getStateJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            getCityJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        gender = findViewById(R.id.gender);
        dob = findViewById(R.id.dob);
        height = findViewById(R.id.height);
        pick_city = findViewById(R.id.pickCity);
        pick_country = findViewById(R.id.pickCountry);
        pick_state = findViewById(R.id.pickState);
        stateText = findViewById(R.id.stateText);
        cityText = findViewById(R.id.cityText);
        next = findViewById(R.id.next);
        genderLayout = findViewById(R.id.genderLayout);
        RL = findViewById(R.id.RL);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

//        pick_city.setKeyListener(null);
//        pick_country.setKeyListener(null);
//        pick_state.setKeyListener(null);
//        dob.setKeyListener(null);


        pick_state.setVisibility(View.INVISIBLE);
        pick_city.setVisibility(View.INVISIBLE);
        stateText.setVisibility(View.INVISIBLE);
        cityText.setVisibility(View.INVISIBLE);
        next.setEnabled(false);
        countryPicker = new CountryPicker.Builder().with(this).listener(this).build();

        Intent intent = getIntent();
        String Self = intent.getStringExtra("Self");
        String Relative = intent.getStringExtra("Relative");
        String Son = intent.getStringExtra("Son");
        String Daughter = intent.getStringExtra("Daughter");
        String Brother = intent.getStringExtra("Brother");
        String Sister = intent.getStringExtra("Sister");
        String Client = intent.getStringExtra("Client");
        String Friend = intent.getStringExtra("Friend");
        String Men=intent.getStringExtra("Second Marriage Men");
        String Women=intent.getStringExtra("Second Marriage Women");


        if (Son != null || Daughter != null || Brother != null || Sister != null || Men!=null || Women!=null) {
            if (genderLayout.getVisibility() != GONE) {
                genderLayout.setVisibility(GONE);
                RL.removeView(genderLayout);

            }
        }

        if (Son != null || Brother != null || Men!=null ) {
            MF="Male";
        }

        if (Daughter != null || Sister != null || Women!=null ) {
            MF="Female";
        }

        RL.removeView(pick_state);
        RL.removeView(pick_city);
        RL.removeView(stateText);
        RL.removeView(cityText);


        //Set Date Code
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PersonalDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("TAG", "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);

                date = day + "/" + month + "/" + year;
                dob.setText(date);
                birthDate.setText("Date of Birth : " + date);
            }
        };

        //Height Spinner Set Code
        List<String> height_array = new ArrayList<String>();
        height_array.add("Height");
        height_array.add("4'0\"(1.22 mts)");
        height_array.add("4'1\"(1.24 mts)");
        height_array.add("4'2\"(1.28 mts)");
        height_array.add("4'3\"(1.31 mts)");
        height_array.add("4'4\"(1.34 mts)");
        height_array.add("4'5\"(1.35 mts)");
        height_array.add("4'6\"(1.37 mts)");
        height_array.add("4'7\"(1.40 mts)");
        height_array.add("4'8\"(1.42 mts)");
        height_array.add("4'9\"(1.45 mts)");
        height_array.add("4'10\"(1.47 mts)");
        height_array.add("4'11\"(1.50 mts)");
        height_array.add("5'0\"(1.52 mts)");
        height_array.add("5'1\"(1.55 mts)");
        height_array.add("5'2\"(1.58 mts)");
        height_array.add("5'3\"(1.60 mts)");
        height_array.add("5'4\"(1.63 mts)");
        height_array.add("5'5\"(1.65 mts)");
        height_array.add("5'6\"(1.68 mts)");
        height_array.add("5'7\"(1.70 mts)");
        height_array.add("5'8\"(1.73 mts)");
        height_array.add("5'9\"(1.75 mts)");
        height_array.add("5'10\"(1.78 mts)");
        height_array.add("5'11\"(1.80 mts)");
        height_array.add("6'0\"(1.83 mts)");
        height_array.add("6'1\"(1.85 mts)");
        height_array.add("6'2\"(1.88 mts)");
        height_array.add("6'3\"(1.91 mts)");
        height_array.add("6'4\"(1.93 mts)");
        height_array.add("6'5\"(1.96 mts)");
        height_array.add("6'6\"(1.98 mts)");
        height_array.add("6'7\"(2.01 mts)");
        height_array.add("6'8\"(2.03 mts)");
        height_array.add("6'9\"(2.06 mts)");
        height_array.add("6'10\"(208 mts)");
        height_array.add("6'11\"(2.11 mts)");
        height_array.add("7'(2.13 mts) plus");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, height_array) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
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
                if(position==0) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        height.setAdapter(dataAdapter);

        pick_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryPicker.showDialog(getSupportFragmentManager());
            }
        });

        pick_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statePicker.showDialog(getSupportFragmentManager());
            }
        });

        pick_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.showDialog(getSupportFragmentManager());
                next.setEnabled(true);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = gender.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                }
                else{
                    Gender.setText(""+genderradioButton.getText().toString());
                }
                dialog1.show();
            }
        });
        //Dialog box code
        dialog1 = new Dialog(PersonalDetails.this);
        dialog1.setContentView(R.layout.alert);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
        }
        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog1.setCancelable(false); //Optional
        dialog1.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog1.setCanceledOnTouchOutside(true);
        ok = dialog1.findViewById(R.id.btn_ok);
        edit = dialog1.findViewById(R.id.btn_edit);
        Gender = dialog1.findViewById(R.id.Gender);
        birthDate = dialog1.findViewById(R.id.birthDate);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonalDetails.this, CareerDetails.class);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });

                String heightText = height.getSelectedItem().toString();

                if (Relative != null || Client != null || Friend != null || Self != null) {
                    intent.putExtra("MF",Gender.getText());
                }
                else {
                    intent.putExtra("MF",MF);
                }
                intent.putExtra("DOB",dob.getText().toString());
                intent.putExtra("Height",heightText);
                intent.putExtra("Country",pick_country.getText().toString());
                intent.putExtra("State",pick_state.getText().toString());
                intent.putExtra("City",pick_city.getText().toString());
                startActivity(intent);

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }
    public void onNothingSelected(AdapterView<?> arg0) { }
    public void initView(){
        stateObject = new ArrayList<>();
        cityObject = new ArrayList<>();
    }
    @Override
    public void onSelectCountry(Country country) {
        // get country name and country ID
        pick_country.setText(country.getName());
        stateText.setVisibility(View.VISIBLE);
        RL.addView(stateText);
        pick_state.setVisibility(View.VISIBLE);
        RL.addView(pick_state);

        countryID = country.getCountryId();
        statePicker.equalStateObject.clear();
        cityPicker.equalCityObject.clear();

        // GET STATES OF SELECTED COUNTRY
        for(int i = 0; i < stateObject.size(); i++) {
            // init state picker
            statePicker = new StatePicker.Builder().with(this).listener(this).build();
            State stateData = new State();
            if (stateObject.get(i).getCountryId() == countryID) {

                stateData.setStateId(stateObject.get(i).getStateId());
                stateData.setStateName(stateObject.get(i).getStateName());
                stateData.setCountryId(stateObject.get(i).getCountryId());
                stateData.setFlag(country.getFlag());
                statePicker.equalStateObject.add(stateData);
            }
        }
    }
    @Override
    public void onSelectState(State state) {

        cityPicker.equalCityObject.clear();
        pick_state.setText(state.getStateName());
        stateID = state.getStateId();
        pick_city.setVisibility(View.VISIBLE);
        RL.addView(pick_city);
        cityText.setVisibility(View.VISIBLE);
        RL.addView(cityText);

        for(int i = 0; i < cityObject.size(); i++) {
            cityPicker = new CityPicker.Builder().with(this).listener(this).build();
            City cityData = new City();
            if (cityObject.get(i).getStateId() == stateID) {
                cityData.setCityId(cityObject.get(i).getCityId());
                cityData.setCityName(cityObject.get(i).getCityName());
                cityData.setStateId(cityObject.get(i).getStateId());
                cityPicker.equalCityObject.add(cityData);
            }

        }
    }
    @Override
    public void onSelectCity(City city) {
        pick_city.setText(city.getCityName());
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }
    public void getStateJson() throws JSONException {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("states.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject(json);
        JSONArray events = jsonObject.getJSONArray("states");
        for (int j = 0; j < events.length(); j++) {
            JSONObject cit = events.getJSONObject(j);
            State stateData = new State();

            stateData.setStateId(Integer.parseInt(cit.getString("id")));
            stateData.setStateName(cit.getString("name"));
            stateData.setCountryId(Integer.parseInt(cit.getString("country_id")));
            stateObject.add(stateData);
        }
    }
    public void getCityJson() throws JSONException {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("cities.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject(json);
        JSONArray events = jsonObject.getJSONArray("cities");
        for (int j = 0; j < events.length(); j++) {
            JSONObject cit = events.getJSONObject(j);
            City cityData = new City();

            cityData.setCityId(Integer.parseInt(cit.getString("id")));
            cityData.setCityName(cit.getString("name"));
            cityData.setStateId(Integer.parseInt(cit.getString("state_id")));
            cityObject.add(cityData);
        }
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