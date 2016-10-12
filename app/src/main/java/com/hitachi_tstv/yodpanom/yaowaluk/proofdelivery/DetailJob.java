package com.hitachi_tstv.yodpanom.yaowaluk.proofdelivery;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class DetailJob extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private TextView jobNoTextView, storeCodeTextView,
            storeNameTextView, arrivalTextView, intentToCallTextView;
    private ListView listView;
    private ImageView firstImageView, secondImageView, thirdImageView;
    private Button arrivalButton, takeImageButton,
            confirmButton, signatureButton;
    private MyConstant myConstant = new MyConstant();
    private String planDtl2_idString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_job);

        //Bind Widget
        bindWidget();

        //Get Value From Intent
        planDtl2_idString = getIntent().getStringExtra("planDtl2_id");

        //Load Data
        SynData synData = new SynData(DetailJob.this);
        synData.execute(myConstant.getUrlDetailWherePlanId(),
                planDtl2_idString);

        //Get Event from Click Button or Image
        firstImageView.setOnClickListener(DetailJob.this);
        secondImageView.setOnClickListener(DetailJob.this);
        thirdImageView.setOnClickListener(DetailJob.this);
        arrivalButton.setOnClickListener(DetailJob.this);
        takeImageButton.setOnClickListener(DetailJob.this);
        confirmButton.setOnClickListener(DetailJob.this);
        signatureButton.setOnClickListener(DetailJob.this);


    }   // Main Method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 0:
                if (resultCode == RESULT_OK) {
                    Log.d("12octV6", "Take Photo and Save Success");
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;

        }   // switch

    }   // onActivityResult

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageView3:
                break;
            case R.id.imageView4:
                break;
            case R.id.imageView5:
                break;
            case R.id.button4:
                break;
            case R.id.button5:  // Take Photo

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);

                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;

        }   // switch

    }   // onClick

    private class SynData extends AsyncTask<String, Void, String> {

        //Explicit
        private Context context;

        public SynData(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormEncodingBuilder()
                        .add("isAdd", "true")
                        .add("planDtl2_id", strings[1])
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(strings[0]).post(requestBody).build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();


            } catch (Exception e) {
                Log.d("12octV5", "e doIn ==> " + e.toString());
                return null;
            }

        }   // doInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("12octV5", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                //Show Text
                jobNoTextView.setText("Job No: " + jsonObject.getString("work_sheet_no"));
                storeCodeTextView.setText("Store Code: " + jsonObject.getString("store_code"));
                storeNameTextView.setText("Store Name: " + jsonObject.getString("store_nameEng"));
                arrivalTextView.setText("Arrival: " + jsonObject.getString("plan_arrivalDateTime"));
                intentToCallTextView.setText("Call: " + jsonObject.getString("store_tel"));

            } catch (Exception e) {
                Log.d("12octV5", "e onPost ==> " + e.toString());
            }

        }   // onPost

    }   // SynData


    private void bindWidget() {

        jobNoTextView = (TextView) findViewById(R.id.textView14);
        storeCodeTextView = (TextView) findViewById(R.id.textView15);
        storeNameTextView = (TextView) findViewById(R.id.textView16);
        arrivalTextView = (TextView) findViewById(R.id.textView17);
        intentToCallTextView = (TextView) findViewById(R.id.textView18);
        listView = (ListView) findViewById(R.id.livContainer);
        firstImageView = (ImageView) findViewById(R.id.imageView3);
        secondImageView = (ImageView) findViewById(R.id.imageView4);
        thirdImageView = (ImageView) findViewById(R.id.imageView5);
        arrivalButton = (Button) findViewById(R.id.button4);
        takeImageButton = (Button) findViewById(R.id.button5);
        confirmButton = (Button) findViewById(R.id.button6);
        signatureButton = (Button) findViewById(R.id.button7);

    }
}   // Main Class
