package geoalert.com.co.geoalert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import geoalert.com.co.geoalert.controller.CreateAlertController;

public class createAlertActivity extends AppCompatActivity {

    Button send = (Button)findViewById(R.id.Send);
    Button camera = (Button)findViewById(R.id.Camera);
    EditText editTextTitle = (EditText) findViewById(R.id.alertTitle);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title =  editTextTitle.getText().toString();
            }
        });
    }
}
