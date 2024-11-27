package com.example.memorymania

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard) // Ladda layouten för scoreboard

        val sharedPref = getSharedPreferences("game_data", MODE_PRIVATE)

        // Läs in bästa tiderna från SharedPreferences och hantera om det är null eller tomt
        val times = sharedPref.getStringSet("best_times", mutableSetOf())?.map { it.toInt() }?.sorted() ?: emptyList()

        // Begränsa till de 5 bästa tiderna
        val topTimes = times.take(5)

        // Formatera listan
        val formattedTimes = topTimes.mapIndexed { index, time -> "${index + 1}. $time sekunder" }

        // Visa tiderna i TextView
        val scoreBoardTitle = findViewById<TextView>(R.id.scoreboard_title)
        scoreBoardTitle.text = if (formattedTimes.isEmpty()) {
            "No scores yet!" // Visa ett meddelande om inga tider finns
        } else {
            formattedTimes.joinToString("\n")
        }
        // Hämta referens till knappen "Back to Menu"
        val backToMenuButton: Button = findViewById(R.id.back_to_menu_button)

        // Sätt OnClickListener för att gå tillbaka till huvudmenyn
        backToMenuButton.setOnClickListener {
            // Starta MainActivity (huvudmenyn)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Avsluta den här aktiviteten så att den inte är kvar i stacken
        }
    }
}