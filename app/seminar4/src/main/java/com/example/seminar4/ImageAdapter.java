package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<ImaginiDomeniu> imageList = null;
    private Context ctx;
    private int resursaLayout;

    public ImageAdapter(List<ImaginiDomeniu> imageList, Context ctx, int resursaLayout) {
        this.imageList = imageList;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        ImageView imageView = v.findViewById(R.id.imageView);
        TextView textView = v.findViewById(R.id.textView);

        ImaginiDomeniu imagine = (ImaginiDomeniu) getItem(i);

        imageView.setImageBitmap(imagine.getImagine());
        textView.setText(imagine.getTextAfisat());

        return v;
    }
}