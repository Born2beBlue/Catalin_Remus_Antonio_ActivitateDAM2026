package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ComputerAdapter extends BaseAdapter {

    private List<Computer> computerList = null;
    private Context ctx;
    private int resursaLayout;

    public ComputerAdapter(List<Computer> computerList, Context ctx, int resursaLayout) {
        this.computerList = computerList;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return computerList.size();
    }

    @Override
    public Object getItem(int i) {
        return computerList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView marcaTV = v.findViewById(R.id.tv_marca);
        TextView detaliiTV = v.findViewById(R.id.tv_detalii);
        RatingBar ratingRB = v.findViewById(R.id.rb_row_rating);
        CheckBox ssdCB = v.findViewById(R.id.cb_row_ssd);

        Computer computer = (Computer) getItem(i);

        marcaTV.setText(computer.getMarca());
        detaliiTV.setText(computer.getTip() + " * " + computer.getSistemOperare() + " * " + computer.getPret() + " lei" + (computer.isPlacaVideoDedicata() ? " • GPU dedicat" : ""));
        ratingRB.setRating(computer.getRating());
        ssdCB.setChecked(computer.isAreSsd());

        return v;
    }
}