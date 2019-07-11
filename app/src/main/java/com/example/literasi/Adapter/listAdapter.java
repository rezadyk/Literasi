package com.example.literasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.literasi.R;
import com.example.literasi.list;

import java.util.List;


public class listAdapter extends ArrayAdapter<list> {
    private List<list>materiList;
    private Context mCtx;

    public listAdapter(List<list> P, Context C){
        super(C, R.layout.list_item,P);
        this.materiList = P;
        this.mCtx = C;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item,null,true);
        TextView id = (TextView) view.findViewById(R.id.id);
        TextView judul = (TextView) view.findViewById(R.id.judul);

        list materi = materiList.get(position);
        judul.setText(materi.getJudul());
        id.setText(materi.getId());

        return view;
    }
}
