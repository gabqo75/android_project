package com.example.demoandroid;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputConfirmPassword;
    private TextView tvErrorName;
    private TextView tvErrorEmail;
    private TextView tvErrorPassword;
    private TextView tvErrorConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        tvErrorName = findViewById(R.id.tvErrorName);
        tvErrorEmail = findViewById(R.id.tvErrorEmail);
        tvErrorPassword = findViewById(R.id.tvErrorPassword);
        tvErrorConfirmPassword = findViewById(R.id.tvErrorConfirmPassword);
        Button btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(v -> {
            validateForm();
        });
    }

    private void validateForm() {
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirm = inputConfirmPassword.getText().toString().trim();

        boolean isValid = true;

        // Reset errors
        tvErrorName.setVisibility(View.INVISIBLE);
        tvErrorEmail.setVisibility(View.INVISIBLE);
        tvErrorPassword.setVisibility(View.INVISIBLE);
        tvErrorConfirmPassword.setVisibility(View.INVISIBLE);

        // Name validation
        if (name.length() < 3) {
            tvErrorName.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // Email validation
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tvErrorEmail.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // Password validation
        if (password.length() < 6) {
            tvErrorPassword.setVisibility(View.VISIBLE);
            isValid = false;
        }

        // Confirmation validation
        if (!confirm.equals(password) || confirm.isEmpty()) {
            tvErrorConfirmPassword.setVisibility(View.VISIBLE);
            isValid = false;
        }

        if (isValid) {
            Log.i("SignUpForm", "Success: Name=" + name + ", Email=" + email);
            Toast.makeText(this, "Inscription réalisée avec succès !", Toast.LENGTH_SHORT).show();
        }
    }
}