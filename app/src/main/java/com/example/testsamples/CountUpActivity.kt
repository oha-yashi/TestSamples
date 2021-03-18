package com.example.testsamples

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CountUpActivity : AppCompatActivity() {
    private lateinit var scoreView: TextView

    private val buttonListList = listOf(
        listOf(16,17,18,19,20),
        listOf(11,12,13,14,15),
        listOf( 6, 7, 8, 9,10),
        listOf( 1, 2, 3, 4, 5)
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_up)

        val buttonField = findViewById<TableLayout>(R.id.buttonTable)
        var tv :Button
        for (l in buttonListList){
            val tr = TableRow(this)
            for (i in l){
                tv = Button(this)
                tv.gravity = Gravity.CENTER
                tv.text = " $i"
                tv.textSize = 16f
                tv.setOnTouchListener(pointButtonFlick)

                tr.addView(tv)
                var lw = tv.layoutParams as LinearLayout.LayoutParams
                lw.weight = 1f
                tv.layoutParams = lw
            }
            buttonField.addView(tr)
        }
        scoreView = findViewById<TextView>(R.id.scoreView)
        findViewById<Button>(R.id.bullButton).setOnTouchListener(bullButton)
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    private val pointButtonFlick =
        OnTouchListener { view: View, motionEvent: MotionEvent ->
            val btn = view as Button
            val point = btn.text
            val gX = motionEvent.x
            val gY = motionEvent.y
            val buttonWidth = view.getWidth()
            val buttonHeight = view.getHeight()
            val hitSingle =
                0 <= gX && gX <= buttonWidth && 0 <= gY && gY <= buttonHeight
            val hitDouble =
                0 <= gX && gX <= buttonWidth && buttonHeight < gY
            val hitTriple =
                0 <= gX && gX <= buttonWidth && gY < 0
            val outOfButton = gX < 0 || buttonWidth < gX

            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    when{
                        hitSingle -> {
                            btn.text = "S${point.substring(1)}"
                        }
                        hitDouble -> {
                            btn.text = "D${point.substring(1)}"
                        }
                        hitTriple -> {
                            btn.text = "T${point.substring(1)}"
                        }
                        outOfButton -> btn.text = " ${point.substring(1)}"
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if(!outOfButton) {
                        score(btn.text as String)
                        btn.text = " ${point.substring(1)}"
                    }
                }
            }

            false
        }

    private fun score(hit: String){
        Log.d("score", hit)
        val sc = DartsRecordHelper.score(this, hit)
        val nowScore = Integer.parseInt(scoreView.text.toString())
        Log.d("score", nowScore.toString())
        scoreView.text = (nowScore+sc).toString()
    }

    @SuppressLint("ClickableViewAccessibility")
    private val bullButton = OnTouchListener { view: View, motionEvent: MotionEvent ->
        val btn = view as Button
        val point = 50
        val gX = motionEvent.x
        val gY = motionEvent.y
        val buttonWidth = view.getWidth()
        val buttonHeight = view.getHeight()
        val hitSingle = 0 <= gX && gX <= buttonWidth && 0 <= gY && gY <= buttonHeight
        val hitDouble = 0 <= gX && gX <= buttonWidth && (0 > gY || gY > buttonHeight)
        val outOfButton = gX < 0 || buttonWidth < gX

        when (motionEvent.action){
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_MOVE -> {
                when{
                    hitSingle -> {}
                    hitDouble -> {}
                    outOfButton -> {}
                }
            }
            MotionEvent.ACTION_UP -> {}
        }

        false
    }
}