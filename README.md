Prototype made in Figma for later developing of a memory game app in Android studio with Kotlin and XML.

https://www.figma.com/proto/5ERjhvg8Cf7qSH0JHm4qow/Memory?node-id=0-1&t=2R04ZDb5Dq89BnvK-1

# Memory Mania

Memory Mania is a classic memory card game built in Kotlin for Android.  
The goal is to flip cards and match all pairs as quickly as possible.  
Your best times are automatically saved and displayed in the scoreboard.

---

## Features

- Interactive gameplay with randomized card layout each round.
- Timer that starts on the first card flip.
- Reset Game button to restart with new card placement.
- Scoreboard that displays your top 5 fastest times using SharedPreferences.
- Navigation between Main Menu, Game Screen, Scoreboard, and Instructions.
- Custom user interface built with ConstraintLayout and GridLayout.

---

## Screens

- **Main Menu:** Options for Play Game, Scoreboard, Instructions, and Exit.
- **Game Screen:** Displays a 4x4 card grid with a timer and control buttons.
- **Scoreboard:** Shows the top 5 best times with a Back to Menu button.
- **Instructions:** Provides a short guide on how to play the game.

---

## Tech Stack

- Language: Kotlin  
- UI: Android XML with ConstraintLayout and GridLayout  
- Data Storage: SharedPreferences  
- IDE: Android Studio  
- Minimum SDK: 24  

---

## How It Works

1. Tap a card to flip it.  
2. Match two identical cards for them to stay visible.  
3. Non-matching cards flip back after a short delay.  
4. When all pairs are matched, the timer stops.  
5. The completion time is saved and added to the scoreboard.

---

## Project Structure
MemoryMania/
├── app/
│   ├── java/com/example/memorymania/
│   │   ├── MainActivity.kt
│   │   ├── PlayGameActivity.kt
│   │   ├── ScoreBoardActivity.kt
│   │   └── GameInstructionsActivity.kt
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml
│   │   │   ├── activity_play_game.xml
│   │   │   ├── activity_scoreboard.xml
│   │   │   └── activity_game_instructions.xml
│   │   ├── drawable/
│   │   └── values/strings.xml
