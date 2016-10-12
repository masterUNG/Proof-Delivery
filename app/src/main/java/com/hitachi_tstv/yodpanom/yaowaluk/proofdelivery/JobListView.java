package com.hitachi_tstv.yodpanom.yaowaluk.proofdelivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class JobListView extends AppCompatActivity {

    //Explicit
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list_view);

        //Bind Widget
        listView = (ListView) findViewById(R.id.livShowDate);

        //Create ListView
        final String[] dateStrings = getIntent().getStringArrayExtra("Date");
        String[] storeStrings = getIntent().getStringArrayExtra("Store");
        final String[] planIdStrings = getIntent().getStringArrayExtra("PlanId");

        DateAdapter dateAdapter = new DateAdapter(JobListView.this, dateStrings, storeStrings);
        listView.setAdapter(dateAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(JobListView.this, ServiceActivity.class);
                intent.putExtra("Login", getIntent().getStringArrayExtra("Login"));
                intent.putExtra("PlanId", planIdStrings[i]);
                intent.putExtra("Date", dateStrings[i]);
                startActivity(intent);
                finish();
            }
        });

    }   // Main Method

}   // Main Class
