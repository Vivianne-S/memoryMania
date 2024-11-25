package com.example.memorymania

import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


class PlayGameActivity : AppCompatActivity() {

    private lateinit var resetButton: Button
    private lateinit var backToMenuButton: Button
    private lateinit var timerLabel: TextView
    private lateinit var gridLayout: GridLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game) // Sätter xml för Play Game activity när knappen trycks.

        // Refernser till UI element
        resetButton = findViewById(R.id.reset_game_button)
        backToMenuButton = findViewById(R.id.back_to_menu_button)
        timerLabel = findViewById(R.id.timer_label)
        gridLayout = findViewById(R.id.gridLayout)

        // Hanterar reset knappen
        resetButton.setOnClickListener {
            resetGame()
        }

        // Hanterar back to menu knappen
        backToMenuButton.setOnClickListener {
            finish()  // Avslutar aktivitet och går tillbaka till huvudmenyn.
        }
    }


    private fun resetGame() {

    }
}