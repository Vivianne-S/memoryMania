package com.example.memorymania

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        // Knappar för att öppna olika sidor
        val playGameButton: Button = findViewById(R.id.playGameButton)
        val scoreBoardButton: Button = findViewById(R.id.scoreBoardButton)
        val instructionsButton: Button = findViewById(R.id.gameInstructionsButton)
        val exitGameButton: Button = findViewById(R.id.exitGameButton)



        // Sätter en click listener för att starta PlayGameActivity när knappen trycks och skapar en Intent för att starta PlayGameActivity.
        playGameButton.setOnClickListener {
            // Logga för felsökning
            Log.d("MainActivity", "Play Game Button clicked")
            val intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent)
        }

        // Sätter en click listener för att starta ScoreBoardActivity när knappen trycks och skapar en Intent för att starta ScoreBoardActivity.
        scoreBoardButton.setOnClickListener {
            Log.d("MainActivity", "ScoreBoard Button clicked")
            startActivity(Intent(this, ScoreBoardActivity::class.java))
        }

        instructionsButton.setOnClickListener {
            Log.d("MainActivity", "Instructions Button clicked")
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent)
        }

        exitGameButton.setOnClickListener {
            Log.d("MainActivity", "Exit Game Button clicked")
            finish() // Avslutar appen
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}