package com.example.testsamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class TestFieldActivity extends AppCompatActivity {
    private static int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_field);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("now number is "+number+"\n");
            stringBuilder.append("number++ is "+(number++)+"\n");
            stringBuilder.append("then number is "+number+"\n");
        });
    }
}