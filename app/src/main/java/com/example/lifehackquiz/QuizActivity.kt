package com.example.lifehackquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// This activity handles the quiz screen where questions are displayed
class QuizActivity : AppCompatActivity() {

    // Keeps track of the current question index
    private var currentQuestionIndex = 0

    // Stores the user's score
    private var score = 0

    // UI components (linked to XML layout)
    private lateinit var questionNumberText: TextView
    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView
    private lateinit var hackButton: Button
    private lateinit var mythButton: Button
    private lateinit var nextButton: Button

    // Gets all questions from QuizData class
    private val questions = QuizData.questions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Linking UI elements with XML IDs
        questionNumberText = findViewById(R.id.questionNumberText)
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)
        hackButton = findViewById(R.id.hackButton)
        mythButton = findViewById(R.id.mythButton)
        nextButton = findViewById(R.id.nextButton)

        // Show the first question when activity starts
        showQuestion()

        // When user clicks "Hack" (true)
        hackButton.setOnClickListener {
            checkAnswer(true)
        }

        // When user clicks "Myth" (false)
        mythButton.setOnClickListener {
            checkAnswer(false)
        }

        // Move to next question or go to score screen
        nextButton.setOnClickListener {
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                showQuestion()
            } else {
                // Send score to ScoreActivity when quiz ends
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    // Displays the current question on screen
    private fun showQuestion() {
        val question = questions[currentQuestionIndex]

        questionNumberText.text = "Question ${currentQuestionIndex + 1} of ${questions.size}"
        questionText.text = question.statement
        feedbackText.text = ""

        // Enable buttons for new question
        hackButton.isEnabled = true
        mythButton.isEnabled = true

        // Hide next button until answer is selected
        nextButton.visibility = View.GONE

        // Log for debugging (shows current question in Logcat)
        Log.d("QuizActivity", "Showing question ${currentQuestionIndex + 1}")
    }

    // Checks if user's answer is correct
    private fun checkAnswer(userAnswer: Boolean) {
        val question = questions[currentQuestionIndex]

        if (userAnswer == question.answer) {
            score++ // Increase score if correct
            feedbackText.text = "Correct! ${question.explanation}"
            Log.d("QuizActivity", "Correct answer. Score is now $score")
        } else {
            feedbackText.text = "Wrong! ${question.explanation}"
            Log.d("QuizActivity", "Wrong answer.")
        }

        // Disable buttons after answering to prevent multiple clicks
        hackButton.isEnabled = false
        mythButton.isEnabled = false

        // Show next button after answering
        nextButton.visibility = View.VISIBLE
    }
}