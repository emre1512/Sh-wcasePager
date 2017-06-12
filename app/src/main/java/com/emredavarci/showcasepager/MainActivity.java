package com.emredavarci.showcasepager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        vpShowcase.setPageMargin(-10);

        int[] images = {R.drawable.one, R.drawable.two, R.drawable.three};

        vpShowcase.setAdapter(new ShowcaseAdapter(MainActivity.this, images));
        vpShowcase.setPageTransformer(false, new ViewPager.PageTransformer() {
            float scale;
            private static final float MIN_SCALE_DEPTH = 0.8f;
            float translationX;

            @Override
            public void transformPage(View page, float position) {
                if (position > -3 && position < 3) {
                    scale = MIN_SCALE_DEPTH + (1 - MIN_SCALE_DEPTH) * (1 - Math.abs(position));
                } else {
                    scale = 1;
                }

//                if (position >= -1 && position <= 1) {
//                    scale = Math.max(MIN_SCALE_ZOOM, 1 - Math.abs(position));
//                } else {
//                    scale = 1;
//                }

                page.setScaleX(scale);
                page.setScaleY(scale);

            }
        });
        vpShowcase.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//                View cur = (View) vpShowcase.findViewWithTag("view" + position);
//                cur.setAlpha(cur.getAlpha() - positionOffset);

                if(position < vpShowcase.getAdapter().getCount() - 1){
                    View next = (View) vpShowcase.findViewWithTag("view" + String.valueOf(position+1));
                    next.setAlpha(0.1f + positionOffset);
//                    next.setScaleX(0.8f + positionOffset);
//                    next.setScaleY(0.8f + positionOffset);
                    View prev = (View) vpShowcase.findViewWithTag("view" + String.valueOf(position));
                    prev.setAlpha(1.1f - positionOffset);
//                    prev.setScaleX(1.2f - positionOffset);
//                    prev.setScaleY(1.2f - positionOffset);
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
