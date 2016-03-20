package com.example.ramakant.virualatm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import Utils.DataHub;
import networking.SharedPreference;
import networking.UserAuthentication;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText _emailText;
    EditText _passwordText;
    Context mContext;
    AppCompatButton _loginButton;
    SharedPreference sharedPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (AppCompatButton) findViewById(R.id.btn_login);

        checkForSharedPreferenceLogin();  //Each Token is Valid For 60 days so we can skip the Authentication API

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
             /*  Intent intent = new Intent(LoginActivity.this, NavigationDrawer.class);
                startActivity(intent);
                finish();*/
            }
        });

    }

    /**
     * Login Directly using shared preference token
     */
    private void checkForSharedPreferenceLogin() {
        sharedPreference = SharedPreference.getInstance(mContext);
        String token = sharedPreference.getFromSharedPreference(DataHub.AUTHENTICATION_TOKEN, "invalid_value");
        if (token.equals("invalid_value")) {
            //login();
            Toast.makeText(this, "Please Login", Toast.LENGTH_LONG).show();
        } else {
            onLoginSuccess();
        }
    }

    public void login() {
        Log.d(TAG, "Login");

/*        if (!validate()) {
            onLoginFailed();
            return;
        }*/

        //_loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        UserAuthentication userAuthentication = new UserAuthentication(mContext, new UserAuthentication.TokenReceived() {
                            @Override
                            public void sendToken(String token) {
                                if (token.equals("")) {
                                    progressDialog.dismiss();
                                    onLoginFailed();
                                } else if (token.equals("error")) {
                                    progressDialog.dismiss();
                                    Toast.makeText(mContext, "Please Try After Sometime", Toast.LENGTH_LONG).show();
                                } else {
                                    sharedPreference = SharedPreference.getInstance(mContext);
                                    sharedPreference.putInSharedPreference(DataHub.AUTHENTICATION_TOKEN, token);
                                    Toast.makeText(mContext, "token " + token, Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                    onLoginSuccess();
                                }
                            }

                        });
                        //String url = "http://corporate_bank.mybluemix.net/corporate_banking/mybank/authenticate_client?client_id=" + email + "&password=" + password;
                        String url = DataHub.AUTHENTICATION_URL + DataHub.QUESTION_MARK_OPERATOR + DataHub.CLIENT_ID + DataHub.EQUAL_OPERATOR + email + DataHub.AND_OPERATOR + DataHub.PASSWORD + DataHub.EQUAL_OPERATOR + password;
                        try {
                            URL url1 = new URL(url);
                            userAuthentication.userAuthentication(url1.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }, 3000);
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

}
