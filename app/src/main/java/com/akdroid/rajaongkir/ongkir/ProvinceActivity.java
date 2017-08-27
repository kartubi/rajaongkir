package com.akdroid.rajaongkir.ongkir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.akdroid.rajaongkir.R;
import com.akdroid.rajaongkir.ongkir.adapter.ProvinceAdapter;
import com.akdroid.rajaongkir.ongkir.model.Ongkir;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.akdroid.rajaongkir.ApiEndPoint.ApiKey;
import static com.akdroid.rajaongkir.ApiEndPoint.BaseUrl;
import static com.akdroid.rajaongkir.ApiEndPoint.Province;

public class ProvinceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProvinceAdapter provinceAdapter;
    final static String TAG = ProvinceActivity.class.getSimpleName();
    String name, bornday, address, gender, telp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_province);
        getProvince();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            bornday = extras.getString("bornday");
            address = extras.getString("address");
            gender = extras.getString("gender");
            telp = extras.getString("telp");
            Log.d(TAG , gender);
        }


    }

    private void getProvince(){
        AndroidNetworking.get(BaseUrl + Province)
                .addHeaders("key", ApiKey)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("rajaongkir");
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            List<Ongkir> ongkirs = new ArrayList<>();
                            // Log.d(TAG , jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++){
                                Ongkir ongkir = new Ongkir();
                                JSONObject object = jsonArray.getJSONObject(i);
                                // Log.d(TAG , String.valueOf(ongkir.province_id = object.getLong("province_id")));
                                // Log.d(TAG, ongkir.province = object.getString("province"));
                                ongkir.province_id = object.getLong("province_id");
                                ongkir.province = object.getString("province");

                                ongkirs.add(ongkir);
                            }

                            recyclerView = findViewById(R.id.rcData);
                            provinceAdapter = new ProvinceAdapter(ongkirs ,R.layout.list_data , getApplicationContext(), ProvinceActivity.this);
                            recyclerView.setAdapter(provinceAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, String.valueOf(anError.getErrorCode()));
                    }
                });
    }

    public String  getName(){
        return name;
    }

    public String getBornday(){
        return bornday;
    }

    public String getGender(){
        return gender;
    }

    public String getAddress(){
        return address;
    }

    public String getTelp(){
        return telp;
    }

}
