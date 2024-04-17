package com.example.customviewactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class PreferecncesActivity extends AppCompatActivity {
    private EditText edittextGrade;
    private EditText editTextTextSize;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private Button buttonSave;
    private Button buttonExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);

        edittextGrade = findViewById(R.id.editTextGrade);
        editTextTextSize = findViewById(R.id.editTextTextSize);

        spinner = findViewById(R.id.spinnerTextColor);
        String[] colors = new String[]{"Black","Gray","Blue","Green","Red","Orange"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_item,colors
        );
        spinner.setAdapter(myAdapter);

        radioGroup = findViewById(R.id.radioGroupBarColor);

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> {
            int grade=-1;
            int textSize=-1;
            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            String barColorString = radioButton.getText().toString();
            String textColorString = spinner.getSelectedItem().toString();

            if (!edittextGrade.getText().toString().equals(""))
                grade = Integer.parseInt(edittextGrade.getText().toString());
            if (!editTextTextSize.getText().toString().equals(""))
                textSize = Integer.parseInt(editTextTextSize.getText().toString());

            if (grade<0 || grade>100 || textSize<10 || textSize>140){
                Toast.makeText(this,"Enter valid values",Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("grade", grade);
                intent.putExtra("textSize",textSize);
                intent.putExtra("barColorString",barColorString);
                intent.putExtra("textColorString",textColorString);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED ,intent);
            finish();
        });



        

    }
}
