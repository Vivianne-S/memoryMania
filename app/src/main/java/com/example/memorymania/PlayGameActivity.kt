package com.example.memorymania

import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView


class PlayGameActivity : AppCompatActivity() {

    private lateinit var images: Array<ImageView>
    private lateinit var resetButton: Button
    private lateinit var backToMenuButton: Button
    private lateinit var timerLabel: TextView
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
    )

    private val handler = Handler(Looper.getMainLooper())
    private var elapsedTime = 0
    private var firstClick = true
    private var timerStarted = false

    private var flippedCards = mutableListOf<Pair<ImageView, Int>>()
    private var matchedPairs = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game) // Sätter xml för Play Game activity när knappen trycks.

        // Referenser till UI-element
        resetButton = findViewById(R.id.reset_game_button)
        backToMenuButton = findViewById(R.id.back_to_menu_button)
        timerLabel = findViewById(R.id.timer_label)
        gridLayout = findViewById(R.id.gridLayout)

        // Skapa ImageViews för varje kort i rutnätet
        images = createImageViews()

        // Lägg till bilderna i gridLayout
        images.forEach { gridLayout.addView(it) }

        // Hantera reset-knappen
        resetButton.setOnClickListener {
            resetGame()
        }

        // Hantera back to menu-knappen
        backToMenuButton.setOnClickListener {
            finish()  // Avslutar aktivitet och går tillbaka till huvudmenyn.
        }
    }

    // Skapa ImageView för varje kort och sätt baksidan av kortet
    private fun createImageViews(): Array<ImageView> {
        val shuffledImages = imageResources.shuffled()
        return shuffledImages.map { imageRes ->
            ImageView(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 200
                    height = 200
                    setMargins(8, 8, 8, 8)
                }
                setImageResource(R.drawable.card_back)
                setOnClickListener { onCardClicked(this, imageRes) }
            }
        }.toTypedArray()
    }

    // Hanterar click på bilder
    private fun onCardClicked(imageView: ImageView, imageRes: Int) {
        if (flippedCards.size == 2 || flippedCards.any { it.first == imageView }) {
            return
        }

        if (firstClick && !timerStarted) {
            startTimer()
            timerStarted = true
        }

        imageView.setImageResource(imageRes)
        flippedCards.add(Pair(imageView, imageRes))

        if (flippedCards.size == 2) {
            checkForMatch()
        }
    }

    // Kollar om två vända kort är ett par
    private fun checkForMatch() {
        if (flippedCards[0].second == flippedCards[1].second) {
            flippedCards.clear()
            matchedPairs++

            // Kolla om alla kort är matchade
            if (matchedPairs == imageResources.size / 2) {
                // Alla kort är matchade, spelet är klart
                timerLabel.text = getString(R.string.game_completed_message, elapsedTime)

                // Stoppa timern
                handler.removeCallbacksAndMessages(null)

                // Spara bästa tider i SharedPreferences
                val sharedPref = getSharedPreferences("game_data", MODE_PRIVATE)
                val times = sharedPref.getStringSet("best_times", mutableSetOf())!!.map { it.toInt() }.toMutableList()
                times.add(elapsedTime)
                times.sort()

                // Spara top 10 tider i SharedPreferences
                sharedPref.edit().putStringSet("best_times", times.take(10).map { it.toString() }.toSet()).apply()

                // Blockera alla kort så att inga fler klick kan göras
                images.forEach {
                    it.isClickable = false
                }

             // Gör så att man kan använda knapparna även under spelets gång.
                backToMenuButton.isEnabled = true
                resetButton.isEnabled = true
            }

        } else {
            // Om inte matchat, vänta 1 sekund och vänd tillbaka korten
            handler.postDelayed({
                flippedCards[0].first.setImageResource(R.drawable.card_back)
                flippedCards[1].first.setImageResource(R.drawable.card_back)
                flippedCards.clear()
            }, 1000)
        }
    }



    private fun startTimer() {
        firstClick = false
        handler.postDelayed(object : Runnable {
            override fun run() {
                elapsedTime++
                timerLabel.text = getString(R.string.timer_label, elapsedTime)
                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }


    private fun resetGame() {
        elapsedTime = 0
        // Återställer timer-label med hjälp av strängresurs
        timerLabel.text = getString(R.string.timer_label, elapsedTime)
        firstClick = true
        timerStarted = false
        handler.removeCallbacksAndMessages(null)

        // Återställ korten till baksidan
        images.forEach {
            it.setImageResource(R.drawable.card_back)
            it.isClickable = true // Gör korten klickbara igen
        }

        // Slumpa om korten och återställ spelet
        val shuffledImages = imageResources.shuffled()
        images.forEachIndexed { index, imageView ->
            imageView.setImageResource(R.drawable.card_back)
            imageView.setOnClickListener { onCardClicked(imageView, shuffledImages[index]) }
        }

        // Aktivera knapparna igen
        backToMenuButton.isEnabled = true
        resetButton.isEnabled = true
    }
}