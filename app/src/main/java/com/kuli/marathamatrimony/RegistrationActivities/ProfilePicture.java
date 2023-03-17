package com.kuli.marathamatrimony.RegistrationActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.kuli.marathamatrimony.NavigationDrawer;
import com.kuli.marathamatrimony.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfilePicture extends AppCompatActivity {
    private UnifiedNativeAd nativeAd;
    CircleImageView profileImage;
    TextView upload,skip;
    Button next;
    String profileimage,encodedImage;
    private int PICK_IMAGE_REQUEST = 1;
    Bitmap rbitmap;
    ProgressDialog progressDialog;
    static String imagePath = "";
    String img="";
    Map <String, String> userMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userMap = new HashMap<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_picture);

        getSupportActionBar().setTitle("Profile Picture");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        profileImage = findViewById(R.id.profileImage);
        upload = findViewById(R.id.upload);
        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);
        upload.setVisibility(View.INVISIBLE);

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

        userMap.put("Phone", getIntent().getStringExtra("Mobile Number"));
        userMap.put("Gender",getIntent().getStringExtra("MF"));
        userMap.put("DOB",getIntent().getStringExtra("DOB"));
        userMap.put("Height",Height);
        userMap.put("Country",Country);
        userMap.put("State",State);
        userMap.put("City",City);
        userMap.put("Highest Education",highesteducation);
        userMap.put("Degree Education",degreeeducation);
        userMap.put("Employed In",employedin);
        userMap.put("Occupation",occupation);
        userMap.put("Income",income);
        userMap.put("Marital Status",maritalstatus);
        userMap.put("Mother Tongue",mothertongue);
        userMap.put("Religion",religion);
        userMap.put("Full Name",fullname);
        userMap.put("Email",email);
        userMap.put("Password",pass);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SAVE_NAME_AND_PHOTO();
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePicture.this, NavigationDrawer.class));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfilePicture.this, NavigationDrawer.class);

                progressDialog = new ProgressDialog(ProfilePicture.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                postDataUsingVolley(userMap);
//                updateData(phone, fullname, email, pass, maritalstatus, mothertongue, religion, highesteducation, degreeeducation, employedin, occupation, income, gender, DOB, Height, Country, State, City);
//
//                intent.putExtra("Mobile Number",phone);
//
//                startActivity(intent);
            }
        });


    }


   //Insert data in google sheet

    private void updateRowInExcel(Map<String, String> map) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbw_dQzyWiUm1iPxYXnCPQrvh_AbaEn4jrliIzK2wv5sJQeKaoAupGpVqg7-M6KBR_Mi/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfilePicture.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("action", "addItem");
                params.put("fullname", map.get("Full Name"));
                params.put("phone", map.get("Phone"));
                params.put("email", map.get("Email"));
                params.put("password", map.get("Password"));
                params.put("maritalstatus", map.get("Marital Status"));
                params.put("mothertongue", map.get("Mother Tongue"));
                params.put("religion", map.get("Religion"));
                params.put("highesteducation", map.get("Highest Education"));
                params.put("degreeeducation", map.get("Degree Education"));
                params.put("employedin", map.get("Employed In"));
                params.put("occupation", map.get("Occupation"));
                params.put("income", map.get("Income"));
                params.put("gender", map.get("Gender"));
                params.put("dob", map.get("DOB"));
                params.put("height", map.get("Height"));
                params.put("country", map.get("Country"));
                params.put("state", map.get("State"));
                params.put("city", map.get("City"));

                // img=phone+"/"+fullname;
                params.put("imagePath",map.get("imagePath"));
                return params;
            }
        };

        int socketTimeOut = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    // upload image to drive

    private void postDataUsingVolley(Map <String, String> map) {
        final Map <String, String> innerMap = map;

        String url = "https://script.google.com/macros/s/AKfycbwdpy-AXgsL3ly001e6t296r4C5vnJOillTFJnTOwQyfKu2pYE8iiEA9xB8zPCvcYutpw/exec";

        RequestQueue queue = Volley.newRequestQueue(ProfilePicture.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject imageJson = new JSONObject(response);
                    imagePath = imageJson.getString("image_path");
                    innerMap.put("imagePath",imagePath);
                    Intent intent=new Intent(ProfilePicture.this, NavigationDrawer.class);
                    updateRowInExcel(innerMap);
                    intent.putExtra("Mobile Number",innerMap.get("Phone"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //  Toast.makeText(ProfilePicture.this, "SUCCESS "+imagePath , Toast.LENGTH_SHORT).show();
                Log.e("-->", ""+response);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfilePicture.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();
                params.put("filename",innerMap.get("Phone"));
                params.put("imageformat","jpeg");
                params.put("file",encodedImage);
                return params;

            }
        };
        queue.add(request);
    }




    // Select Image

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                rbitmap = getResizedBitmap(bitmap,250);//Setting the Bitmap to ImageView
                profileimage = getStringImage(rbitmap);
                profileImage.setImageBitmap(rbitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }
}

