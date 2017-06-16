package com.emredavarci.showcasepager;

import android.net.VpnService;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11.06.2017.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager vpShowcase = (ViewPager) findViewById(R.id.vpShowcase);
        vpShowcase.setPadding(110, 0, 110, 0);
        vpShowcase.setClipToPadding(false);
        vpShowcase.setPageMargin(0);

        int[] images = {R.drawable.one, R.drawable.two, R.drawable.three};

        vpShowcase.setAdapter(new ShowcaseAdapter(MainActivity.this, images));
        vpShowcase.setPageTransformer(false, new ViewPager.PageTransformer() {
            float scale = 1;
            private static final float MIN_SCALE_DEPTH = 0.8f;

            @Override
            public void transformPage(View page, float position) {

                // This calculation can be done only once
                float x = page.getX()/page.getWidth();
                x = (x - (int)(x));
                //Log.d("TAGG",String.valueOf(x));

                float p = Math.abs(position-x);
                if (p > 1) p = 1;
                scale = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1-p);

                page.setScaleX(scale);
                page.setScaleY(scale);

            }
        });
        vpShowcase.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position < vpShowcase.getAdapter().getCount() - 1){
                    View next = (View) vpShowcase.findViewWithTag("view" + String.valueOf(position+1));
                    next.setAlpha(0.1f + positionOffset);
                    View prev = (View) vpShowcase.findViewWithTag("view" + String.valueOf(position));
                    prev.setAlpha(1.1f - positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
