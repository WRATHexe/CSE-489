package com.example.a2021_2_60_041_cse489;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {

    private EditText etUserName, etEmail, etPhone, etPassword, etCPassword;

    private CheckBox cbRememberUser, cbRememberLogin;

    private Button btnHaveAccount, btnSignup;

    private SharedPreferences sp;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp= this.getSharedPreferences("my_sp", MODE_PRIVATE);
        String email = sp.getString("USER-EMAIL", "NOT-YET-CREATED");
        if(!email.equals("NOT-YET-CREATED")){
            System.out.println("moving from Signup");

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finishAffinity();
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.signup_activity);

        etUserName = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etConfirmPassword);

        cbRememberUser = findViewById(R.id.etRemUser);
        cbRememberLogin = findViewById(R.id.etRemLogin);

        btnHaveAccount = findViewById(R.id.btnHaveAccount);
        btnSignup = findViewById(R.id.btnSignUP);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                String cPass = etCPassword.getText().toString().trim();

                System.out.println(userName);
                System.out.println(email);
                System.out.println(phone);
                System.out.println(pass);
                System.out.println(cPass);

                if(userName.length()>8){
                    Toast.makeText(SignUpActivity.this,"username should be 4-8 letters",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(SignUpActivity.this,"Use a valid email",Toast.LENGTH_LONG).show();
                    return;

                }
                if(phone.length()>11){
                    Toast.makeText(SignUpActivity.this,"phone should be 11 numbers",Toast.LENGTH_LONG).show();
                    return;
                }
                if(pass.length()<4){
                    Toast.makeText(SignUpActivity.this,"Password must have 4 digit",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!pass.equals(cPass)){
                    Toast.makeText(SignUpActivity.this, "Password Didn't Match", Toast.LENGTH_LONG).show();
                    return;
                }




                SharedPreferences.Editor e = sp.edit();
                e.putString("USER_EMAIL", email);
                e.putString("USER-NAME", userName);
                e.putString("USER-PHONE",phone);
                e.putString("USER-PASSWORD", pass);
                e.putBoolean("REMEMBER-USER", cbRememberUser.isChecked());
                e.putBoolean("REMEMBER-LOGIN", cbRememberLogin.isChecked());
                e.apply();

                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);



            }

        });

        btnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}