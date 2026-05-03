package com.example.lifehackquiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val reviewText = findViewById<TextView>(R.id.reviewText)

        var reviewContent = ""

        for (i in QuizData.questions.indices) {
            val question = QuizData.questions[i]
            val correctAnswer = if (question.answer) "Hack" else "Myth"

            reviewContent += """
                Question ${i + 1}:
                ${question.statement}
                
                Correct Answer: $correctAnswer
                
                Explanation:
                ${question.explanation}
                
                -------------------------
                
                
            """.trimIndent()
        }

        reviewText.text = reviewContent
    }
}