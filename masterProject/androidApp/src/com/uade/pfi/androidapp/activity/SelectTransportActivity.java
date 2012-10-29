package com.uade.pfi.androidapp.activity;

import com.uadepfi.android.R;
import com.uadepfi.android.R.layout;
import com.uadepfi.android.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SelectTransportActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_transport);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_transport, menu);
        return true;
    }
}
