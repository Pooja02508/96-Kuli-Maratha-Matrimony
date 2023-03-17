package com.kuli.marathamatrimony.RegistrationActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kuli.marathamatrimony.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView Gender, Religion, Phone, Name, Email, MotherTongue, MaritalStatus, Qualification, Income, Occupation;
    CircleImageView profileImage;
    String username, phonenumber, gender, religion, email, mothertongue, maritalstatus, qualification, income, occupation;
    String imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        Gender = findViewById(R.id.Gender);
        Religion = findViewById(R.id.Religion);
        Phone = findViewById(R.id.profilePhone);
        Name = findViewById(R.id.profileName);
        Email = findViewById(R.id.profileEmail);
        MotherTongue = findViewById(R.id.mother_tongue);
        MaritalStatus = findViewById(R.id.marital_status);
        Qualification = findViewById(R.id.Education);
        Occupation = findViewById(R.id.Occupation);
        Income = findViewById(R.id.Income);

        profileImage = findViewById(R.id.profileImage);

        String Mob = getIntent().getStringExtra("Mobile Number");

        OkHttpClient client = new OkHttpClient();
        String url = "https://script.google.com/macros/s/AKfycbx1oon7iSKWCwutZ2jMqrsIUI2UvLk9207QvRVzbOyGUxhJa78b8LMurbShNWbxxdMWtA/exec?mobile="+Mob;
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("*** FAILED ***", "HTTP request failed ****", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String str = response.body().string();
                JSONObject jo = null;
                if (response.isSuccessful()) {
                    try {
                        jo = new JSONObject(str);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.e("#### PASSED ####", str);
                    JSONObject finalJo = jo;
                    ProfileActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                username = finalJo.getString("Username");
                                phonenumber = finalJo.getString("Phonenumber");
                                gender = finalJo.getString("Gender");
                                religion = finalJo.getString("Religion");
                                email = finalJo.getString("Email");
                                mothertongue = finalJo.getString("Mother Tongue");
                                maritalstatus = finalJo.getString("Marital Status");
                                qualification = finalJo.getString("Highest Education");
                                income = finalJo.getString("Income");
                                occupation = finalJo.getString("Occupation");
                                imageUrl= finalJo.getString("Image");

                                Log.e("#### PASSED ####", imageUrl);

                                Name.setText("" + username);
                                Phone.setText("" + phonenumber);
                                Gender.setText("" + gender);
                                Religion.setText("" + religion);
                                Email.setText("" + email);
                                MotherTongue.setText("" + mothertongue);
                                MaritalStatus.setText("" + maritalstatus);
                                Qualification.setText("" + qualification);
                                Income.setText("" + income);
                                Occupation.setText("" + occupation);

                                Picasso.get().load("https://drive.google.com/uc?export=view&id="+imageUrl).into(profileImage);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //match here
                        }
                    });
                }
            }
        });

    }

}