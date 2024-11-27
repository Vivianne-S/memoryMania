package com.example.memorymania

import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class PlayGameActivity : AppCompatActivity() {

    private lateinit var resetButton: Button
    private lateinit var backToMenuButton: Button
    private lateinit var timerLabel: TextView
    private lateinit var gridLayout: GridLayout
    private lateinit var images: Array<ImageView>

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
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game) // Sätter xml för Play Game activity när knappen trycks.

        // Skapar ImageViews för varje kort och lägger till i layout
        images = createImageViews()
        images.forEach { gridLayout.addView(it) }




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


    // Skapar ImageViews för korten
    private fun createImageViews(): Array<ImageView> {
        val shuffledImages = imageResources.shuffled() // Blandar korten slumpmässigt
        return shuffledImages.map { imageRes ->
            ImageView(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 200
                    height = 200
                    setMargins(8, 8, 8, 8)
                }
                setImageResource(R.drawable.card_back) // Baksidan av kortet
            }
        }.toTypedArray()
    }
}
    private fun resetGame() {

    }
