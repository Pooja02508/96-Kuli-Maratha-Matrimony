package com.kuli.marathamatrimony.Fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kuli.marathamatrimony.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyProfileFragment extends Fragment {

    TextView Gender, Religion, Phone, Name, Email, MotherTongue, MaritalStatus, Qualification, Income, Occupation;
    CircleImageView profileImage;
    String username,phonenumber,gender,religion,email,mothertongue,maritalstatus,qualification,income,occupation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);

        Gender = root.findViewById(R.id.Gender);
        Religion = root.findViewById(R.id.Religion);
        Phone = root.findViewById(R.id.profilePhone);
        Name = root.findViewById(R.id.profileName);
        Email = root.findViewById(R.id.profileEmail);
        MotherTongue = root.findViewById(R.id.mother_tongue);
        MaritalStatus = root.findViewById(R.id.marital_status);
        Qualification = root.findViewById(R.id.Education);
        Occupation = root.findViewById(R.id.Occupation);
        Income = root.findViewById(R.id.Income);

        profileImage = root.findViewById(R.id.profileImage);


        OkHttpClient client = new OkHttpClient();
        String url = "https://script.google.com/macros/s/AKfycbz-z8t5kaMJ0H9DKCZ-WwYq9wV6JubigqspSiiSD5KOQNKdmzbj20wnXbRmnlk7edsNPw/exec?mobile=917391888336";
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
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                username = finalJo.getString("Username");
                                phonenumber=finalJo.getString("Phonenumber");
                                gender=finalJo.getString("Gender");
                                religion=finalJo.getString("Religion");
                                email=finalJo.getString("Email");
                                mothertongue=finalJo.getString("Mother Tongue");
                                maritalstatus=finalJo.getString("Marital Status");
                                qualification=finalJo.getString("Highest Education");
                                income=finalJo.getString("Income");
                                occupation=finalJo.getString("Occupation");


                                Name.setText(""+username);
                                Phone.setText(""+phonenumber);
                                Gender.setText(""+gender);
                                Religion.setText(""+religion);
                                Email.setText(""+email);
                                MotherTongue.setText(""+mothertongue);
                                MaritalStatus.setText(""+maritalstatus);
                                Qualification.setText(""+qualification);
                                Income.setText(""+income);
                                Occupation.setText(""+occupation);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //match here
                        }
                    });
                }
            }
        });

        return root;
    }

}




//  mAuth = FirebaseAuth.getInstance();
//          mFirestore = FirebaseFirestore.getInstance();
//          storageReference = FirebaseStorage.getInstance().getReference().child("profile images");
//          user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
//          String ph = mAuth.getCurrentUser().getPhoneNumber();
//
//
//          if(ph!=null) {
//
//          mFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//@Override
//public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//        if (task.isSuccessful()) {
//        if (task.getResult().exists()) {
//        String image = task.getResult().getString("image");
//        Glide.with(getActivity()).load(image).into(profileImage);
//        }
//        }
//        else {
//        Name.setText("User Name");
//        Phone.setText("Mobile Number");
//        Email.setText("Email");
//        Address.setText("Address");
//        Gender.setText("Gender");
//        Religion.setText("Religion");
//        MotherTongue.setText("Mother Tongue");
//        MaritalStatus.setText("Marital Status");
//        Qualification.setText("Qualification");
//        Income.setText("Income");
//        Occupation.setText("Occupation");
//        DOB.setText("DOB");
//        Height.setText("Height");
//        startActivity(new Intent(getActivity(), NavigationDrawer.class));
//        }
//        }
//        });
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(ph);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//        if (!snapshot.exists()) {
//        Phone.setText(ph);
//        Toast.makeText(getActivity(), "Profile not completed", Toast.LENGTH_SHORT).show();
//        } else {
//        String uName = snapshot.child("Full Name").getValue().toString();
//        String email = snapshot.child("Email").getValue().toString();
//        String country = snapshot.child("Country").getValue().toString();
//        String state = snapshot.child("State").getValue().toString();
//        String city = snapshot.child("City").getValue().toString();
//        String ms = snapshot.child("Marital Status").getValue().toString();
//        String mt = snapshot.child("Mother Tongue").getValue().toString();
//        String rlgn = snapshot.child("Religion").getValue().toString();
//        String gen = snapshot.child("Gender").getValue().toString();
//        String qual = snapshot.child("Highest Education").getValue().toString();
//        String incm = snapshot.child("Income").getValue().toString();
//        String ht = snapshot.child("Height").getValue().toString();
//        String dob = snapshot.child("Date of Birth").getValue().toString();
//        String occ = snapshot.child("Occupation").getValue().toString();
//        String mob = snapshot.child("Mobile Number").getValue().toString();
//
//        String addr = country + " , " + state + " , " + city;
//
//        Name.setText(uName);
//        Phone.setText(mob);
//        Email.setText(email);
//        Address.setText(addr);
//        Gender.setText(gen);
//        Religion.setText(rlgn);
//        MotherTongue.setText(mt);
//        MaritalStatus.setText(ms);
//        Qualification.setText(qual);
//        Income.setText(incm);
//        Occupation.setText(occ);
//        DOB.setText(dob);
//        Height.setText(ht);
//
//        }
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//        }
//        });
//        }
//        else {
//        Toast.makeText(getActivity(), "Complete Your Registration First", Toast.LENGTH_SHORT).show();
//        Name.setText("User Name");
//        Phone.setText("Mobile Number");
//        Email.setText("Email");
//        Address.setText("Address");
//        Gender.setText("Gender");
//        Religion.setText("Religion");
//        MotherTongue.setText("Mother Tongue");
//        MaritalStatus.setText("Marital Status");
//        Qualification.setText("Qualification");
//        Income.setText("Income");
//        Occupation.setText("Occupation");
//        DOB.setText("DOB");
//        Height.setText("Height");
//        }