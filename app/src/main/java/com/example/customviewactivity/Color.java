package com.example.customviewactivity;

import androidx.core.content.ContextCompat;

public enum Color {
    BLACK(R.color.black),GRAY(R.color.gray),BLUE(R.color.blue),
    GREEN(R.color.green),RED(R.color.red),ORANGE(R.color.orange);
    private int color;
    Color(int color){
        this.color = color;
    }
    public int getColor() {
        return color;
    }
}
