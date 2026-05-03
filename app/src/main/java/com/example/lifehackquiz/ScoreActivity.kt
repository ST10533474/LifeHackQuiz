package com.example.lifehackquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoreText = findViewById<TextView>(R.id.scoreText)
        val messageText = findViewById<TextView>(R.id.messageText)
        val reviewButton = findViewById<Button>(R.id.reviewButton)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        scoreText.text = "Your score: $score / $total"

        messageText.text = when {
            score >= 4 -> "Master Hacker! You know your life hacks well."
            score >= 2 -> "Good effort! Keep learning."
            else -> "Stay Safe Online! Some myths can be misleading."
        }

        reviewButton.setOnClickListener {
            startActivity(Intent(this, ReviewActivity::class.java))
        }
    }
}