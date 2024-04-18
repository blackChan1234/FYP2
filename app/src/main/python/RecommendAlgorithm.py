from pymongo import MongoClient
import json

# Connect to MongoDB (Update with your MongoDB URI)
client = MongoClient('mongodb://localhost:27017/')
db = client['meal_database']  # Change 'meal_database' to your database name
meals = db.meals  # Change 'meals' to your collection name

# Example user data - typically this will come from the user inputs in your app
user_preferences = {
    'age': 25,
    'gender': 'male',
    'weight': 70,
    'height': 175,
    'dietaryRestrictions': ['glutenFree', 'dairyFree'],
    'cuisinePreferences': ['Mediterranean'],
}

# Function to recommend meals based on dietary restrictions and cuisine preferences
def recommend_meal(preferences):
    query = {
        '$and': [
            {'diets': {'$in': preferences['dietaryRestrictions']}},
            {'cuisines': {'$in': preferences['cuisinePreferences']}}
        ]
    }
    recommended_meals = meals.find(query)
    return list(recommended_meals)

# Get recommended meals
recommended_meals = recommend_meal(user_preferences)
for meal in recommended_meals:
    print(meal['title'], meal['id'])

# Close the MongoDB connection
client.close()
