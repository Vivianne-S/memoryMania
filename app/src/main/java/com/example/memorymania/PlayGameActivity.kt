package com.example.memorymania

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PlayGameActivity : AppCompatActivity() {

    private lateinit var images: Array<ImageView>
    private lateinit var resetButton: Button
    private lateinit var backToMenuButton: Button
    private lateinit var timerLable: Textview
    private lateinit var gridLayout: GridLayout

    private val imageResources = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8
    ).shuffled()

    private val handler = Handler()
    private var elapsedTime = 0
    private var firstClick = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game) // Sätter xml för Play Game activity när knappen trycks.

        // Refernser till UI element
        resetButton = findViewById(R.id.resetButton)
        backToMenuButton = findViewById(R.id.backToMenuButton)
        timerLable = findViewById(R.id.timerLable)
        gridLayout = findViewById(R.id.gridLayout)

        // Skapa ImageView för varje kort i rutnätet
        images = createImageViews()

        // Lägger till bilderna i gridLayout
        images.forEach { gridLayout.addView(it)}

        // Hanterar reset knappen
        resetButton.setOnClickListener {
            resetGame()
        }

        // Hanterar back to menu knappen
        backToMenuButton.SetOnClickListener {
            finish()  // Avslutar aktivitet och går tillbaka till huvudmenyn.
        }




    }
}