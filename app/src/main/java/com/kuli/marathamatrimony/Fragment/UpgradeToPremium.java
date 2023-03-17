package com.kuli.marathamatrimony.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.kuli.marathamatrimony.NavigationDrawer;
import com.kuli.marathamatrimony.R;

import java.util.ArrayList;


public class UpgradeToPremium extends Fragment {
    private WebView webView;
    final int UPI_PAYMENT = 0;
    String TAG = "main";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_upgrade_to_premium, container, false);

        TextView textView1 = root.findViewById(R.id.tv1);
        String text1 ="Rs3,500.00  Rs551.00";
        SpannableString ss1 = new SpannableString(text1);
        StrikethroughSpan strikethroughSpan1 = new StrikethroughSpan();
        ss1.setSpan(strikethroughSpan1, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(ss1);

        TextView textView2 = root.findViewById(R.id.tv2);
        String text2 ="Rs10000.00  Rs1500.00";
        SpannableString ss2 = new SpannableString(text2);
        StrikethroughSpan strikethroughSpan2 = new StrikethroughSpan();
        ss2.setSpan(strikethroughSpan2, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(ss2);

        TextView textView3 = root.findViewById(R.id.tv3);
        String text3 ="Rs25000.00  Rs3000.00";
        SpannableString ss3 = new SpannableString(text3);
        StrikethroughSpan strikethroughSpan3 = new StrikethroughSpan();
        ss3.setSpan(strikethroughSpan3, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView3.setText(ss3);




        ImageView goldiv=(ImageView)root.findViewById(R.id.gold);
        goldiv.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View v){
                Intent intent = gold(getActivity());

            }
        });

        ImageView platinumiv=(ImageView)root.findViewById(R.id.platinum);
        platinumiv.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View v){
                Intent intent = platinum(getActivity());

            }
        });

        ImageView diamondiv=(ImageView)root.findViewById(R.id.diamond);
        diamondiv.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View v){
                Intent intent = diamond(getActivity());

            }
        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(getActivity(), NavigationDrawer.class));
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);


        return root;
    }

    private Intent gold(FragmentActivity activity) {
        payUsingUpi("Shaadi Ke Rishtey", "jeevansathisearch@upi", "Gold Membership", "551");
        return null;
    }
    private Intent platinum(FragmentActivity activity) {
        payUsingUpi("Shaadi Ke Rishtey", "jeevansathisearch@upi", "Platinum Membership", "1500");
        return null;
    }
    private Intent diamond(FragmentActivity activity) {
        payUsingUpi("Shaadi Ke Rishtey", "jeevansathisearch@upi", "Diamond Membership", "3000");
        return null;
    }

        private void payUsingUpi(String name,String upiId, String note, String amount) {
            Log.e("main ", "name "+name +"--up--"+upiId+"--"+ note+"--"+amount);
            Uri uri = Uri.parse("upi://pay").buildUpon()
                    .appendQueryParameter("pa", upiId)
                    .appendQueryParameter("pn", name)
                    //.appendQueryParameter("mc", "")
                    //.appendQueryParameter("tid", "02125412")
                    //.appendQueryParameter("tr", "25584584")
                    .appendQueryParameter("tn", note)
                    .appendQueryParameter("am", amount)
                    .appendQueryParameter("cu", "INR")
                    //.appendQueryParameter("refUrl", "blueapp")
                    .build();


            Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
            upiPayIntent.setData(uri);

            // will always show a dialog to user to choose an app
            Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

            // check if intent resolves
            if(null != chooser.resolveActivity(getActivity().getPackageManager())) {
                startActivityForResult(chooser, UPI_PAYMENT);
            } else {
                Toast.makeText(getActivity(),"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            Log.e("main ", "response "+resultCode );
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
            switch (requestCode) {
                case UPI_PAYMENT:
                    if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                        if (data != null) {
                            String trxt = data.getStringExtra("response");
                            Log.e("UPI", "onActivityResult: " + trxt);
                            ArrayList<String> dataList = new ArrayList<>();
                            dataList.add(trxt);
                            upiPaymentDataOperation(dataList);
                        } else {
                            Log.e("UPI", "onActivityResult: " + "Return data is null");
                            ArrayList<String> dataList = new ArrayList<>();
                            dataList.add("nothing");
                            upiPaymentDataOperation(dataList);
                        }
                    } else {
                        //when user simply back without payment
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                    break;
            }
        }

        private void upiPaymentDataOperation(ArrayList<String> data) {
            if (isConnectionAvailable(getContext())) {
                String str = data.get(0);
                Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
                String paymentCancel = "";
                if(str == null) str = "discard";
                String status = "";
                String approvalRefNo = "";
                String response[] = str.split("&");
                for (int i = 0; i < response.length; i++) {
                    String equalStr[] = response[i].split("=");
                    if(equalStr.length >= 2) {
                        if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                            status = equalStr[1].toLowerCase();
                        }
                        else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                            approvalRefNo = equalStr[1];
                        }
                    }
                    else {
                        paymentCancel = "Payment cancelled by user.";
                    }
                }

                if (status.equals("success")) {
                    //Code to handle successful transaction here.
                    Toast.makeText(getActivity(), "Transaction successful.", Toast.LENGTH_SHORT).show();
                    Log.e("UPI", "payment successfull: "+approvalRefNo);
                }
                else if("Payment cancelled by user.".equals(paymentCancel)) {
                    Toast.makeText(getActivity(), "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                    Log.e("UPI", "Cancelled by user: "+approvalRefNo);

                }
                else {
                    Toast.makeText(getActivity(), "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                    Log.e("UPI", "failed payment: "+approvalRefNo);

                }
            } else {
                Log.e("UPI", "Internet issue: ");

                Toast.makeText(getActivity(), "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
            }
        }

        public static boolean isConnectionAvailable(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()
                        && netInfo.isConnectedOrConnecting()
                        && netInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        }


}