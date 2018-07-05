package com.example.suresh.demoapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    TextView tvForgotYourPassword;
    TextView tvNoOfAttemptsRemaining;
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(validate(email,password) ){
                    if(email=="spangeni44@gmail.com" && password=="spsp1234") {
                        Intent intent = new Intent(MainActivity.this, Main1Activity.class);
                        startActivity(intent);
                    }
                }
                //    System.out.println("Username:" + email + "Password:" + password);
                //    Toast.makeText(MainActivity.this, "Username"+email+" Password"+password, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public boolean validate(String email, String password) {
    boolean temp=false;
        if (email.isEmpty()) {
            showAlertDialog("Please enter a email addresss");
        } else if (password.isEmpty()) {
            showAlertDialog("Please enter a password");
        } else if (email.isEmpty() && password.isEmpty()) {
            showAlertDialog("Please enter a email address and password");
        }
        else if(password.length()<8){
            showAlertDialog("Password must be 8 character long.");
        }
        else if(password.equals(password.toLowerCase())){
            showAlertDialog("Password must include one capital letter");
        }
        else{
                temp=true;

        }
        return temp;
    }
     /*
       else if (email == "spangeni44@gmail.com" && password == "spsp1234") {

            Intent intent = new Intent(MainActivity.this, Main1Activity.class);
            startActivity(intent);
        }

      else{
            counter--;
            tvNoOfAttemptsRemaining.setText("Number of attempts remaining:"+String.valueOf(counter));
            if(counter==0){
                btnLogin.setEnabled(false);
            }
*/



}
