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

    private lateinit var images: Array<ImageView> // Håller alla ImageView-objekt för korten
    private lateinit var resetButton: Button // Knapp för att återställa spelet
    private lateinit var backToMenuButton: Button // Knapp för att gå tillbaka till huvudmenyn
    private lateinit var timerLabel: TextView // Visar den förflutna tiden under spelets gång
    private lateinit var gridLayout: GridLayout // GridLayout som innehåller korten

    // Lista med bildresurser som representerar paren
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

    private val handler = Handler(Looper.getMainLooper()) // Hanterare för att köra kod på huvudtråden
    private var elapsedTime = 0 // Förfluten tid som ska visas på skärmen
    private var firstClick = true // Kontrollera om det är första klicket för att starta timern
    private var timerStarted = false // Flagga för att hålla reda på om timern redan har startat

    private var flippedCards = mutableListOf<Pair<ImageView, Int>>() // Lista som håller reda på vända kort
    private var matchedPairs = 0 // Räknare för antal matchade kortpar

    // När aktiviteten skapas (vid appens start eller när man kommer tillbaka till denna skärm)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game) // Sätter upp layouten för spelet

        // Referenser till UI-element
        resetButton = findViewById(R.id.reset_game_button)
        backToMenuButton = findViewById(R.id.back_to_menu_button)
        timerLabel = findViewById(R.id.timer_label)
        gridLayout = findViewById(R.id.gridLayout)

        // Skapar ImageViews för varje kort i rutnätet
        images = createImageViews()

        // Lägger till alla ImageViews i gridLayout för att visa korten på skärmen
        images.forEach { gridLayout.addView(it) }

        // Hanterar reset-knappen för att starta om spelet
        resetButton.setOnClickListener {
            resetGame() // Anropar resetGame-metoden för att återställa spelet
        }

        // Hanterar tillbaka till meny-knappen
        backToMenuButton.setOnClickListener {
            finish() // Stänger den nuvarande aktiviteten och återgår till huvudmenyn
        }
    }

    // Skapar ImageViews för varje kort och sätter baksidan av kortet
    private fun createImageViews(): Array<ImageView> {
        val shuffledImages = imageResources.shuffled() // Slumpar om bilderna för att skapa variation
        return shuffledImages.map { imageRes ->
            ImageView(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 200
                    height = 200
                    setMargins(8, 8, 8, 8) // Sätter marginaler för varje kort
                }
                setImageResource(R.drawable.card_back) // Sätter baksidan på kortet från början
                setOnClickListener { onCardClicked(this, imageRes) } // Lägger till klick-hanterare för varje kort
            }
        }.toTypedArray() // Omvandlar listan till en array av ImageViews
    }

    // Hanterar när ett kort klickas
    private fun onCardClicked(imageView: ImageView, imageRes: Int) {
        // Förhindrar att fler än två kort vänds samtidigt eller om kortet redan är vänt
        if (flippedCards.size == 2 || flippedCards.any { it.first == imageView }) {
            return
        }

        // Startar timern vid första klicket
        if (firstClick && !timerStarted) {
            startTimer() // Anropar startTimer-metoden för att börja räkna tiden
            timerStarted = true
        }

        // Vänder på kortet och visar dess bild
        imageView.setImageResource(imageRes)
        flippedCards.add(Pair(imageView, imageRes)) // Lägger till det vända kortet i listan

        // Om två kort har vänt på sig, kollar vi om de är ett par
        if (flippedCards.size == 2) {
            checkForMatch() // Anropar checkForMatch-metoden för att kontrollera om korten matchar
        }
    }

    // Kollar om de två vända korten är ett matchande par
    private fun checkForMatch() {
        if (flippedCards[0].second == flippedCards[1].second) { // Om bilderna på korten matchar
            flippedCards.clear() // Rensar listan med vända kort
            matchedPairs++ // Ökar räknaren för matchade par

            // Om alla kort är matchade, avslutas spelet
            if (matchedPairs == imageResources.size / 2) {
                timerLabel.text = getString(R.string.game_completed_message, elapsedTime) // Visar meddelande om att spelet är klart

                // Stoppar timern
                handler.removeCallbacksAndMessages(null)

                // Sparar bästa tider i SharedPreferences
                val sharedPref = getSharedPreferences("game_data", MODE_PRIVATE)
                val times = sharedPref.getStringSet("best_times", mutableSetOf())!!.map { it.toInt() }.toMutableList()
                times.add(elapsedTime) // Lägg till den aktuella tiden i listan
                times.sort() // Sortera tiderna

                // Spara de 10 bästa tiderna i SharedPreferences
                sharedPref.edit().putStringSet("best_times", times.take(10).map { it.toString() }.toSet()).apply()

                // Blockera alla kort så att inga fler klick kan göras
                images.forEach {
                    it.isClickable = false
                }

                // Gör knapparna aktiva även när spelet är klart
                backToMenuButton.isEnabled = true
                resetButton.isEnabled = true
            }

        } else { // Om korten inte matchar
            // Vänta 1 sekund och vänd tillbaka korten om de inte matchar
            handler.postDelayed({
                flippedCards[0].first.setImageResource(R.drawable.card_back)
                flippedCards[1].first.setImageResource(R.drawable.card_back)
                flippedCards.clear() // Rensar listan med vända kort
            }, 1000)
        }
    }

    // Startar timern och uppdaterar tiden varje sekund
    private fun startTimer() {
        firstClick = false
        handler.postDelayed(object : Runnable {
            override fun run() {
                elapsedTime++ // Öka förfluten tid med 1 sekund
                timerLabel.text = getString(R.string.timer_label, elapsedTime) // Uppdatera timer-displayen
                handler.postDelayed(this, 1000) // Kör denna metod varje sekund
            }
        }, 1000)
    }

    // Återställer spelet till sitt ursprungliga tillstånd
    private fun resetGame() {
        elapsedTime = 0 // Återställ förfluten tid
        timerLabel.text = getString(R.string.timer_label, elapsedTime) // Återställ timer-displayen
        firstClick = true // Återställ första klick-flagga
        timerStarted = false // Återställ timern
        handler.removeCallbacksAndMessages(null) // Stoppa timern om spelet återställs

        // Återställ alla kort till deras baksida och gör dem klickbara
        images.forEach {
            it.setImageResource(R.drawable.card_back)
            it.isClickable = true
        }

        // Slumpa om korten och sätt dem i rutnätet igen
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
