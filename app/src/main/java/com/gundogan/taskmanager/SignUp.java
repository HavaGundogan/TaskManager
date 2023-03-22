package com.gundogan.taskmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        EditText birthDateEditText = findViewById(R.id.birthDateEditText);
        birthDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickBirthDate();
            }
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


}
