package com.example.memorymania

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Hitta knappen för att spela spelet
        val playGameButton = findViewById<Button>(R.id.playGameButton)
        val scoreBoardButton: Button = findViewById(R.id.scoreBoardButton)

        // Sätter en click listener för att starta PlayGameActivity när knappen trycks och skapar en Intent för att starta PlayGameActivity.
        playGameButton.setOnClickListener {
            val intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent)
        }

        // Sätter en click listener för att starta ScoreBoardActivity när knappen trycks och skapar en Intent för att starta ScoreBoardActivity.
        scoreBoardButton.setOnClickListener {
            startActivity(Intent(this, ScoreBoardActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}