package com.gundogan.taskmanager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText mEmailField;
    private Button mResetButton;

    private FirebaseAuth mAuth;

    private static final String TAG = "forgotPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_layout);

        mEmailField = findViewById(R.id.textEmailReset);
        mResetButton = findViewById(R.id.buttonReset);

        mAuth = FirebaseAuth.getInstance();

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailField.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmailField.setError("Email alanı boş bırakılamaz.");
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Şifre sıfırlama emaili gönderildi.");
                                    Toast.makeText(ForgotPassword.this, "Şifre sıfırlama emaili gönderildi.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.w(TAG, "Şifre sıfırlama emaili gönderilemedi.", task.getException());
                                    Toast.makeText(ForgotPassword.this, "Şifre sıfırlama emaili gönderilemedi.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
