package com.bank.bankdetailsgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText ifsc;
    private TextView bankdetails;
    String ifscCode;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ifsc=findViewById(R.id.editifsc);
        bankdetails=findViewById(R.id.textViewDetails);
        Button getdetails=findViewById(R.id.button);

        mRequestQueue = Volley.newRequestQueue(MainActivity.this);
        getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifscCode=ifsc.getText().toString();

                if(TextUtils.isEmpty(ifscCode)){
                    Toast.makeText(MainActivity.this,"Enter Valid IFSC Code",Toast.LENGTH_SHORT).show();
                }
                else{
                    GetData(ifscCode);
                }
            }
        });
    }
    private void GetData(String ifscCode){
        mRequestQueue.getCache().clear();

        String url= "https://bank-apis.justinclicks.com/API/V1/IFSC/" + ifscCode;

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                       try{


                               // if the status is successful we are

                               // extracting data from JSON file

                              // JSONObject dataObj = response.getJSONObject("data");

                               String state = response.getString("STATE");

                               String bankName =response.getString("BANK");

                               String branch = response.getString("BRANCH");

                               String address = response.getString("ADDRESS");

                               String contact = response.getString("CONTACT");

                               String micrcode =response.getString("MICR");

                               String city = response.getString("CITY");

                               bankdetails.setText("Bank Name : " + bankName + "\nBranch : " + branch + "\nAddress : " + address + "\nMICR Code : " + micrcode + "\nCity : " + city + "\nState : " + state + "\nContact : " + contact);



                       } catch (JSONException e) {

                           // if we get any error while loading data

                           // we are setting our text as invalid IFSC code.

                           e.printStackTrace();

                           bankdetails.setText("Invalid IFSC Code");

                       }

                    }

                }, new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        // if we get any error while loading json

                        // data we are setting our text to invalid IFSC code.

                        bankdetails.setText("Invalid IFSC Code");

                    }

                });

        // below line is use for adding object

        // request to our request queue.

        queue.add(jsonObjectRequest);

    }
}

