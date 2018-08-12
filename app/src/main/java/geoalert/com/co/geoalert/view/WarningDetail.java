package geoalert.com.co.geoalert.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import geoalert.com.co.geoalert.R;

public class WarningDetail extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_detail);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //mDatabase.
    }
}
