package com.example.testsamples

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.testsamples.darts.CountUpActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tv_countUp).setOnClickListener {
            startActivity(Intent(this,
                CountUpActivity::class.java))
        }

        findViewById<View>(R.id.tv_testField).setOnClickListener {
            startActivity(Intent(this, TestFieldActivity::class.java))
        }

        findViewById<View>(R.id.preferenceTest).setOnClickListener {
            startActivity(Intent(this, PreferenceTestActivity::class.java))
        }

        findViewById<View>(R.id.tv_GitHub).setOnClickListener {
            val page = Uri.parse("https://github.com/oha-yashi/TestSamples")
            val intent = Intent(Intent.ACTION_VIEW, page)
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }

        findViewById<View>(R.id.mapActivity).setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }
}