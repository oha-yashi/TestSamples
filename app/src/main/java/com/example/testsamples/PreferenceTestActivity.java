package com.example.testsamples;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PreferenceTestActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    //keys
    String keyString = "keyString";
    String keyInt = "keyInt";
    String keySwitch = "keySwitch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_test);

        // https://qiita.com/mackiso/items/7b0b0060b0489b62b4cb
        sharedPreferences = getPreferences(MODE_PRIVATE); //このアクティビティに紐づいたpreference
        /*
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if(s.equals(keySwitch))toggleSwitch();
            }
        });
         */

        findViewById(R.id.button1).setOnClickListener(view -> textEdit());
        ((EditText) findViewById(R.id.edit1))
                .setHint(sharedPreferences.getString(keyString, "no String"));

        findViewById(R.id.button2).setOnClickListener(view -> intEdit());
        ((EditText) findViewById(R.id.editInt))
                .setHint( Integer.toString(sharedPreferences.getInt(keyInt, 1234567890)));

        findViewById(R.id.switch1).setOnClickListener(view -> toggleSwitch());
        ((TextView) findViewById(R.id.textView3))
                .setText(String.valueOf(sharedPreferences.getBoolean(keySwitch, false)));
        ((Switch) findViewById(R.id.switch1))
                .setChecked(sharedPreferences.getBoolean(keySwitch, false));
    }

    private void textEdit(){
        TextView textView = findViewById(R.id.textView1);
        EditText editText = findViewById(R.id.edit1);
        String getText = editText.getText().toString();
        textView.setText(getText);
        sharedPreferences.edit().putString(keyString, getText).apply();
    }

    private void intEdit(){
        TextView textView = findViewById(R.id.textView2);
        EditText editText = findViewById(R.id.editInt);
        int getInt = Integer.parseInt(editText.getText().toString());
        textView.setText(Integer.toString(getInt));
        sharedPreferences.edit().putInt(keyInt, getInt).apply();
    }

    private void toggleSwitch(){
        Log.d("toggleSwitch", "start");
        TextView textView = findViewById(R.id.textView3);
        Switch _switch = findViewById(R.id.switch1);
        boolean s = _switch.isChecked();
        textView.setText(String.valueOf(s));
        sharedPreferences.edit().putBoolean(keySwitch, s).apply();
    }
}