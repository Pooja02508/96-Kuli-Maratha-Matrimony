package com.kuli.marathamatrimony.RegistrationActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.kuli.marathamatrimony.NavigationDrawer;
import com.kuli.marathamatrimony.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText phone;
    EditText password;
    ProgressBar progressBar;
    TextView tvRegisterHere;
    Button btnLogin;
    String phonenumber,pass,pwd;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        phone = findViewById(R.id.phone);
        password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressBar);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getApplicationContext(), NavigationDrawer.class);
            String mobNumber=phone.getText().toString();



            OkHttpClient client = new OkHttpClient();
            String url = "https://script.google.com/macros/s/AKfycbz-z8t5kaMJ0H9DKCZ-WwYq9wV6JubigqspSiiSD5KOQNKdmzbj20wnXbRmnlk7edsNPw/exec?mobile="+mobNumber;
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
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    pwd=password.getText().toString();
                                    phonenumber=finalJo.getString("Phonenumber");
                                    pass = finalJo.getString("Password");


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if(pass.equals(pwd)){
                                    intent.putExtra("Mobile Number",phonenumber);
                                    startActivity(intent);
                                }
                                else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    password.setText("");
                                    Toast.makeText(getApplicationContext(),"Mobile number and Password doesn't match!",Toast.LENGTH_SHORT).show();

                                }

                            }

                        });





                    }
                }
            });

        });
        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, CreateProfile.class));
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