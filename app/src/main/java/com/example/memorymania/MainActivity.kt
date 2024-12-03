package com.example.memorymania

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// MainActivity är den första aktiviteten i appen, där användaren kan navigera till olika sidor.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aktiverar kant-till-kant visning för att få en mer visuell effekt där appens innehåll går ända ut till skärmen kanter.
        enableEdgeToEdge()

        // Sätter layouten för aktiviteten från XML-filen activity_main.
        setContentView(R.layout.activity_main)

        // Hitta knapparna i layouten baserat på deras id:n.
        val playGameButton: Button = findViewById(R.id.playGameButton)
        val scoreBoardButton: Button = findViewById(R.id.scoreBoardButton)
        val instructionsButton: Button = findViewById(R.id.gameInstructionsButton)
        val exitGameButton: Button = findViewById(R.id.exitGameButton)

        // Sätter en click listener för PlayGame-knappen som startar PlayGameActivity när den klickas.
        playGameButton.setOnClickListener {
            // Loggar en rad för att hjälpa till vid felsökning.
            Log.d("MainActivity", "Play Game Button clicked")
            // Skapar en intent för att starta PlayGameActivity.
            val intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent) // Startar PlayGameActivity.
        }

        // Sätter en click listener för ScoreBoard-knappen som startar ScoreBoardActivity när den klickas.
        scoreBoardButton.setOnClickListener {
            // Loggar att ScoreBoard-knappen har klickats.
            Log.d("MainActivity", "ScoreBoard Button clicked")
            // Startar ScoreBoardActivity.
            startActivity(Intent(this, ScoreBoardActivity::class.java))
        }

        // Sätter en click listener för Instructions-knappen som startar InstructionsActivity när den klickas.
        instructionsButton.setOnClickListener {
            // Loggar att Instructions-knappen har klickats.
            Log.d("MainActivity", "Instructions Button clicked")
            // Skapar en intent för att starta InstructionsActivity.
            val intent = Intent(this, InstructionsActivity::class.java)
            startActivity(intent) // Startar InstructionsActivity.
        }

        // Sätter en click listener för Exit Game-knappen som avslutar appen när den klickas.
        exitGameButton.setOnClickListener {
            // Loggar att Exit Game-knappen har klickats.
            Log.d("MainActivity", "Exit Game Button clicked")
            // Stänger aktiviteten och avslutar appen.
            finish() // Avslutar appen
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}