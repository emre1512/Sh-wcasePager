package com.emredavarci.showcasepager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by user on 11.06.2017.
 */

public class ShowcaseAdapter extends PagerAdapter {

    int[] images;
    private Context context;

    public ShowcaseAdapter(Context context, int[] images){
        this.context = context;
        this.images = images;
    }

    @Override
    public Object instantiateItem(ViewGroup parent, int position) {

        ViewHolder viewHolder = new ViewHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.items_showcasepager, parent, false);

        view.setTag("view" + String.valueOf(position));

        viewHolder.ivItemImage = (ImageView) view.findViewById(R.id.ivItemImage);

        if(position != 0){
            view.setAlpha(0.1f);
            view.setScaleX(0.8f);
            view.setScaleY(0.8f);
        }

//        for (int i = 1; i < getCount(); i++){
//            View prev = (View) view.findViewWithTag("view" + position);
//            view.setAlpha(0.1f);
//        }

        viewHolder.ivItemImage.setBackground(context.getResources().getDrawable(images[position]));

        parent.addView(view);

        return view;
    }

    class ViewHolder {
        ImageView ivItemImage;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view == ((CardView) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }

    @Override
    public int getCount() {
        return images.length;
    }

}
