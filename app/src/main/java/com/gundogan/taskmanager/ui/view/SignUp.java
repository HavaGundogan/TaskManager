package com.gundogan.taskmanager.ui.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gundogan.taskmanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class SignUp extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        EditText dobEditText = findViewById(R.id.birthDateEditText);
        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickBirthDate();
            }

        });


        Button signupButton = findViewById(R.id.buttonSignup);
        signupButton.setOnClickListener(v -> {
            EditText nameEditText = findViewById(R.id.textNameSignup);
            EditText emailEditText = findViewById(R.id.textEmailSignup);
            EditText passwordEditText = findViewById(R.id.textPasswordSignup);
            EditText EditText = findViewById(R.id.birthDateEditText);

            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String dob = EditText.getText().toString();

            signup(name, email, password, dob);
        });


    }

    private void pickBirthDate() {
        // Şimdiki tarihi alın
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        // DatePickerDialog oluşturun
        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Seçilen tarihi alın
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, monthOfYear, dayOfMonth);

                // Seçilen tarihi EditText'e yazdırın
                EditText birthDateEditText = findViewById(R.id.birthDateEditText);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                birthDateEditText.setText(dateFormat.format(selectedDate.getTime()));
              /* // Veritabanına tarihi ekle
               Date birthDate = selectedDate.getTime();
               addToDatabase(birthDate);*/
            }

        }, year, month, day);

        // DatePickerDialog'un maksimum tarihini ayarlayın (şimdiki tarih)
        datePicker.getDatePicker().setMaxDate(currentDate.getTimeInMillis());

        // DatePickerDialog'u gösterin
        datePicker.show();
    }

    private void signup(String name, String email, String password, String dob) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();

                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("name", name);
                        userMap.put("email", email);
                        userMap.put("dob", dob);


                        // Add a new document with a generated ID
                        db.collection("users")
                                .add(userMap)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(SignUp.this, "Kayıt işlemi başarılı oldu", Toast.LENGTH_SHORT).show();
                                        Intent MainActivity = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(MainActivity);

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignUp.this, "Kayıt işlemi başarısız oldu", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            /*String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                            db.collection("users").document(userId)
                                    .set(userMap)
                                    .addOnSuccessListener(aVoid -> {
                                        // Kullanıcı başarıyla kaydedildi
                                        // Burada kullanıcıyı uygulamanızın ana ekranına yönlendirebilirsiniz
                                    })
                                    .addOnFailureListener(e -> {
                                        // Firestore'a kayıt başarısız oldu
                                        Toast.makeText(SignUp.this, "Kayıt işlemi başarısız oldu", Toast.LENGTH_SHORT).show();
                                    });*/

                    } else {
                        // Kayıt başarısız oldu
                        Toast.makeText(SignUp.this, "Kayıt işlemi başarısız oldu", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
