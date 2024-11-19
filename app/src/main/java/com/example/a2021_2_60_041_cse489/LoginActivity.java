package com.example.a2021_2_60_041_cse489;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;

    private CheckBox cbRememberUser, cbRememberLogin;

    private Button btnDosentHaveAccount, btnLogin;

    private SharedPreferences sp;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_activity);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        cbRememberUser = findViewById(R.id.etRemUser);
        cbRememberLogin = findViewById(R.id.etRemLogin);

        btnDosentHaveAccount = findViewById(R.id.btnnoAccount);
        btnLogin = findViewById(R.id.btnlogin);

        sp= this.getSharedPreferences("my_sp", MODE_PRIVATE);
        email = sp.getString("USER-EMAIL", "NOT-YET-CREATED");
        password =sp.getString("PASSWORD","INCORRECT PASSWORD");
        boolean remUser =sp.getBoolean("REMEMBER-USER", false);
        boolean remlogin =sp.getBoolean("REMEMBER-LOGIN", false);
        if(remUser){
            etEmail.setText(email);
            cbRememberUser.setChecked(true);
        }
        if(remlogin){
            etPassword.setText(password);
            cbRememberUser.setChecked(true);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                System.out.println(email);
                System.out.println(pass);
                if(!email.equals(email)){
                    Toast.makeText(LoginActivity.this, "Email didnt match", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!pass.equals(pass)){
                    Toast.makeText(LoginActivity.this, "password didnt match", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(LoginActivity.this, ReportActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        btnDosentHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
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