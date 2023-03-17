package com.kuli.marathamatrimony.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.kuli.marathamatrimony.NavigationDrawer;
import com.kuli.marathamatrimony.R;


public class WhoViewedMyProfile extends Fragment {
    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_who_viewed_my_profile, container, false);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(getActivity(), NavigationDrawer.class));
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);


        return root;
    }
}