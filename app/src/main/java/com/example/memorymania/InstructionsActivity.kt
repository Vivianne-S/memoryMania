package com.example.memorymania

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.memorymania.R

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)

        // Hämta tillbaka-knappen och sätt lyssnare
        val backToMenuButton: Button = findViewById(R.id.back_to_menu_button)
        backToMenuButton.setOnClickListener {
            // Gå tillbaka till huvudmenyn
            finish()
        }
    }
}
