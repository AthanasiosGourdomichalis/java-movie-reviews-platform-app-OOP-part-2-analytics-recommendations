import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		Platform platform = new Platform();//Instance of the Platform class. Represents the platform managing the collection of movies within a list, alongside one active user session at a time
		Scanner in = new Scanner(System.in);
		DataLoader loader = new DataLoader(platform);
		//Instantiate Comparator objects of appropriate types based on the sorting criteria
		MovieAverageRatingComparator comp1 = new MovieAverageRatingComparator();
		MovieTitleComparator comp2 = new MovieTitleComparator();
		MovieYearComparator comp3 = new MovieYearComparator ();


	//----------------------------------------------DEMO---------------------------------------------------------------------
		loader.loadFile("Reviews.csv");
		

	//----------------------------------------------------MENU-------------------------------------------------------------
		System.out.println("****************************************************************************************************");
		System.out.println("****************************************************************************************************");
		platform.login(); //  login MENU (Guest, Verified, Critic)

		//MAIN MENU
		while (true) {
			System.out.println("****************************************************************************************************");
			System.out.println("\n--- MENU ---");
			System.out.println("1. Add Movie"); //Movie registration
			System.out.println("2. Search Movie"); //Search
			System.out.println("3. List Sorted Movies (by Rating/Title/Year)"); //Sorting based on 3 criteria
			System.out.println("4. Genres");//Display movies in each genre
			System.out.println("5. All Movies"); //Displays all movies in the catalog
			System.out.println("6. Show Profile"); //Prints user details
			System.out.println("7. Change User"); //Change active user account
			System.out.println("8. Statistics");//search movie by genre and display top five
			System.out.println("9. Recommended Movies");//Display recommended movies. Recommendation system.
			System.out.println("10. Exit"); //exit the app
			System.out.print("Choose (1-10): ");
			
			int input = Integer.parseInt(in.nextLine());
			System.out.println("----------------------------------------------------------------------------------------------------");
			if (input == 1) { //Movie registration
				platform.addMovie();
			}

			if (input == 2) { //Movie search (platform)
				if(platform.search()){
					System.out.println("----------------------------------------------------------------------------------------------------");
					System.out.print("Enter index of movie: ");
					int index = Integer.parseInt(in.nextLine());
					platform.specificMovie(index);
				}

			}

			if (input == 3) { //Sorting based on: 1.Rating\ 2.Title\ 3.Year of release
				System.out.println("1. Sort by Rating\n2. Sort by Title\n3. Sort by Year");
				
				System.out.print("Choose (1-3): ");
        
				String sortChoice = in.nextLine(); 
				System.out.println("----------------------------------------------------------------------------------------------------");
				List<Movie> templist;
				
				if (sortChoice.equals("1")) { //Displays top-rated movies per genre (with at least n reviews and average rating > x)

					

					templist = platform.getSortedMovies(platform.getMovies(),comp1);
					platform.displayMoviesWithFilters(templist);//asks the user how many reviews and what rating the displayed movies should have
				}

				if (sortChoice.equals("2")) { //sorting by title
					templist = platform.getSortedMovies(platform.getMovies(),comp2);
					for(Movie obj : templist){
						System.out.println(obj);
					}

				}
				if (sortChoice.equals("3")) { //sorting by year
					templist = platform.getSortedMovies(platform.getMovies(),comp3);
					for(Movie obj : templist){
						System.out.println(obj);
					}

				}


			}
			if (input == 4) { //display details per genre
				Map<String, List<Movie>> genresMap = platform.getGenreMap();// creation of Map<Genre, List<Movie>> for movies belonging to each genre
				System.out.println("Available Genres:");
				for (String genre : genresMap.keySet()) {
					System.out.println("- " + genre);
				}
				System.out.print("\nEnter genre to view movies: ");
				String ans = in.nextLine();
				
			
				if (genresMap.containsKey(ans)) {
					platform.specificGenre(ans,genresMap);//display movies of the corresponding genre
					
				} else {
					System.out.println("Genre not found. Try again.");
				}
					
				
			}
			
			
				
			
			if (input == 5) { // display details of all movies in the catalog list
				platform.listMovies();
			}
			if (input == 6) { //Prints user details
				platform.profile();
			}

			if (input == 7) { //Returns the user to the main menu to log in as a different account/user
				System.out.println("****************************************************************************************************");
				platform.addCurrentUser();//registers the user into userList before changing the user
				platform.login();
			}

			if (input == 8) {
				
				System.out.println("1. Top 5 movies by genre");
				System.out.println("2. Highly liked movies");
				System.out.println("3. Average Rating by user");
				System.out.print("Choose (1-3): ");
				int index = Integer.parseInt(in.nextLine());

				System.out.println("----------------------------------------------------------------------------------------------------");
				if (index == 1) {
					List<Movie> topMovies = platform.getTopMovies();
					if (topMovies.isEmpty()) {
						System.out.println("No movies found for genre.");
						System.out.println("----------------------------------------------------------------------------------------------------");
					} else {
						System.out.println("----------------------------------------------------------------------------------------------------");
						System.out.println("Top 5 Movies in genre:");
						for (int i = 0; (i < topMovies.size() && i<5); i++) {
						System.out.println((i + 1) + ". " + topMovies.get(i));
							} 
					}
				} else if (index == 2) {
					List<Movie> highlyliked = platform.getHighlyLiked();
					for (int i = 0; i < highlyliked.size(); i++) {
						System.out.println((i + 1) + ". " + highlyliked.get(i));
					}
				} else if (index == 3) {
					platform.AverageRatingPerUser();
				} else {
					System.out.println("Invalid Choice");
				}
			}		
			
				if (input == 9){
					Recommender rec = new Recommender();
					List<Movie> recMovies;
					User currentUser = platform.getCurrentUser();

					System.out.println("----------------------------------------------------------------------------------------------------");
					System.out.println("1. Collaborative Filtering (User-Based)");
					System.out.println("2. Content-Based Filtering");

					System.out.print("Choose (1-2): ");
					int index = Integer.parseInt(in.nextLine());
					System.out.println("----------------------------------------------------------------------------------------------------");

					if(index == 1){
						recMovies = rec.recommendByUserSimilarity(currentUser,platform);
						Set<Movie> moviesSet = new HashSet<>(recMovies);
						if( recMovies.isEmpty()){ System.out.println("No Similar Movies Available.");}
					else{
						System.out.println("Recommended Movies:");
						for(Movie m: moviesSet){
							System.out.println(m);
						}
					}
					} else if (index == 2){
						recMovies = rec.recommendByContent(currentUser,platform);
						Set<Movie> moviesSet = new HashSet<>(recMovies);
						if( recMovies.isEmpty()){ System.out.println("No Similar Movies Available.");}
					else{
						System.out.println("Recommended Movies:");
						for(Movie m: moviesSet){
							System.out.println(m);
						}
					}
					} else {
					System.out.println("Invalid Choice");
					}

					

				}
				

			if (input == 10) { //exit
				System.out.println("Exiting...");
				System.out.println("****************************************************************************************************");
				System.out.println("****************************************************************************************************");
				break;
			}

			if( input >10 || input <1) { //else...
				System.out.println("Invalid choice.");
			}
		}
	}
}
