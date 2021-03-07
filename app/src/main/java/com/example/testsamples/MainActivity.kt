package com.example.testsamples

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tv_countUp).setOnClickListener {
            startActivity(Intent(this,CountUpActivity::class.java))
        }

        findViewById<View>(R.id.tv_testField).setOnClickListener {
            startActivity(Intent(this, TestFieldActivity::class.java))
        }
    }
}