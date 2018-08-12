package geoalert.com.co.geoalert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import geoalert.com.co.geoalert.controller.CreateAlertController;
import geoalert.com.co.geoalert.model.Warning;

public class createAlertActivity extends AppCompatActivity {

    Button send = (Button)findViewById(R.id.Send);
    Button camera = (Button)findViewById(R.id.Camera);
    EditText editTextTitle = (EditText) findViewById(R.id.alertTitle);
    EditText editTextDescription = (EditText) findViewById(R.id.alertDescription);

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    DatabaseReference alertRef = ref.child("alerts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Warning alert = new Warning( );
                alert.setName( editTextTitle.getText().toString());
                alert.setDescription(editTextDescription.getText().toString());
                alertRef.setValue(alert);
            }
        });
    }
}
