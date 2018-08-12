package geoalert.com.co.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import geoalert.com.co.geoalert.model.User;
import geoalert.com.co.geoalert.view.MapActivity;

public class login extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener  listener;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("User");

    private TextInputEditText tietUsuario;
    private TextInputLayout tielUsuario;
    private TextInputEditText tietPass;
    private TextInputLayout tielPass;
    private Button btnLogin;
    private User user;
    Map<String,Object> useData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        initViews();
        initLinsteners();
    }

    private void initLinsteners() {
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               procesarLogin(v);
           }
       });
    }

    private void initViews() {
        tietUsuario = findViewById(R.id.tietUsuario);
        tielUsuario = findViewById(R.id.tielUsuario);
        tietPass = findViewById(R.id.tietPass);
        tielPass = findViewById(R.id.tielPass);
        btnLogin = findViewById(R.id.loginPress);
    }

    public void udpateUsers() {
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    useData.put("name",user.getDisplayName());
                    useData.put("name",user.getDisplayName());
                    useData.put("id",user.getUid());
                    useData.put("email",user.getEmail());
                    useData.put("photoUrl",user.getEmail());
                    useData.put("providerId",user.getEmail());
                    ref.child(user.getUid()).updateChildren(useData);
                    startActivity(new Intent(login.this, MapActivity.class));
                } else {
                    // User is signed out
                    Log.d("~~ :", "onAuthStateChanged:signed_out");
                }
            }
        };
    }

    private boolean validateForm() {
            tielUsuario.setErrorEnabled(true);
            tielPass.setErrorEnabled(true);

            if (tietUsuario.getText().toString().length() == 0) {
                tielUsuario.setError("Falta");
                return false;
            }

            if (tietPass.getText().toString().length() == 0) {
                tielPass.setError("Falta");
                return false;
            }
//        if (tietUsuario.getText().equals(""))
//
//        if (TextUtils.isEmpty(email)) {
//            tielUsuario.setError("Faltan ingresar el correo Electronico");
//           return false;
//        } else  {
//            tietPass.setError(null);
//        }
//
//        if (TextUtils.isEmpty(pass)) {
//            tietPass.setError("Faltan ingresar la contraseña");
//            return false;
//        } else  {
//            tietPass.setError(null);
//        }
//        return false;
        return  true;
    }


    public void procesarLogin(final View view) {
        if (validateForm()) {
            user = new User();
            user.setEmail(tietUsuario.getText().toString().trim());
            user.setPass(tietPass.getText().toString().trim());

            showProgressDialog("Estamos Validando los datos");
            auth.signInWithEmailAndPassword(user.getEmail(),user.getPass())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                Log.d("----TMM ","Succes: " + user);
                                udpateUsers();
                                startActivity(new Intent(login.this, MapActivity.class));
                            } else {
                                Log.w("---//Error: ", "signInWithEmail:failure", task.getException());
                                Snackbar snackbar = Snackbar.make(view,"No Se pudo iniciar session",Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }

                            if (!task.isSuccessful()) {
                                Snackbar snackbar = Snackbar.make(view,"Correo o Contraseña incorrectos",Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                            hideProgressDialog();
                        }
                    });
        }

    }

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(message);
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
