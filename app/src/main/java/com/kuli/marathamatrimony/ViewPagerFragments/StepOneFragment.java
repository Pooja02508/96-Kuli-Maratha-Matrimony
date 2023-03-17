package com.kuli.marathamatrimony.ViewPagerFragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.kuli.marathamatrimony.R;
import com.kuli.marathamatrimony.SliderAdapter;
import com.kuli.marathamatrimony.SliderItems;

import java.util.ArrayList;
import java.util.List;


public class StepOneFragment extends Fragment {

    private ViewPager2 vp1,vp2;
    private Handler sliderHandler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_step_one, container, false);

        vp1 =root.findViewById(R.id.vp1);
        vp2=root.findViewById(R.id.vp2);


        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.m1));
        sliderItems.add(new SliderItems(R.drawable.m2));
        sliderItems.add(new SliderItems(R.drawable.m3));
        sliderItems.add(new SliderItems(R.drawable.m4));
        sliderItems.add(new SliderItems(R.drawable.m5));
        sliderItems.add(new SliderItems(R.drawable.m6));
        sliderItems.add(new SliderItems(R.drawable.m7));
        sliderItems.add(new SliderItems(R.drawable.m1));
        sliderItems.add(new SliderItems(R.drawable.m2));
        sliderItems.add(new SliderItems(R.drawable.m3));
        sliderItems.add(new SliderItems(R.drawable.m4));
        sliderItems.add(new SliderItems(R.drawable.m5));
        sliderItems.add(new SliderItems(R.drawable.m6));
        sliderItems.add(new SliderItems(R.drawable.m7));

        List<SliderItems> sliderItems2 = new ArrayList<>();
        sliderItems2.add(new SliderItems(R.drawable.f1));
        sliderItems2.add(new SliderItems(R.drawable.f2));
        sliderItems2.add(new SliderItems(R.drawable.f3));
        sliderItems2.add(new SliderItems(R.drawable.f4));
        sliderItems2.add(new SliderItems(R.drawable.f5));
        sliderItems2.add(new SliderItems(R.drawable.f6));
        sliderItems2.add(new SliderItems(R.drawable.f7));
        sliderItems2.add(new SliderItems(R.drawable.f8));
        sliderItems2.add(new SliderItems(R.drawable.f9));
        sliderItems2.add(new SliderItems(R.drawable.f10));
        sliderItems2.add(new SliderItems(R.drawable.f11));
        sliderItems2.add(new SliderItems(R.drawable.f12));
        sliderItems2.add(new SliderItems(R.drawable.f13));
        sliderItems2.add(new SliderItems(R.drawable.f14));

        vp1.setAdapter(new SliderAdapter(sliderItems,vp1));
        vp1.setClipToPadding(false);
        vp1.setClipChildren(false);
        vp1.setOffscreenPageLimit(3);
        vp1.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        vp2.setAdapter(new SliderAdapter(sliderItems2,vp2));
        vp2.setClipToPadding(false);
        vp2.setClipChildren(false);
        vp2.setOffscreenPageLimit(3);
        vp2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        int layoutDirectionLtr = ViewPager2.LAYOUT_DIRECTION_RTL;
        vp2.setLayoutDirection(layoutDirectionLtr);



        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float a=Math.abs(position);
                float r = 1-a;
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        vp1.setPageTransformer(compositePageTransformer);
        vp1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 700); // slide duration 2 seconds
            }
        });

        vp2.setPageTransformer(compositePageTransformer);
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 700); // slide duration 2 seconds
            }
        });


        return root;

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            vp1.setCurrentItem(vp1.getCurrentItem() + 1);
            vp2.setCurrentItem(vp2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 500);
    }

}
