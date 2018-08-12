package geoalert.com.co.geoalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import geoalert.com.co.geoalert.model.Warning;
import geoalert.com.co.geoalert.view.WarningDetail;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(login.this, WarningDetail.class));
    }
}
