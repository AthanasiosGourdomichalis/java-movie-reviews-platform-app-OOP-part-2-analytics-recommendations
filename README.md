# MOVIE PLATFORM APPLICATION - USER GUIDE
# 1. SYSTEM DESIGN (class hierarchies)
<img width="1011" height="562" alt="image" src="https://github.com/user-attachments/assets/238c1471-b8f5-43de-baa4-81d1eea909a1" />

-----------------------------------------------------------------------------------------------
# 2. FUNCTIONALITY 
# 2.1 Core Features 

# - File Parsing and Object Instantiation
* Read the reviews.csv file 
* Instantiate core domain objects:

o Movie (title, release year, genre, list of reviews) 

o User (username/ID, list of reviews) 

o Review (movie, user, rating, comment)

# - User Management:

* Registration / Login for 3 user types (Guest, Verified, Critic) 
* Account switching

# - Movie Management: 
* Adding movies 
* Search by title or director 
* Sorting (by rating with a minimum of 𝑁 reviews and average rating > 𝑋, title, release year) 
* Movie recommendation / association (same genre, common director) 
* Display movie list 
* Data organization and processing (by genre, average rating, and review count)

# - Review Platform Management: 
* Statistical queries / Analytics

# - Review System: 
* Review submission (rating + comment) 
* Display of reviews 
* Automatic average rating calculation

# - Movie Recommendation System: 
* Collaborative Filtering (User-Based) 
* Content-Based Filtering

-----------------------------------------------------------------------------------------------
# 3. LOGIN 
# 3.1 Login System  
# - Select Account Type: 
* Guest (Type 1): Username only (can submit basic reviews) 
* Verified User (Type 2): Username + email (can submit basic reviews) 
* Critic (Type 3): Username + email + password (can submit professional critic reviews)
-----------------------------------------------------------------------------------------------
# 4. MAIN MENU OPTIONS - DETAILED INSTRUCTIONS 
# 1. Add Movie 
# - Available exclusively to Critic accounts 
# - Steps: 
a) Press '1' from the main menu.

b) Enter the Movie Title when prompted. 

c) Enter the Release Year (e.g., 2025). 

d) Enter the director's full Name. 

e) Add Genres one by one (press Enter after each genre). 

f) When finished, press Enter on an empty line. 

The movie will be added to the platform if it does not already exist
-----------------------------------------------------------------------------------------------
# 2. Search Movie 
# - Steps:
    
a) Press '2' from the main menu.

b) Select the search type: 
• Press '1' to search by Movie Title. 
• Press '2' to search by Director Name. 

c) Enter the search query (partial matches are supported). 

d) View the list of results with index numbers. 

e) For details: 
• Enter the index number displayed before the title. 
• Press Enter. 

f) In the movie details view:

• Press '1' to view reviews and write a new review.

• Press '2' to view related movies. 

• Press '3' to return to the main menu.
-----------------------------------------------------------------------------------------------
# 3. Sorted Movie List 
# - Steps: 

1. Press '3' from the main menu. 
2. Select sorting method:

- Press '1' to sort by Rating: 
* Enter the minimum required number of reviews. 
* Enter the minimum rating threshold (e.g., 7.5). 
* Results are displayed with the highest ratings first. - Press '2' to sort by Title (A–Z). - Press '3' to sort by Year (newest first). 
3. View the sorted list. 
4. Automatically returns to the menu after viewing.
-----------------------------------------------------------------------------------------------
# 4. Display by Genre 
# - Steps: 

a) Press '4' from the main menu.

b) Select Genre: 

# - Choose sorting criteria based on review count and rating: 
* Enter the minimum required number of reviews. 
* Enter the minimum rating threshold (e.g., 7.5). 
* Results are displayed with the highest ratings first.
 
c) Automatically returns to the menu after viewing.
-----------------------------------------------------------------------------------------------
# 5. All Movies

# - Steps:

a) Press '5' from the main menu. 

b) View the complete list of all movies. 

c) The list is displayed and automatically returns to the menu.

-----------------------------------------------------------------------------------------------
# 6. View Profile 
# - Steps:

a) Press '6' from the main menu.

b) View your profile information: 
- Username 
- Account type (Guest / Verified / Critic) 
- All your submitted reviews 
- For Critics: special Critic ID 

c) Automatically returns to the menu after viewing.

-----------------------------------------------------------------------------------------------
# 7. Switch User 
# - Steps: 

a) Press '7' from the main menu. 

b) The login screen will appear again. 

c) After successful login, return to the main menu.

-----------------------------------------------------------------------------------------------
# 8. Statistics 
# - Steps:

a) Press '8' from the main menu.

b) Select Statistics:

- Press '1' to view the Top 5 movies per genre.
- Then type the corresponding genre.
- Press '2' to view high-appraisal movies. 
- Press '3' to view the user's average rating
-----------------------------------------------------------------------------------------------
# 9. Recommendations 
# - Steps: 
a) Press '9' from the main menu. 

# b) Select Recommendation Mechanism: 
- Press '1' to view movies watched by similar users (Collaborative Filtering). 
- Press '2' to view recommended movies based on your favorite genres (Content-Based Filtering).
-----------------------------------------------------------------------------------------------
# 10. Exit
# - Steps: 

a) Press '10' from the main menu.
-----------------------------------------------------------------------------------------------

---

# 👤 Author
## Athanasios Gourdomichalis - https://github.com/AthanasiosGourdomichalis

---
