package com.example.customviewactivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CustomGradeView customGradeView;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonPreferences;
    private int gradeAtt;
    private int textSizeAtt;
    private int barColorAtt;
    private int textColorAtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customGradeView = findViewById(R.id.customGradeView);

        if (savedInstanceState!=null){ // Si no es nulo hay que deserializar lo que ha guardado
            customGradeView.setGrade((int)savedInstanceState.getSerializable("grade"));
            customGradeView.setTextSize((int)savedInstanceState.getSerializable("textSize"));
            customGradeView.setBarColor((int)savedInstanceState.getSerializable("barColor"));
            customGradeView.setTextColor((int)savedInstanceState.getSerializable("textColor"));
        }

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(view -> customGradeView.setGrade(customGradeView.getGrade()+1));

        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(view -> customGradeView.setGrade(customGradeView.getGrade()-1));

        ActivityResultLauncher activityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode()==Activity.RESULT_OK){
                        Intent data = result.getData();
                        customGradeView.setGrade(data.getExtras().getInt("grade"));
                        customGradeView.setTextSize(data.getExtras().getInt("textSize"));

                        String barColorString = data.getExtras().getString("barColorString");
                        int barColor=ContextCompat.getColor(this,Color.valueOf(barColorString.toUpperCase()).getColor());
                        if (barColor!=0)
                            customGradeView.setBarColor(barColor);

                        String textColorString = data.getExtras().getString("textColorString");
                        int textColor=ContextCompat.getColor(this,Color.valueOf(textColorString.toUpperCase()).getColor());
                        if (textColor!=0)
                            customGradeView.setTextColor(textColor);
                    }
                }
        );

        buttonPreferences = findViewById(R.id.buttonPreferences);
        buttonPreferences.setOnClickListener(view -> {
            Intent intent = new Intent(this,PreferecncesActivity.class);
            activityResult.launch(intent);
        });

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("grade",customGradeView.getGrade());
        outState.putSerializable("textSize",customGradeView.getTextSize());
        outState.putSerializable("barColor",customGradeView.getBarColor());
        outState.putSerializable("textColor",customGradeView.getTextColor());
    } // Para que guarde los usuarios cuando pasa de vertical a horizontal
}