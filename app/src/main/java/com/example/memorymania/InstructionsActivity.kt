package com.example.memorymania


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button


// InstructionsActivity hanterar visningen av instruktioner och har en knapp för att gå tillbaka till huvudmenyn.
class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sätter layouten för aktiviteten från XML-filen activity_instructions.
        setContentView(R.layout.activity_instructions)

        // Hämta tillbaka-knappen från layouten genom att använda findViewById med rätt id.
        val backToMenuButton: Button = findViewById(R.id.back_to_menu_button)

        // Sätter en click listener för knappen.
        backToMenuButton.setOnClickListener {
            // När knappen trycks, avsluta denna aktivitet och gå tillbaka till huvudmenyn.
            finish() // Avslutar InstructionsActivity och återgår till den föregående aktiviteten (MainActivity).
        }
    }
}
