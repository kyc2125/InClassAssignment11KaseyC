package com.example.kaseyclark.inclassassignment11kaseyc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import com.facebook.login.LoginResult;
import com.facebook.FacebookCallback;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android. gms.tasks. Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookException;


public class Main2Activity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private String email, password;


    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText=(EditText)findViewById(R.id.et_Username);
        passwordEditText=(EditText)findViewById(R.id.et_Password);
        mAuth=FirebaseAuth.getInstance();
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               Toast.makeText(Main2Activity.this,  "success", Toast.LENGTH_SHORT).show();
              //  handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                //  Log.d(TAG, "facebook:onCancel");
            }


            @Override
            public void onError(FacebookException error) {
                //Log.d(TAG, "facebook:onError", error);
          Toast.makeText(Main2Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

// ...
    }
    public void login(View view){
        email = emailEditText.getText().toString();
        password=passwordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)

                .addOnCompleteListener (this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult>task ){
                        if (!task.isSuccessful()) {
                            Toast.makeText(Main2Activity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Main2Activity.this,task.getResult().getUser().getEmail()+"logged in succesfully",Toast.LENGTH_SHORT).show();
                        }

                    }

                });

    }

}
