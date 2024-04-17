package com.example.customviewactivity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.Serializable;

public class CustomGradeView extends ConstraintLayout{
    private ProgressBar progressBar;
    private TextView textView;
    private int barColor;

    public CustomGradeView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomGradeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomGradeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomGradeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        if(widthMeasureSpec!=0 || heightMeasureSpec!=0){
            int size = Math.max(widthMeasureSpec,heightMeasureSpec);
            setMeasuredDimension(size,size);
        }
    }
    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_grade_view,this,true);

        progressBar = view.findViewById(R.id.puntuacionGraf);
        textView = view.findViewById(R.id.puntuacionNum);
    }
    public void init(AttributeSet attrs){
        init();

        if (attrs!=null){
            int grade = getContext().obtainStyledAttributes(attrs,R.styleable.CustomGradeView)
                    .getInt(R.styleable.CustomGradeView_grade,100);

            float textSize = getContext().obtainStyledAttributes(attrs,R.styleable.CustomGradeView)
                    .getDimensionPixelSize(R.styleable.CustomGradeView_textSize,0);

            int barColor = getContext().obtainStyledAttributes(attrs,R.styleable.CustomGradeView)
                    .getColor(R.styleable.CustomGradeView_barColor,getContext().getTheme()
                            .getResources().getColor(R.color.green,getContext().getTheme()));

            int textColor = getContext().obtainStyledAttributes(attrs,R.styleable.CustomGradeView)
                    .getColor(R.styleable.CustomGradeView_textColor,getContext().getTheme()
                            .getResources().getColor(R.color.black,getContext().getTheme()));


            setGrade(grade);
            setBarColor(barColor);

            textView.setTextColor(textColor);
            if (textSize>0)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        }
    }
    public void setGrade(int grade){
        if (grade<0)
            grade=0;
        if (grade>100)
            grade=100;
        progressBar.setProgress(grade);
        textView.setText(String.valueOf(grade));
    }
    public int getGrade(){
        return Integer.parseInt(textView.getText().toString());
    }
    public void setBarColor(int barColor){
        progressBar.getProgressDrawable().setColorFilter(barColor, PorterDuff.Mode.SRC_IN);
        this.barColor = barColor;
    }
    public int getBarColor(){return barColor;}
    public void setTextColor(int textColor){textView.setTextColor(textColor);}
    public int getTextColor(){return textView.getCurrentTextColor();}
    public void setTextSize(int textSize){
        if (textSize>0)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
    }
    public int getTextSize(){return (int)textView.getTextSize();}
}
