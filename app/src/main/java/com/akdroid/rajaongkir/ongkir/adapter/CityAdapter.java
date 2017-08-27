package com.akdroid.rajaongkir.ongkir.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akdroid.rajaongkir.MainActivity;
import com.akdroid.rajaongkir.R;
import com.akdroid.rajaongkir.ongkir.CityActivity;
import com.akdroid.rajaongkir.ongkir.model.Ongkir;

import java.util.List;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.OngkirViewHolder>{

    private List<Ongkir> ongkirs;
    private int rowLayout;
    private Context context;
    CityActivity cityActivity;

    public static class OngkirViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linear_data;
        TextView tvData;



        public OngkirViewHolder(View v) {
            super(v);
            linear_data = v.findViewById(R.id.linear_data);
            tvData = v.findViewById(R.id.tvData);
        }


    }

    public CityAdapter(List<Ongkir> ongkirs, int rowLayout, Context context, CityActivity cityActivity) {
        this.ongkirs = ongkirs;
        this.rowLayout = rowLayout;
        this.context = context;
        this.cityActivity = cityActivity;
    }



    @Override
    public OngkirViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new OngkirViewHolder(view);
    }


    @Override
    public void onBindViewHolder(OngkirViewHolder holder, final int position) {
        holder.tvData.setText(ongkirs.get(position).city_name);

        holder.tvData.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                i.putExtra("city_id", String.valueOf(ongkirs.get(position).city_id));
                i.putExtra("city", String.valueOf(ongkirs.get(position).city_name));
                i.putExtra("province", String.valueOf(cityActivity.getProvince()));
                i.putExtra("name", cityActivity.getName());
                i.putExtra("address", cityActivity.getAddress());
                i.putExtra("telp", cityActivity.getTelp());
                i.putExtra("gender", cityActivity.getGender());
                i.putExtra("bornday", cityActivity.getBornday());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ongkirs.size();
    }

}