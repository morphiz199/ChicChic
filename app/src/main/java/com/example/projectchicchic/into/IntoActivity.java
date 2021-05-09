package com.example.projectchicchic.into;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.projectchicchic.R;

public class IntoActivity extends AppCompatActivity {

    ImageView logo,splashImg;

    private static final int NUM_PAGE =3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;

    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);

        logo = findViewById(R.id.logo);
        splashImg = findViewById(R.id.img);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        anim = AnimationUtils.loadAnimation(this, R.anim.o_b_anim);
        viewPager.startAnimation(anim);

        splashImg.animate().translationY(-2400).setDuration(1000).setStartDelay(1000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(1000);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{


        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGE;
        }
    }
}