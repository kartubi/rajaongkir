package com.akdroid.rajaongkir;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akdroid.rajaongkir.ongkir.CounterActivity;
import com.akdroid.rajaongkir.ongkir.ProvinceActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String province,city_id, city, gender , bornday, address , name, telp;
    final static String TAG = MainActivity.class.getSimpleName();
    TextView etCity , etProvince , etBornDay;
    EditText etName , etNoTelp , etAddress ;
    private RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddress = findViewById(R.id.etAddress);
        etName = findViewById(R.id.etName);
        etNoTelp = findViewById(R.id.etNotelp);
        etCity = findViewById(R.id.etCity);
        etProvince = findViewById(R.id.etProvince);
        radioGroup = findViewById(R.id.radioGroup);
        etBornDay = findViewById(R.id.etBornDay);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);

        radioGroup.clearCheck();



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
                if (null != radioButton && i > -1){
                    gender = String.valueOf(radioButton.getText());
                    Log.d(TAG , "kelaminnya adalah : " + gender);
                }
            }
        });

        checkKeyboard();

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
            Log.d(TAG, "provinsi : " + province + "city id : " + city_id + "city name : " + city);

            etCity.setText(city);
            etProvince.setText(province);
            etName.setText(name);
            etAddress.setText(address);
            etBornDay.setText(bornday);
            etNoTelp.setText(telp);


            Log.d(TAG , gender);
            if (gender.equals("perempuan")){
                radioButton2.setChecked(true);
            }else{
                radioButton1.setChecked(true);
            }

        }


    }

    public void getCalendar(View view){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Log.d(TAG , dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        bornday = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        etBornDay.setText(bornday);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void checkKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void listProvince(View view){

        name = etName.getText().toString();
        address = etAddress.getText().toString();
        telp = etNoTelp.getText().toString();

        if (gender != null && bornday != null && name != null && address != null && telp != null){
            Intent i = new Intent(getApplicationContext(), ProvinceActivity.class);
            i.putExtra("name", name);
            i.putExtra("address", address);
            i.putExtra("telp", telp);
            i.putExtra("gender", gender);
            i.putExtra("bornday", bornday);
            Log.d(TAG , "di validasi : " + gender);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "You must fill all before this",
                    Toast.LENGTH_SHORT).show();
        }



    }

    public void nextCounter(View view){
        name = etName.getText().toString();
        address = etAddress.getText().toString();
        telp = etNoTelp.getText().toString();

        if (gender != null && bornday != null && name != null && address != null && telp != null && city_id != null && city != null && province != null){
            Intent i = new Intent(getApplicationContext(), CounterActivity.class);
            i.putExtra("city", city);
            i.putExtra("city_id", city_id);
            i.putExtra("province", province);
            i.putExtra("name", name);
            i.putExtra("address", address);
            i.putExtra("telp", telp);
            i.putExtra("gender", gender);
            i.putExtra("bornday", bornday);
            Log.d(TAG , "di validasi : " + gender);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(), "You must fill all before this",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
