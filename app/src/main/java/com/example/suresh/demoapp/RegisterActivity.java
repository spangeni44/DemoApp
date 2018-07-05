package com.example.suresh.demoapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etPassword;
    Button btnRegister;
    Spinner spinnerChooseSalary;
    TextView tvTermsAndConditions;
    ImageButton btnShowHidePassword;
    ProgressBar pbRegister;
    Context context;
    RegisterModel registerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        etFirstName = findViewById(R.id.et_firstname);
        etLastName = findViewById(R.id.et_lastname);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        spinnerChooseSalary = findViewById(R.id.spinner_state);
        pbRegister = findViewById(R.id.pb_state);
        btnShowHidePassword=findViewById(R.id.btn_show_hide_register_password);
        btnShowHidePassword.setImageResource(R.drawable.ic_eye);

        context = getApplicationContext();
        registerModel=new RegisterModel();

        final String firstName=etFirstName.getText().toString().trim();
        final String lastName=etLastName.getText().toString().trim();
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();

        registerModel.setFirstName(firstName);
        registerModel.setLastName(lastName);
        registerModel.setEmail(email);
        registerModel.setPassword(password);



//        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString("name ", "Suresh");
//        editor.apply();
//
//        String name = prefs.getString("name", "");

//        Toast.makeText(RegisterActivity.this, "name" + name, Toast.LENGTH_SHORT).show();

        btnShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnShowHidePassword.setImageResource(R.drawable.ic_eye_slash);
                } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnShowHidePassword.setImageResource(R.drawable.ic_eye);
                }
            }
        });

        ArrayList<String> salaryTypes = new ArrayList<>(Arrays.asList("All salaries", "Annual", "Hourly"));
        final SpinnerAdapter salaryAdapter = new SpinnerAdapter(context, R.layout.item_spinner, salaryTypes);
        salaryAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerChooseSalary.setAdapter(salaryAdapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(validate(firstName,lastName, email,password) ){

                        Intent intent = new Intent(RegisterActivity.this, Main1Activity.class);
                        startActivity(intent);

                }
                //    System.out.println("Username:" + email + "Password:" + password);
                //    Toast.makeText(MainActivity.this, "Username"+email+" Password"+password, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
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

    public boolean validate(String firstName, String lastName, String email, String password) {
        boolean temp = false;

        if (firstName.isEmpty()) {
            showAlertDialog("Please enter a first name.");
        } else if (lastName.isEmpty()) {
            showAlertDialog("Please enter a last name.");
        } else if (email.isEmpty()) {
            showAlertDialog("Please enter a email addresss");
        } else if (password.isEmpty()) {
            showAlertDialog("Please enter a password");
        } else if (email.isEmpty() && password.isEmpty()) {
            showAlertDialog("Please enter a email address and password");
        } else if (password.length() < 8) {
            showAlertDialog("Password must be 8 character long.");
        } else if (password.equals(password.toLowerCase())) {
            showAlertDialog("Password must include one capital letter");
        } else {
            temp = true;

        }
        return temp;
    }

}
