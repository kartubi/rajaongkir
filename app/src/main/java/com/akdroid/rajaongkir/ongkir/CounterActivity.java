package com.akdroid.rajaongkir.ongkir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.akdroid.rajaongkir.R;
import com.akdroid.rajaongkir.ongkir.adapter.CityAdapter;
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
import static com.akdroid.rajaongkir.ApiEndPoint.Cost;

public class CounterActivity extends AppCompatActivity {
    String city, city_id, province , name , address , gender , bornday , telp;
    final static String TAG = CounterActivity.class.getSimpleName();

    TextView etData , etOngkir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        etData = findViewById(R.id.etData);
        etOngkir = findViewById(R.id.etOngkir);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("name");
            bornday = extras.getString("bornday");
            address = extras.getString("address");
            gender = extras.getString("gender");
            province = extras.getString("province");
            city_id = extras.getString("city_id");
            city = extras.getString("city");
            telp = extras.getString("telp");

           etData.setText(name + bornday + address + gender + province + city_id + city + telp);
           getCounter();
        }else{
            Log.d(TAG, "kosong");
        }

    }

    private void getCounter(){
        AndroidNetworking.post(BaseUrl + Cost)
                .addBodyParameter("origin", "152")
                .addBodyParameter("destination", city_id)
                .addBodyParameter("weight", "1000")
                .addBodyParameter("courier", "pos")
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
                            JSONObject jsonObject1 = null;
                            JSONObject jsonObject2 = null;
                            for(int i = 0; i < jsonArray.length(); i++)
                            {
                                jsonObject1 = jsonArray.getJSONObject(i);
                            }

                            assert jsonObject1 != null;
                            JSONArray jsonArray1 = jsonObject1.getJSONArray("costs");

                            for (int i = 0; i < jsonArray1.length(); i++){
                                jsonObject2 = jsonArray1.getJSONObject(i);
                            }

                            assert jsonObject2 != null;
                            JSONArray jsonArray2 = jsonObject2.getJSONArray("cost");
                            JSONObject jsonObject3 = jsonArray2.getJSONObject(0);

                            etOngkir.setText("Ongkir : " + String.valueOf(jsonObject3.getString("value")));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }

                    @Override
                    public void onError(ANError anError) {
                       // Log.d(TAG, String.valueOf(anError.getErrorCode()));
                    }
                });
    }
}
