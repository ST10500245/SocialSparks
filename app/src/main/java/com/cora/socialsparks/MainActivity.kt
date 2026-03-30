package com.cora.socialsparks

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity — the heart of Social Sparks.
 *
 * This app was built for my friend Cora, who sometimes forgets to stay
 * in touch with people during hectic days. The idea is simple: type in
 * the time of day, and the app gives you a small "social spark" — a tiny
 * action you can do right now to stay connected with the people you love.
 *
 * The logic uses if-else statements to match the user's input to a
 * predefined suggestion. It's intentionally straightforward so anyone
 * can read and understand the code.
 */
class MainActivity : AppCompatActivity() {

    // I'm tagging logs so they're easy to find in Logcat when debugging
    private val TAG = "SocialSparks"

    // UI elements — declared up here so all our functions can access them
    private lateinit var etTimeOfDay: EditText
    private lateinit var btnGetSpark: Button
    private lateinit var btnReset: Button
    private lateinit var tvSuggestion: TextView
    private lateinit var tvSuggestionLabel: TextView
    private lateinit var suggestionCard: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "App started — Social Sparks is ready to go!")

        // Hook up the views from our layout file
        etTimeOfDay = findViewById(R.id.etTimeOfDay)
        btnGetSpark = findViewById(R.id.btnGetSpark)
        btnReset = findViewById(R.id.btnReset)
        tvSuggestion = findViewById(R.id.tvSuggestion)
        tvSuggestionLabel = findViewById(R.id.tvSuggestionLabel)
        suggestionCard = findViewById(R.id.suggestionCard)

        // When the user taps "Get My Spark", figure out what to suggest
        btnGetSpark.setOnClickListener {
            handleGetSpark()
        }

        // When the user taps "Reset", clear everything back to the start
        btnReset.setOnClickListener {
            handleReset()
        }
    }

    /**
     * This is where the magic happens.
     * We read what the user typed, clean it up, and use if-else statements
     * to figure out which social spark to show them.
     */
    private fun handleGetSpark() {
        // Grab whatever the user typed and tidy it up
        val rawInput = etTimeOfDay.text.toString()
        val userInput = rawInput.trim().lowercase()

        Log.d(TAG, "User entered: \"$rawInput\" (cleaned: \"$userInput\")")

        // First, check if they actually typed something
        if (userInput.isEmpty()) {
            Log.w(TAG, "Empty input detected — showing friendly error")
            showError(getString(R.string.error_empty_input))
            return
        }

        // Now let's match the input to a time of day and pick a suggestion.
        // Using if-else here as required — each block checks for a specific
        // time period. I'm checking for multiple variations of each time
        // so Cora doesn't have to type it perfectly every time.

        val suggestion: String

        if (userInput == "morning" || userInput == "good morning" || userInput == "am") {
            // Early morning — start the day with a kind gesture
            suggestion = getString(R.string.spark_morning)
            Log.i(TAG, "Matched: MORNING")

        } else if (userInput == "mid-morning" || userInput == "mid morning"
            || userInput == "midmorning" || userInput == "late morning") {
            // Mid-morning — perfect time to show appreciation at work
            suggestion = getString(R.string.spark_mid_morning)
            Log.i(TAG, "Matched: MID-MORNING")

        } else if (userInput == "afternoon" || userInput == "lunch"
            || userInput == "midday" || userInput == "noon") {
            // Afternoon — keep the energy up with some fun
            suggestion = getString(R.string.spark_afternoon)
            Log.i(TAG, "Matched: AFTERNOON")

        } else if (userInput == "afternoon snack time" || userInput == "snack time"
            || userInput == "snack" || userInput == "tea time"
            || userInput == "late afternoon") {
            // Snack time — a quiet moment to think of others
            suggestion = getString(R.string.spark_snack_time)
            Log.i(TAG, "Matched: AFTERNOON SNACK TIME")

        } else if (userInput == "dinner" || userInput == "supper"
            || userInput == "evening" || userInput == "dinner time") {
            // Dinner time — connect with a real conversation
            suggestion = getString(R.string.spark_dinner)
            Log.i(TAG, "Matched: DINNER")

        } else if (userInput == "after dinner" || userInput == "night"
            || userInput == "tonight" || userInput == "late night"
            || userInput == "bedtime" || userInput == "pm"
            || userInput == "after dinner / night") {
            // Night-time — wind down with a thoughtful online interaction
            suggestion = getString(R.string.spark_night)
            Log.i(TAG, "Matched: NIGHT")

        } else {
            // If we get here, whatever they typed didn't match any time of day.
            // Let's give them a helpful nudge in the right direction.
            Log.w(TAG, "No match found for input: \"$userInput\"")
            showError(getString(R.string.error_invalid_input))
            return
        }

        // If we made it here, we found a valid suggestion — show it!
        showSuggestion(suggestion)
    }

    /**
     * Display a social spark suggestion on the card.
     * We update the label to say "Your Social Spark" and show the suggestion text.
     */
    private fun showSuggestion(suggestion: String) {
        tvSuggestionLabel.text = "Your Social Spark ⚡"
        tvSuggestionLabel.setTextColor(getColor(R.color.purple_button))
        tvSuggestion.text = suggestion
        tvSuggestion.setTextColor(getColor(R.color.dark_text))

        Log.d(TAG, "Displaying suggestion to user")
    }

    /**
     * Display an error message on the card.
     * We change the label colour to make it clear something went wrong,
     * but keep the tone friendly and encouraging.
     */
    private fun showError(errorMessage: String) {
        tvSuggestionLabel.text = "Oops! 😅"
        tvSuggestionLabel.setTextColor(getColor(R.color.error_red))
        tvSuggestion.text = errorMessage
        tvSuggestion.setTextColor(getColor(R.color.error_red))

        Log.d(TAG, "Displaying error message to user")
    }

    /**
     * Reset everything back to the initial state.
     * Clears the text input, resets the suggestion card,
     * and gets the app ready for another go.
     */
    private fun handleReset() {
        // Clear the text field
        etTimeOfDay.text.clear()

        // Reset the suggestion card back to its default state
        tvSuggestionLabel.text = "Your Social Spark"
        tvSuggestionLabel.setTextColor(getColor(R.color.light_text))
        tvSuggestion.text = getString(R.string.default_suggestion)
        tvSuggestion.setTextColor(getColor(R.color.dark_text))

        Log.d(TAG, "App reset — ready for a fresh spark!")
    }
}