//    private void SAVE_NAME_AND_PHOTO() {
//        final String user_name = getIntent().getStringExtra("Full Name");
////        final String user_name="test";
//        if (!TextUtils.isEmpty(user_name)) {
//
//            ProgressDialog progressDialog
//                    = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//
//
//            final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            final StorageReference ImagesPath = storageReference.child("Users").child(user_id + ".jpg");
//            ImagesPath.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if (!task.isSuccessful()) {
//                        throw task.getException();
//                    }
//                    return ImagesPath.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if (task.isSuccessful()) {
//
//                        Uri downUri = task.getResult();
//                        downUri.toString();
//
//                        Map<String, Object> userMap = new HashMap<>();
//                        userMap.put("image", downUri.toString());
//                        userMap.put("name", user_name);
//                        Glide.with(ProfilePicture.this).load(downUri).into(profileImage);
//
//                        progressDialog.dismiss();
//
//                        Toast.makeText(ProfilePicture.this, "Successfully uploaded profile.", Toast.LENGTH_SHORT).show();
//
//                        next.setEnabled(true);
//                        mFirestore.collection("Users").document(user_id).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                            }
//                        });
//                    }else {
//                        String ERROR = task.getException().getMessage();
//                        progressDialog.dismiss();
//                        Toast.makeText(ProfilePicture.this, ERROR, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }else {
//            Toast.makeText(ProfilePicture.this, "Enter your name.", Toast.LENGTH_SHORT).show();
//        }
//    }


//    AdView mAdView = findViewById(R.id.adView);
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





























