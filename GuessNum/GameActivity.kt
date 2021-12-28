package com.example.guessnum

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class GameActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private var intervalBegin: Int = 0
    private var intervalEnd: Int = 0
    lateinit var buttonYes: Button
    lateinit var buttonNo: Button
    var bitSearch: Int = 0
    lateinit var mainMenuButton: Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        buttonYes = findViewById(R.id.yes_btn)
        buttonNo = findViewById(R.id.no_btn)
        questionTextView = findViewById(R.id.question)
        mainMenuButton = findViewById(R.id.go_to_main_menu)
        intervalBegin = intent.getIntExtra("start", 0)
        intervalEnd = intent.getIntExtra("end", 0)

        if (intervalBegin == intervalEnd) {
            Toast.makeText(this, "Seems like you haven't entered any numbers!", Toast.LENGTH_LONG).show()
            endGame()
        } else if (intervalEnd - intervalBegin <= 2) {
            endGame()
            questionTextView.text = "Your number is ${intervalEnd - 1}"
        } else {
            bitSearch = intervalEnd - (intervalEnd - intervalBegin) / 2
            setQuestion()
        }
    }
    private fun endGame() {
        buttonYes.visibility = View.GONE
        buttonNo.visibility = View.GONE
        mainMenuButton.visibility = View.VISIBLE
    }

    private fun setQuestion() {
        val newText = getString(R.string.question_gr) + " " + bitSearch.toString() + "?"
        questionTextView.text = newText
    }
    fun onConfirmationClick(view: View) {
        if (view.id == R.id.yes_btn) {
            intervalBegin = bitSearch
            bitSearch = intervalEnd - (intervalEnd - intervalBegin) / 2
            setQuestion()
        } else {
            intervalEnd = bitSearch
            bitSearch = intervalEnd - (intervalEnd - intervalBegin) / 2
            setQuestion()
        }
        Log.d("difference", (intervalEnd - intervalBegin).toString())
        if (intervalEnd - intervalBegin <= 2) {
            endGame()
            questionTextView.text = "Your number is ${intervalEnd - 1}"
        }
    }

    fun goToMainMenu(view: View) {
        this.finish()
        return
    }


}