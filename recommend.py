import pandas as pd
from pymongo import MongoClient
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# MongoDB connection setup
client = MongoClient('mongodb://localhost:27017/')
db = client['your_database_name']  # Adjust the database name as per your setup

# Function to load data from MongoDB
def load_data():
    users = pd.DataFrame(list(db.users.find()))
    foods = pd.DataFrame(list(db.foods.find()))
    orders = pd.DataFrame(list(db.orders.find()))
    return users, foods, orders

users, foods, orders = load_data()

# Convert fields from MongoDB BSON to usable Python data types
foods['preparationMinutes'] = foods['preparationMinutes'].apply(lambda x: x.get('$numberInt', 0)).astype(int)
foods['cookingMinutes'] = foods['cookingMinutes'].apply(lambda x: x.get('$numberInt', 0)).astype(int)
foods['aggregateLikes'] = foods['aggregateLikes'].apply(lambda x: x.get('$numberInt', 0)).astype(int)
foods['healthScore'] = foods['healthScore'].apply(lambda x: x.get('$numberInt', 0)).astype(float)
foods['pricePerServing'] = foods['pricePerServing'].apply(lambda x: x.get('$numberDouble', 0.0)).astype(float)

# Create a 'soup' of keywords for each food item
def create_soup(x):
    return ' '.join([x['title'], x['summary'], ','.join(x['cuisines'])])

foods['soup'] = foods.apply(create_soup, axis=1)

# Applying CountVectorizer to 'soup' to create a count matrix
count = CountVectorizer(stop_words='english')
count_matrix = count.fit_transform(foods['soup'])

# Calculating cosine similarity matrix from the count matrix
cosine_sim = cosine_similarity(count_matrix, count_matrix)

# Mapping indices with food titles
indices = pd.Series(foods.index, index=foods['title']).drop_duplicates()

# Function to get recommendations based on the food title
def get_recommendations(title, cosine_sim=cosine_sim):
    idx = indices[title]
    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:11]  # Get the top 10 items
    food_indices = [i[0] for i in sim_scores]
    return foods.iloc[food_indices][['title', 'pricePerServing', 'summary']]

# Example of generating recommendations
recommended_foods = get_recommendations('Crock-Pot Greek Chicken (for Salads or Pita Sandwiches)')
print(recommended_foods)
np.random.seed(42)
user_feedback = pd.DataFrame({
    'food_id': foods['_id'],
    'user_interaction': np.random.randint(0, 2, size=len(foods))
})

def calculate_metrics(predictions, truths):
    """ Calculate precision, recall, and F1-score """
    true_positives = np.sum((predictions == 1) & (truths == 1))
    predicted_positives = np.sum(predictions)
    actual_positives = np.sum(truths)

    precision = true_positives / predicted_positives if predicted_positives else 0
    recall = true_positives / actual_positives if actual_positives else 0
    f1_score = 2 * (precision * recall) / (precision + recall) if (precision + recall) else 0

    return precision, recall, f1_score

# Example usage: evaluate the top 5 recommendations for a specific food
top_5_recommended_indices = get_recommendations('Crock-Pot Greek Chicken (for Salads or Pita Sandwiches)').index
predicted_relevance = user_feedback.loc[top_5_recommended_indices]['user_interaction'].values
actual_relevance = np.random.randint(0, 2, size=5)  # Simulate actual relevance as random for example purposes

precision, recall, f1_score = calculate_metrics(predicted_relevance, actual_relevance)
print(f"Precision: {precision}, Recall: {recall}, F1 Score: {f1_score}")