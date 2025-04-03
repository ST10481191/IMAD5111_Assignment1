package com.example.assignment1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast", "MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Gather UI components
        val timeText = findViewById<EditText>(R.id.timeText) // EditText for entering time of day
        val mealText = findViewById<TextView>(R.id.courseText) // TextView for displaying meal suggestions
        val resetButton = findViewById<Button>(R.id.undoButton) // Button to reset input and output
        val submitButton = findViewById<Button>(R.id.enterButton) // Button to submit input and get meal suggestions

        // Create a data class to hold the meal's cost
        data class Meal(val name: String, val price: Double)

        // Food suggestions with their prices
        val suggestions = mapOf(
            "Morning" to listOf(
                Meal("Avocado toast with poached eggs and smoothie", 6.99),
                Meal("Oatmeal with fresh fruit", 8.49),
                Meal("Pancakes with maple syrup and coffee", 5.99),
                Meal("Scrambled eggs with spinach and toast", 9.99),
                Meal("Greek yogurt with granola and honey", 6.99)
            ),
            "Mid-Morning" to listOf(
                Meal("Bagel with cream cheese", 2.99),
                Meal("Apple cinnamon muffin", 3.99),
                Meal("Smoothie bowl with toppings", 7.49),
                Meal("Fresh fruit platter", 5.99),
                Meal("Granola bar", 1.49)
            ),
            "Afternoon" to listOf(
                Meal("Veggie wrap with hummus", 10.49),
                Meal("Quinoa salad", 10.99),
                Meal("Grilled chicken sandwich with iced tea", 15.49),
                Meal("Veggie stir fry with rice", 9.99),
                Meal("BBQ pulled pork sandwich", 6.49)
            ),
            "Mid-Afternoon" to listOf(
                Meal("Spinach and cheese croissant", 3.99),
                Meal("Mozzarella sticks", 6.49),
                Meal("Veggie sushi rolls", 5.99),
                Meal("Soft pretzel", 1.49),
                Meal("Chicken salad", 6.49)
            ),
            "Night" to listOf(
                Meal("Grilled salmon with roasted vegetables", 15.99),
                Meal("Veggie lasagna with garlic bread", 12.99),
                Meal("BBQ ribs with coleslaw", 14.49),
                Meal("Shrimp stir fry with rice noodles", 13.99),
                Meal("Teriyaki chicken with steamed rice", 12.49)
            ),
            "Dessert" to listOf(
                Meal("Lemon meringue pie", 4.49),
                Meal("Apple crumble", 3.99),
                Meal("Salted caramel tart", 5.99),
                Meal("Vegan chocolate mousse", 4.49),
                Meal("Cheese Cake", 5.49),
                Meal("Coconut macaroon", 2.99)
            )
        )

        // Reset button performance
        resetButton.setOnClickListener {
            timeText.text.clear()
            mealText.text = ""
        }

        // Submit button performance
        submitButton.setOnClickListener {
            val inputText = timeText.text.toString().trim()

            if (inputText.isEmpty()) {
                Toast.makeText(this, "Please enter a time of day", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Look for any meal groups where the input time corresponds
            val mealSuggestions = suggestions[inputText]

            if (mealSuggestions != null) {
                // Display suggestions for the selected time
                val mealList = mealSuggestions.joinToString("\n") { "${it.name} - \$${"%.2f".format(it.price)}" }
                mealText.text = "Suggestions for $inputText:\n$mealList"
            } else {
                // Show error if the time of day is invalid
                Toast.makeText(this, "Invalid time of day. Try Morning, Mid-Morning, Afternoon, etc.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


