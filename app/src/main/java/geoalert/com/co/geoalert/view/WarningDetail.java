package geoalert.com.co.geoalert.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import geoalert.com.co.geoalert.R;
import geoalert.com.co.geoalert.model.Warning;

public class WarningDetail extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_detail);

        mDatabase = FirebaseDatabase.getInstance().getReference("alerts");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Warning warning = dataSnapshot.getValue(Warning.class);
                System.out.println("snapshot: " + warning.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("snapshot: " + databaseError.toException());
            }
        });
    }
}
