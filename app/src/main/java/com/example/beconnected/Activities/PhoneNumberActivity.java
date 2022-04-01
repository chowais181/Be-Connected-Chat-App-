package com.example.beconnected.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beconnected.R;
import com.google.firebase.auth.FirebaseAuth;


public class PhoneNumberActivity extends AppCompatActivity {
    EditText phonebox;
    Button continueBtn;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(PhoneNumberActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        phonebox=findViewById(R.id.phoneBox);

        continueBtn=findViewById(R.id.continueBtn);
        getSupportActionBar().hide();

        //for showing the keyboard
        phonebox.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(phonebox, InputMethodManager.SHOW_IMPLICIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phonebox.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter number", Toast.LENGTH_SHORT).show();
                }else{
                    if((phonebox.getText().toString().trim()).length()==10){
                        Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                       intent.putExtra("phoneNumber", phonebox.getText().toString());
                       startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Please Enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

}