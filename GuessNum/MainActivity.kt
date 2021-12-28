package com.example.guessnum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.reflect.typeOf



class MainActivity : AppCompatActivity() {

    lateinit var beginInterval: EditText
    lateinit var endInterval: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beginInterval = findViewById(R.id.first_num)
        endInterval = findViewById(R.id.second_num)
    }

    fun onStartGame(view: View) {
        val beginInterval_value = beginInterval.text.toString().toInt()
        val endInterval_value = endInterval.text.toString().toInt()
        if (beginInterval_value >= endInterval_value) {
            Toast.makeText(this, "Start of the range cannot be equal or greater then the end of the interval!", Toast.LENGTH_LONG).show()
            return
        } else if (endInterval_value - beginInterval_value == 1) {
            Toast.makeText(this, "Length of interval should be greater then 1!", Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("start", beginInterval_value)
        intent.putExtra("end", endInterval_value)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        beginInterval.setText("")
        endInterval.setText("")
    }
}