//package com.kuli.marathamatrimony;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.RetryPolicy;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import de.hdodenhof.circleimageview.CircleImageView;
//
//
//public class ProfilePicture extends AppCompatActivity {
//    private UnifiedNativeAd nativeAd;
//    CircleImageView profileImage;
//    TextView upload,skip;
//    Button next;
//    String profileimage,encodedImage;
//    private int PICK_IMAGE_REQUEST = 1;
//    Bitmap rbitmap;
//    ProgressDialog progressDialog;
//    String imagePath = "";
//    String img="";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.profile_picture);
//
//        getSupportActionBar().setTitle("Profile Picture");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        profileImage = findViewById(R.id.profileImage);
//        upload = findViewById(R.id.upload);
//        skip = findViewById(R.id.skip);
//        next = findViewById(R.id.next);
//        upload.setVisibility(View.INVISIBLE);
//
//        String phone = getIntent().getStringExtra("Mobile Number");
//        String gender = getIntent().getStringExtra("MF");
//        String DOB = getIntent().getStringExtra("DOB");
//        String Height = getIntent().getStringExtra("Height");
//        String Country = getIntent().getStringExtra("Country");
//        String State = getIntent().getStringExtra("State");
//        String City = getIntent().getStringExtra("City");
//        String highesteducation = getIntent().getStringExtra("Highest Education");
//        String degreeeducation = getIntent().getStringExtra("Degree Education");
//        String employedin = getIntent().getStringExtra("Employed In");
//        String occupation = getIntent().getStringExtra("Occupation");
//        String income = getIntent().getStringExtra("Income");
//        String maritalstatus = getIntent().getStringExtra("Marital Status");
//        String mothertongue = getIntent().getStringExtra("Mother Tongue");
//        String religion = getIntent().getStringExtra("Religion");
//        String fullname = getIntent().getStringExtra("Full Name");
//        String email = getIntent().getStringExtra("Email");
//        String pass = getIntent().getStringExtra("Password");
//
//
//
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // SAVE_NAME_AND_PHOTO();
//            }
//        });
//        profileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showFileChooser();
//            }
//        });
//
//        skip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ProfilePicture.this, NavigationDrawer.class));
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent intent=new Intent(ProfilePicture.this, NavigationDrawer.class);
//
//                progressDialog = new ProgressDialog(ProfilePicture.this);
//                progressDialog.setMessage("Loading...");
//                progressDialog.show();
//                postDataUsingVolley();
//                updateData(phone, fullname, email, pass, maritalstatus, mothertongue, religion, highesteducation, degreeeducation, employedin, occupation, income, gender, DOB, Height, Country, State, City);
//
//                intent.putExtra("Mobile Number",phone);
//
//                startActivity(intent);
//            }
//        });
//
//
//    }
//
//
//    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        float bitmapRatio = (float)width / (float) height;
//        if (bitmapRatio > 1) {
//            width = maxSize;
//            height = (int) (width / bitmapRatio);
//        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
//        }
//        return Bitmap.createScaledBitmap(image, width, height, true);
//
//    }
//
//
//    public String getStringImage(Bitmap bmp) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//        return encodedImage;
//    }
//
//    private void updateData(String phone, String fullname, String email, String pass, String maritalstatus, String mothertongue, String religion, String highesteducation, String degreeEd, String employed,
//                            String occupation, String income, String gender, String dob, String height, String country, String state, String city) {
//
//
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbw_dQzyWiUm1iPxYXnCPQrvh_AbaEn4jrliIzK2wv5sJQeKaoAupGpVqg7-M6KBR_Mi/exec",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(ProfilePicture.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("action", "addItem");
//                params.put("fullname", fullname);
//                params.put("phone", phone);
//                params.put("email", email);
//                params.put("password", pass);
//                params.put("maritalstatus", maritalstatus);
//                params.put("mothertongue", mothertongue);
//                params.put("religion", religion);
//                params.put("highesteducation", highesteducation);
//                params.put("degreeeducation", degreeEd);
//                params.put("employedin", employed);
//                params.put("occupation", occupation);
//                params.put("income", income);
//                params.put("gender", gender);
//                params.put("dob", dob);
//                params.put("height", height);
//                params.put("country", country);
//                params.put("state", state);
//                params.put("city", city);
//
//                img=phone+"/"+fullname;
//                params.put("imagePath",img);
//
//
//                return params;
//            }
//        };
//
//        int socketTimeOut = 50000;
//
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(retryPolicy);
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        queue.add(stringRequest);
//
//
//    }
//
//    private String postDataUsingVolley() {
//
//        String url = "https://script.google.com/macros/s/AKfycbwWk9RcLbn1gLP9VOVd3JDljd9zEvt5EGedTpOkbRzRCYrio26jtx_z5QtAqc_7PQTGrQ/exec";
//
//        RequestQueue queue = Volley.newRequestQueue(ProfilePicture.this);
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONObject imageJson = new JSONObject(response);
//                    imagePath = imageJson.getString("image_path");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//              //  Toast.makeText(ProfilePicture.this, "SUCCESS "+imagePath , Toast.LENGTH_SHORT).show();
//                Log.e("-->", ""+response);
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ProfilePicture.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                // below line we are creating a map for
//                // storing our values in key and value pair.
//                Map<String, String> params = new HashMap<String, String>();
//
//                params.put("filename",img);
//                params.put("imageformat","jpeg");
//                params.put("file",encodedImage);
//                return params;
//
//            }
//        };
//
//        queue.add(request);
//        return imagePath;
//    }
//
//        private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri filePath = data.getData();
//            try {
//                //Getting the Bitmap from Gallery
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                rbitmap = getResizedBitmap(bitmap,250);//Setting the Bitmap to ImageView
//                profileimage = getStringImage(rbitmap);
//                profileImage.setImageBitmap(rbitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    private void SAVE_NAME_AND_PHOTO() {
////        final String user_name = getIntent().getStringExtra("Full Name");
//////        final String user_name="test";
////        if (!TextUtils.isEmpty(user_name)) {
////
////            ProgressDialog progressDialog
////                    = new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.show();
////
////
////            final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
////            final StorageReference ImagesPath = storageReference.child("Users").child(user_id + ".jpg");
////            ImagesPath.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
////                @Override
////                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
////                    if (!task.isSuccessful()) {
////                        throw task.getException();
////                    }
////                    return ImagesPath.getDownloadUrl();
////                }
////            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
////                @Override
////                public void onComplete(@NonNull Task<Uri> task) {
////                    if (task.isSuccessful()) {
////
////                        Uri downUri = task.getResult();
////                        downUri.toString();
////
////                        Map<String, Object> userMap = new HashMap<>();
////                        userMap.put("image", downUri.toString());
////                        userMap.put("name", user_name);
////                        Glide.with(ProfilePicture.this).load(downUri).into(profileImage);
////
////                        progressDialog.dismiss();
////
////                        Toast.makeText(ProfilePicture.this, "Successfully uploaded profile.", Toast.LENGTH_SHORT).show();
////
////                        next.setEnabled(true);
////                        mFirestore.collection("Users").document(user_id).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
////                            @Override
////                            public void onSuccess(Void aVoid) {
////                            }
////                        });
////                    }else {
////                        String ERROR = task.getException().getMessage();
////                        progressDialog.dismiss();
////                        Toast.makeText(ProfilePicture.this, ERROR, Toast.LENGTH_SHORT).show();
////                    }
////                }
////            });
////        }else {
////            Toast.makeText(ProfilePicture.this, "Enter your name.", Toast.LENGTH_SHORT).show();
////        }
////    }
//
//
////    AdView mAdView = findViewById(R.id.adView);
////        AdRequest adRequest = new AdRequest.Builder().build();
////        mAdView.loadAd(adRequest);
//
////        MobileAds.initialize(getApplicationContext(), getString(R.string.ADMOB_ADS_UNIT_ID));
////
////        AdLoader.Builder builder = new AdLoader.Builder(getApplicationContext(), getString(R.string.ADMOB_ADS_UNIT_ID));
////        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
////            @Override
////            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
////                if (nativeAd != null) {
////                    nativeAd.destroy();
////                }
////                nativeAd = unifiedNativeAd;
////                FrameLayout frameLayout = findViewById(R.id.fl_adplaceholder);
////                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater().inflate(R.layout.ad_unified, null);
////
////                populateUnifiedNativeAdView(unifiedNativeAd, adView);
////                frameLayout.removeAllViews();
////                frameLayout.addView(adView);
////
////            }
////        });
////
////        NativeAdOptions adOptions = new NativeAdOptions.Builder().build();
////        builder.withNativeAdOptions(adOptions);
////        AdLoader adLoader = builder.withAdListener (new AdListener(){
////
////            @Override
////            public void onAdFailedToLoad(int i) {
////
////            }
////        }).build();
////        adLoader.loadAd(new AdRequest.Builder().build());
////    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
////
////        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
////        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
////        adView.setBodyView(adView.findViewById(R.id.ad_body));
////        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
////        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
////        adView.setPriceView(adView.findViewById(R.id.ad_price));
////        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
////        adView.setStoreView(adView.findViewById(R.id.ad_store));
////        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
////
////        ((TextView)adView.getHeadlineView()).setText(nativeAd.getHeadline());
////        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
////
////        if (nativeAd.getBody() == null) {
////            adView.getBodyView().setVisibility(View.INVISIBLE);
////        } else {
////            adView.getBodyView().setVisibility(View.VISIBLE);
////            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
////        }
////        if (nativeAd.getCallToAction() == null) {
////            adView.getCallToActionView().setVisibility(View.INVISIBLE);
////        } else {
////            adView.getCallToActionView().setVisibility(View.VISIBLE);
////            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
////        }
////        if (nativeAd.getIcon() == null) {
////            adView.getIconView().setVisibility(View.GONE);
////        } else {
////            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
////            adView.getIconView().setVisibility(View.VISIBLE);
////        }
////        if (nativeAd.getPrice() == null) {
////            adView.getPriceView().setVisibility(View.INVISIBLE);
////        } else {
////            adView.getPriceView().setVisibility(View.VISIBLE);
////            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
////        }
////        if (nativeAd.getStore() == null) {
////            adView.getStoreView().setVisibility(View.INVISIBLE);
////        } else {
////            adView.getStoreView().setVisibility(View.VISIBLE);
////            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
////
////        }
////        if (nativeAd.getStarRating() == null) {
////            adView.getStarRatingView().setVisibility(View.INVISIBLE);
////        } else {
////            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
////            adView.getStarRatingView().setVisibility(View.VISIBLE);
////        }
////        if (nativeAd.getAdvertiser() == null) {
////            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
////
////        } else {
////            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
////            adView.getAdvertiserView().setVisibility(View.VISIBLE);
////        }
////        adView.setNativeAd(nativeAd);
////    }