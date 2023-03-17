package com.kuli.marathamatrimony.RegistrationActivities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class OTPVerify extends AppCompatActivity {


    TextView mobile,resend;
    EditText o1,o2,o3,o4,o5,o6;
    Button verify;
    OTP_Receiver otp_receiver;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String verificationId,PhoneNum;
    String OTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verify);

        getSupportActionBar().setTitle("Verify Number");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mobile = findViewById(R.id.mobile);
        resend = findViewById(R.id.resend);
        o1 = findViewById(R.id.o1);
        o2 = findViewById(R.id.o2);
        o3 = findViewById(R.id.o3);
        o4 = findViewById(R.id.o4);
        o5 = findViewById(R.id.o5);
        o6 = findViewById(R.id.o6);
        verify = findViewById(R.id.verify);


        String fullname=getIntent().getStringExtra("Full Name");
        String no = getIntent().getStringExtra("phone");
        mobile.setText(no);
        verificationId = getIntent().getStringExtra("verificationId");

        editTextInput();
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInput();
                if (o1.getText().toString().trim().isEmpty() ||
                        o2.getText().toString().trim().isEmpty() ||
                        o3.getText().toString().trim().isEmpty() ||
                        o4.getText().toString().trim().isEmpty() ||
                        o5.getText().toString().trim().isEmpty() ||
                        o6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(OTPVerify.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                } else {
                    if (verificationId != null) {
                        String code = o1.getText().toString().trim() +
                                o2.getText().toString().trim() +
                                o3.getText().toString().trim() +
                                o4.getText().toString().trim() +
                                o5.getText().toString().trim() +
                                o6.getText().toString().trim();


                        if (verificationId.equals(code)) {

                            String MF = getIntent().getStringExtra("MF");
                            String DOB = getIntent().getStringExtra("DOB");
                            String Height = getIntent().getStringExtra("Height");
                            String Country = getIntent().getStringExtra("Country");
                            String State = getIntent().getStringExtra("State");
                            String City = getIntent().getStringExtra("City");
                            String highestEd = getIntent().getStringExtra("Highest Education");
                            String degreeEd = getIntent().getStringExtra("Degree Education");
                            String employed = getIntent().getStringExtra("Employed In");
                            String occu = getIntent().getStringExtra("Occupation");
                            String incm = getIntent().getStringExtra("Income");
                            String ms = getIntent().getStringExtra("Marital Status");
                            String mt = getIntent().getStringExtra("Mother Tongue");
                            String rlgn = getIntent().getStringExtra("Religion");
                            String fullname = getIntent().getStringExtra("Full Name");
                            String email = getIntent().getStringExtra("Email");
                            String pass = getIntent().getStringExtra("Password");

                            Intent intent = new Intent(OTPVerify.this, PhoneVerification.class);
                            intent.putExtra("Mobile Number", no);
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

                        } else {

                            Toast.makeText(OTPVerify.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    OTP = generateOTP(6);

                    OkHttpClient client = new OkHttpClient();
                    String url = "https://jeevansathisearch.com/sms.php?name=" + fullname + "&otp=" + OTP + "&mobile=" + no;
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
                                OTPVerify.this.runOnUiThread(new Runnable() {
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

                                            Toast.makeText(getApplicationContext(), "OTP Resent", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });
                            }

                        }
                    });

            }

        });
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

    private void editTextInput() {
        o1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                o2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        o2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                o3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        o3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                o4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        o4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                o5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        o5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                o6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
