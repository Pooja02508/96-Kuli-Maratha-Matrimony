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
import android.widget.RelativeLayout;
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


public class CareerDetails extends AppCompatActivity {
    private UnifiedNativeAd nativeAd;
    Spinner employedIn,income;
    Spinner occupation;
    Spinner highestEducation,ugDegree;
    Button next;
    Button purchase_premium;
    RelativeLayout RL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career_details);
        getSupportActionBar().setTitle("Career Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        highestEducation=findViewById(R.id.highestEducation);
        ugDegree=findViewById(R.id.ugDegree);
        employedIn=findViewById(R.id.employedIn);
        income=findViewById(R.id.income);
        occupation=findViewById(R.id.occupation);
        next=findViewById(R.id.next);
        RL=findViewById(R.id.RL);
        purchase_premium=findViewById(R.id.purchase_premium);
        next.setEnabled(false);

//-----------------------Education Spinner-------------------------------------------------------
        List<String> highestEducation_array = new ArrayList<String>();
        highestEducation_array.add("Highest Qualification");
        highestEducation_array.add("Engineering/Design");
        highestEducation_array.add("B.Arch");
        highestEducation_array.add("B.Des");
        highestEducation_array.add("B.E/B.Tech");
        highestEducation_array.add("B.Pharma");
        highestEducation_array.add("M.Arch");
        highestEducation_array.add("M.Des");
        highestEducation_array.add("M.E/M.Tech");
        highestEducation_array.add("M.Pharma");
        highestEducation_array.add("M.S. (Engineering)");
        highestEducation_array.add("Computers");
        highestEducation_array.add("B.IT");
        highestEducation_array.add("BCA");
        highestEducation_array.add("MCA/PGDCA");
        highestEducation_array.add("Finance/Commerce");
        highestEducation_array.add("B.Com");
        highestEducation_array.add("CA");
        highestEducation_array.add("CFA");
        highestEducation_array.add("CS");
        highestEducation_array.add("ICWA");
        highestEducation_array.add("M.Com");
        highestEducation_array.add("Management");
        highestEducation_array.add("BBA");
        highestEducation_array.add("BHM");
        highestEducation_array.add("MBA/PGDM");
        highestEducation_array.add("Medicine");
        highestEducation_array.add("BAMS");
        highestEducation_array.add("BDS");
        highestEducation_array.add("BHMS");
        highestEducation_array.add("BPT");
        highestEducation_array.add("BVSc.");
        highestEducation_array.add("DM");
        highestEducation_array.add("M.D.");
        highestEducation_array.add("M.S.(Medicine)");
        highestEducation_array.add("MBBS");
        highestEducation_array.add("MCh");
        highestEducation_array.add("MDS");
        highestEducation_array.add("MPT");
        highestEducation_array.add("MVSc.");
        highestEducation_array.add("Law");
        highestEducation_array.add("BL/LLB");
        highestEducation_array.add("ML/LLM");
        highestEducation_array.add("Arts/Science");
        highestEducation_array.add("B.A");
        highestEducation_array.add("B.Ed");
        highestEducation_array.add("B.Science");
        highestEducation_array.add("BFA");
        highestEducation_array.add("BJMC");
        highestEducation_array.add("M.A");
        highestEducation_array.add("M.Ed");
        highestEducation_array.add("M.Sc");
        highestEducation_array.add("MFA");
        highestEducation_array.add("MJMC");
        highestEducation_array.add("MSW");
        highestEducation_array.add("Doctorate");
        highestEducation_array.add("M.Phil");
        highestEducation_array.add("Ph.D");
        highestEducation_array.add("Non-Graduate");
        highestEducation_array.add("High School");
        highestEducation_array.add("Trade School");
        highestEducation_array.add("Diploma");
        highestEducation_array.add("Others");
        highestEducation_array.add("Other");
        ArrayAdapter<String> educationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, highestEducation_array) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 ||position==1 || position==11 || position==15 || position==22 || position==26 || position==40 || position==43
                        || position==55 || position==58 || position==62)  {
                    return false;
                }

                else {
                    return true;
                }
            }
            @SuppressLint("ResourceAsColor")
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position==1 || position==11 || position==15 || position==22 || position==26 || position==40 || position==43
                        || position==55 || position==58 || position==62) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else if(position==0) {
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        educationAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        highestEducation.setAdapter(educationAdapter);

//-----------------------------UG Degree Spinner--------------------------------------------------------------------------
        List<String> ugDegree_array = new ArrayList<String>();
        ugDegree_array.add("Graduation Degree");
        ugDegree_array.add("Engineering/Design");
        ugDegree_array.add("B.Arch");
        ugDegree_array.add("B.Des");
        ugDegree_array.add("B.E/B.Tech");
        ugDegree_array.add("B.Pharma");
        ugDegree_array.add("Computers");
        ugDegree_array.add("B.IT");
        ugDegree_array.add("BCA");
        ugDegree_array.add("Finance/Commerce");
        ugDegree_array.add("B.Com");
        ugDegree_array.add("Management");
        ugDegree_array.add("BBA");
        ugDegree_array.add("BHM");
        ugDegree_array.add("Medicine");
        ugDegree_array.add("BAMS");
        ugDegree_array.add("BDS");
        ugDegree_array.add("BHMS");
        ugDegree_array.add("BPT");
        ugDegree_array.add("BVSc.");
        ugDegree_array.add("MBBS");
        ugDegree_array.add("Law");
        ugDegree_array.add("BL/LLB");
        ugDegree_array.add("Arts/Science");
        ugDegree_array.add("B.A");
        ugDegree_array.add("B.Ed");
        ugDegree_array.add("B.Sc");
        ugDegree_array.add("BFA");
        ugDegree_array.add("BJMC");
        ugDegree_array.add("Others");
        ugDegree_array.add("Other");
        ArrayAdapter<String> ugDegreeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ugDegree_array) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 || position==1 || position==6 || position==9 || position==11 || position==14 || position==21 || position==23 || position==29)  {
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
                if( position==1 || position==6 || position==9 || position==11 || position==14 || position==21 || position==23 || position==29) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else if(position==0){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
       // ugDegreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ugDegreeAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        ugDegree.setAdapter(ugDegreeAdapter);

//------------------------------ Employed In Spinner-------------------------------------------------------------
        List<String> employedIn_array = new ArrayList<String>();
        employedIn_array.add("Employed In");//0
        employedIn_array.add("Private Sector");//1
        employedIn_array.add("Government/Public Sector");//2
        employedIn_array.add("Civil Services");//3
        employedIn_array.add("Defense");//4
        employedIn_array.add("Business/Self Employed");//5
        employedIn_array.add("Not Working");//6
        ArrayAdapter<String> employedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, employedIn_array) {
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
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
       // employedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employedAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        employedIn.setAdapter(employedAdapter);

//---------------------------- Occupation In Spinner---------------------------------------------------------
        List<String> occupation_array1 = new ArrayList<String>();
        occupation_array1.add("Administration");
        occupation_array1.add("Admin Professional");
        occupation_array1.add("Clerk");
        occupation_array1.add("Operator/Technician");
        occupation_array1.add("Secretary/Front Office");
        occupation_array1.add("Advertising,Media and Entertainment");
        occupation_array1.add("Actor/Model");
        occupation_array1.add("Advertising Professional");
        occupation_array1.add("Film/Entertainment Professional");
        occupation_array1.add("Journalist");
        occupation_array1.add("Media Professional");
        occupation_array1.add("PR Professional");
        occupation_array1.add("Agricultural");
        occupation_array1.add("Agriculture Professional");
        occupation_array1.add("Farming");
        occupation_array1.add("Airline and Aviation");
        occupation_array1.add("Airline Professional");
        occupation_array1.add("Flight Attendant");
        occupation_array1.add("Pilot");
        occupation_array1.add("Architecture");
        occupation_array1.add("Architect");
        occupation_array1.add("Banking and Finance");
        occupation_array1.add("Accounting Professional New 2");
        occupation_array1.add("Auditor");
        occupation_array1.add("Banking Professional");
        occupation_array1.add("Charted accountant");
        occupation_array1.add("Finance Professional");
        occupation_array1.add("BPO/Customer Service");
        occupation_array1.add("BPO/ITes Professional");
        occupation_array1.add("Customer Service");
        occupation_array1.add("Corporate Management Professional");
        occupation_array1.add("Analyst");
        occupation_array1.add("Consultant");
        occupation_array1.add("Corporate Communication");
        occupation_array1.add("Corporate Planning");
        occupation_array1.add("HR Professional");
        occupation_array1.add("Marketing Professional");
        occupation_array1.add("Operations Management");
        occupation_array1.add("Product Manager");
        occupation_array1.add("Program Manager");
        occupation_array1.add("Project Manager-Non IT");
        occupation_array1.add("Sales Professional");
        occupation_array1.add("Sr.Manager/Manager");
        occupation_array1.add("Subject Matter Expert");
        occupation_array1.add("Doctor");
        occupation_array1.add("Dentist");
        occupation_array1.add("Doctor");
        occupation_array1.add("Surgeon");
        occupation_array1.add("Education and Training");
        occupation_array1.add("Education Professional");
        occupation_array1.add("Educational Institution Owner");
        occupation_array1.add("Librarian");
        occupation_array1.add("Professor/Lecturer");
        occupation_array1.add("Research Assistant");
        occupation_array1.add("Teacher");
        occupation_array1.add("Engineering");
        occupation_array1.add("Electronics Engineering");
        occupation_array1.add("Hradware/Telecom Engineer");
        occupation_array1.add("Non IT Engineer");
        occupation_array1.add("Quality Assurance Engineer");
        occupation_array1.add("Hospitality");
        occupation_array1.add("Hotels/Hospitality Professional");
        occupation_array1.add("Legal");
        occupation_array1.add("Lawyer and Legal Professional");
        occupation_array1.add("Merchant");
        occupation_array1.add("Mariner");
        occupation_array1.add("Merchant naval Officer");
        occupation_array1.add("Other Medical and Healthcare");
        occupation_array1.add("Medical/Healthcare Professional");
        occupation_array1.add("Nurse");
        occupation_array1.add("Paramedic");
        occupation_array1.add("Pharmacist");
        occupation_array1.add("Physiotherapist");
        occupation_array1.add("Psychologist");
        occupation_array1.add("Veterinary doctor");
        occupation_array1.add("Science and Research");
        occupation_array1.add("Research Professional");
        occupation_array1.add("Science Professional");
        occupation_array1.add("Scientist");
        occupation_array1.add("Software and IT");
        occupation_array1.add("Animator");
        occupation_array1.add("Cyber/Network Security");
        occupation_array1.add("Project Lead-IT");
        occupation_array1.add("Project Manager-IT");
        occupation_array1.add("Quality Assurance Engineer-IT");
        occupation_array1.add("Software Professional");
        occupation_array1.add("UI/UX Designer");
        occupation_array1.add("Web/Graphic Designer");
        occupation_array1.add("Top Management");
        occupation_array1.add("CxO/Chairman/President/Director");
        occupation_array1.add("VP/AVP/GM/DGM");
        occupation_array1.add("Others");
        occupation_array1.add("Agent");
        occupation_array1.add("Artist");
        occupation_array1.add("Beautician");
        occupation_array1.add("Broker");
        occupation_array1.add("Fashion Designer");
        occupation_array1.add("Fitness Professional");
        occupation_array1.add("Interior Designer");
        occupation_array1.add("Security Professional");
        occupation_array1.add("Singer");
        occupation_array1.add("Social Services/NGO/Volunteer");
        occupation_array1.add("Sportsperson");
        occupation_array1.add("Travel Professional");
        occupation_array1.add("Writer");
        occupation_array1.add("Others");

//-----------------------------Civil Services Array-----------------------------------------------
        List<String> occupation_array2 = new ArrayList<String>();
        occupation_array2.add("Civil Services");
        occupation_array2.add("Civil Services(IAS/IPS/IRS/IES/IFS)");

//------------------------------ Defense Array---------------------------------------------------
        List<String> occupation_array3 = new ArrayList<String>();
        occupation_array3.add("Administration");
        occupation_array3.add("Admin Professional");
        occupation_array3.add("Clerk");
        occupation_array3.add("Operator/Technician");
        occupation_array3.add("Airline/Aviation");
        occupation_array3.add("Airline Professional");
        occupation_array3.add("Pilot");
        occupation_array3.add("Armed Forces");
        occupation_array3.add("Air Force");
        occupation_array3.add("Army");
        occupation_array3.add("Defense Services");
        occupation_array3.add("Navy");
        occupation_array3.add("Doctor");
        occupation_array3.add("Dentist");
        occupation_array3.add("Doctor");
        occupation_array3.add("Surgeon");
        occupation_array3.add("Education and Training");
        occupation_array3.add("Education Professional");
        occupation_array3.add("Educational Institution Owner");
        occupation_array3.add("Librarian");
        occupation_array3.add("Professor/Lecturer");
        occupation_array3.add("Research Assistant");
        occupation_array3.add("Teacher");
        occupation_array3.add("Engineering");
        occupation_array3.add("Electronics Engineering");
        occupation_array3.add("Hradware/Telecom Engineer");
        occupation_array3.add("Non IT Engineer");
        occupation_array3.add("Quality Assurance Engineer");
        occupation_array3.add("Law Enforcement");
        occupation_array3.add("Law Enforcement Officer");
        occupation_array3.add("Police");
        occupation_array3.add("Legal");
        occupation_array3.add("Lawyer and Legal Professional");
        occupation_array3.add("Merchant Navy");
        occupation_array3.add("Mariner");
        occupation_array3.add("Merchant naval Officer");
        occupation_array3.add("Other Medical and Healthcare");
        occupation_array3.add("Medical/Healthcare Professional");
        occupation_array3.add("Nurse");
        occupation_array3.add("Paramedic");
        occupation_array3.add("Pharmacist");
        occupation_array3.add("Physiotherapist");
        occupation_array3.add("Psychologist");
        occupation_array3.add("Veterinary doctor");
        occupation_array3.add("Science and Research");
        occupation_array3.add("Research Professional");
        occupation_array3.add("Science Professional");
        occupation_array3.add("Scientist");
        occupation_array3.add("Software and IT");
        occupation_array3.add("Cyber/Network Security");
        occupation_array3.add("Quality Assurance Engineer-IT");
        occupation_array3.add("Software Professional");

//-------------------------- Not Working Array---------------------------------------------------
        List<String> occupation_array5 = new ArrayList<String>();
        occupation_array5.add("Not Working");
        occupation_array5.add("Looking for job");
        occupation_array5.add("Not Working");
        occupation_array5.add("Retired");
        occupation_array5.add("Student");

         List<String> occupation_array4 = new ArrayList<String>();
        occupation_array4.add("Advertising,Media and Entertainment");
        occupation_array4.add("Actor/Model");
        occupation_array4.add("Advertising Professional");
        occupation_array4.add("Film/Entertainment Professional");
        occupation_array4.add("Agricultural");
        occupation_array4.add("Agriculture Professional");
        occupation_array4.add("Farming");
        occupation_array4.add("Architecture");
        occupation_array4.add("Architect");
        occupation_array4.add("Banking and Finance");
        occupation_array4.add("Charted accountant");
        occupation_array4.add("Corporate Management Professional");
        occupation_array4.add("Subject Matter Expert");
        occupation_array4.add("Doctor");
        occupation_array4.add("Dentist");
        occupation_array4.add("Doctor");
        occupation_array4.add("Surgeon");
        occupation_array4.add("Education and Training");
        occupation_array4.add("Education Professional");
        occupation_array4.add("Educational Institution Owner");
        occupation_array4.add("Librarian");
        occupation_array4.add("Professor/Lecturer");
        occupation_array4.add("Research Assistant");
        occupation_array4.add("Teacher");
        occupation_array4.add("Hospitality");
        occupation_array4.add("Hotels/Hospitality Professional");
        occupation_array4.add("Legal");
        occupation_array4.add("Lawyer and Legal Professional");
        occupation_array4.add("Other Medical and Healthcare");
        occupation_array4.add("Medical/Healthcare Professional");
        occupation_array4.add("Nurse");
        occupation_array4.add("Paramedic");
        occupation_array4.add("Pharmacist");
        occupation_array4.add("Physiotherapist");
        occupation_array4.add("Psychologist");
        occupation_array4.add("Veterinary doctor");
        occupation_array4.add("Science and Research");
        occupation_array4.add("Research Professional");
        occupation_array4.add("Science Professional");
        occupation_array4.add("Scientist");
        occupation_array4.add("Software and IT");
        occupation_array4.add("Animator");
        occupation_array4.add("Top Management");
        occupation_array4.add("CxO/Chairman/President/Director");
        occupation_array4.add("VP/AVP/GM/DGM");
        occupation_array4.add("Others");
        occupation_array4.add("Agent");
        occupation_array4.add("Artist");
        occupation_array4.add("Beautician");
        occupation_array4.add("Broker");
        occupation_array4.add("Business Owner/Entrepreneur");
        occupation_array4.add("Businessperson");
        occupation_array4.add("Fashion Designer");
        occupation_array4.add("Fitness Professional");
        occupation_array4.add("Interior Designer");
        occupation_array4.add("Singer");
        occupation_array4.add("Social Services/NGO/Volunteer");
        occupation_array4.add("Sportsperson");
        occupation_array4.add("Travel Professional");
        occupation_array4.add("Writer");
        occupation_array4.add("Others");

        ArrayAdapter<String> occupationAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupation_array1) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 || position==5 || position==12||position==15||position==19||position==21||position==27||position==30||position==44
                        ||position==48||position==55||position==60||position==62||position==64||position==67||position==75||position==79||position==88||position==91)  {
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
                if(position==0 || position==5 || position==12||position==15||position==19||position==21||position==27||position==30||position==44
                ||position==48||position==55||position==60||position==62||position==64||position==67||position==75||position==79||position==88||position==91) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> occupationAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupation_array2) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0)  {
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
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> occupationAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupation_array3) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 || position==4 || position==7||position==12||position==16||position==23||position==28||position==31||position==33
                        ||position==36||position==44||position==48)  {
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
                if(position==0 || position==4 || position==7||position==12||position==16||position==23||position==28||position==31||position==33
                        ||position==36||position==44||position==48) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> occupationAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupation_array4) {
            @Override
            public boolean isEnabled(int position) {
                if(position==0 || position==4 || position==7||position==9||position==11||position==13||position==17||position==24||position==26
                        ||position==28||position==36||position==40 ||position==42 || position==45)  {
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
                if(position==0 || position==4 || position==7||position==9||position==11||position==13||position==17||position==24||position==26
                        ||position==28||position==36||position==40 ||position==42 || position==45) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        ArrayAdapter<String> occupationAdapter5= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, occupation_array5) {
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
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        employedIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position==1 ||position==2){
                    occupationAdapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    occupation.setAdapter(occupationAdapter1);
                }
                else if(position==3){
                    occupationAdapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    occupation.setAdapter(occupationAdapter2);
                }
                else if(position==4){
                    occupationAdapter3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    occupation.setAdapter(occupationAdapter3);
                }
                else if(position==5){
                    occupationAdapter4.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    occupation.setAdapter(occupationAdapter4);
                }
                else if(position==6){
                    occupationAdapter5.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    occupation.setAdapter(occupationAdapter5);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
//--------------------------Income spinner----------------------------------
        List<String> income_array = new ArrayList<String>();
        income_array.add("Annual Income");
        income_array.add("No Income");
        income_array.add("Rs. 0-1 Lakh");
        income_array.add("Rs. 1-2 Lakh");
        income_array.add("Rs. 2-3 Lakh");
        income_array.add("Rs. 3-4 Lakh");
        income_array.add("Rs. 4-5 Lakh");
        income_array.add("Rs. 5-7.5 Lakh");
        income_array.add("Rs. 7.5-10 Lakh");
        income_array.add("Rs. 10-15 Lakh");
        income_array.add("Rs. 15-20 Lakh");
        income_array.add("Rs. 20-25 Lakh");
        income_array.add("Rs. 25-35 Lakh");
        income_array.add("Rs. 35-50 Lakh");
        income_array.add("Rs. 50-70 Lakh");
        income_array.add("Rs. 70 Lakh-1 crore");
        income_array.add("Rs. 1 crore and above");
        ArrayAdapter<String> incomeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, income_array) {
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
                if(position==0) {
                    // Set the disable item text color
                    tv.setTextColor(R.color.blue);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                next.setEnabled(true);
                return view;
            }
        };
      //  incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        income.setAdapter(incomeAdapter);

        String MF=getIntent().getStringExtra("MF");
        String DOB=getIntent().getStringExtra("DOB");
        String Height=getIntent().getStringExtra("Height");
        String Country=getIntent().getStringExtra("Country");
        String State=getIntent().getStringExtra("State");
        String City=getIntent().getStringExtra("City");

       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String highestEd = highestEducation.getSelectedItem().toString();
               String degreeEd = ugDegree.getSelectedItem().toString();
               String employed = employedIn.getSelectedItem().toString();
               String occu = occupation.getSelectedItem().toString();
               String incm = income.getSelectedItem().toString();
               Intent intent=new Intent(getApplicationContext(), SocialDetails.class);
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
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
//   private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
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