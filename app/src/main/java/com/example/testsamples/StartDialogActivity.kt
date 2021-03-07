package com.example.testsamples

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class StartDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(this)
        val dialogTitle = "Dialog w/o Activity UI"
        val dialogMessage = "起動しますか？"
        builder.setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton("Yes") { _, _ ->
                Log.d("PositiveButton", "Clicked")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                toastMake("end DialogActivity")
                finish()
            }
            .setNegativeButton("No") { _, _ ->
                Log.d("NegativeButton", "Clicked")
                toastMake("exit app")
                finish()
            }

        builder.setOnCancelListener {
            toastMake("dialog cancelled")
            finish()
        }

        builder.show()
    }

    fun toastMake(str :String){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
}