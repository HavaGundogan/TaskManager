package com.gundogan.taskmanager.ui.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gundogan.taskmanager.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        emailEditText = findViewById(R.id.textEmailLogin);
        passwordEditText = findViewById(R.id.textPasswordLogin);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        //burada bir hata var kontrol et!
        if (user != null) {

            Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }


    }


    public void login(View view) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Kullanıcı giriş yaptı, yapılacak işlemler

                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Giriş başarısız, hata mesajı göster
                            Toast.makeText(LoginActivity.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
   public void SignupOnc(View view){
        Intent intent = new Intent(LoginActivity.this, SignUp.class);
        startActivity(intent);
        finish();
    }
    public void forgetPasswordOnc(View view){
        Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
        finish();
    }
}
