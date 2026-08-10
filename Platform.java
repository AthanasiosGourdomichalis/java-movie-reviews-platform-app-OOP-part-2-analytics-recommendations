import java.util.*;

public class Platform{
    private List<Movie> movies; // List of pre-made Movies in Main and input Movies made by Users  
    private User currentUser; //User currently using the App
    private List<User> users;//Users List that have made reviews


    MovieAverageRatingComparator comp1 = new MovieAverageRatingComparator();

    public Platform() {
        this.movies = new ArrayList<Movie>(); //List initialization
    } //Constructor

    Scanner in = new Scanner(System.in);



    //adds User in the Platfrom's user List 
    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }

    //adds a Pre-made Movie in the Platfrom's List made in Main
    public void addMovie(Movie m){
        
        movies.add(m);
    }
    //addMovie() method: Adds the user's movie to the platform's movie list 
    //METHOD OVERLOADING
    public void addMovie(){

        System.out.print("Title: ");
        String title = in.nextLine();

        System.out.print("Year: ");
        int year = Integer.parseInt(in.nextLine());

        System.out.print("Director: ");
        String director = in.nextLine();

        List<String> genres = new ArrayList<>();
        for(;;){
            System.out.print("Add Genres or leave blank to end: ");
            String g = in.nextLine();
            if(!g.isEmpty()){
                genres.add(g);
            }else{
                break;
            }
        }
        boolean flag = false;
        for (Movie m : movies) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Movie already exists.");
                flag = true;
            }
        }
        if (!flag) {Movie newMovie = new Movie(title, year,genres, director);
            movies.add(newMovie);
            System.out.println("Movie added successfully.");
        }


    }

//used in dataloader to map reviews to users and movies
    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; //this User does not exist
    }
    
    public Movie findMovieByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null; // this movie does not exist
    }



//------------------------------------------------------------------------------------------------------------
     public void addCurrentUser(){//add user to userlist before changing user
        users.add(currentUser);
    }


    public void login(){ //login() method makes a User instance in the LOGIN MENU, according to the user's choice
        boolean successful = false; //Boolean flag used to break the while loop
        while(!successful) {
            System.out.println("--- LOGIN ---\n1.Guest\n2.Verified User\n3.Critic"); //USER LOGIN MENU options 
            System.out.print("Choose (1-3): ");
            int choice = Integer.parseInt(in.nextLine());
            System.out.print("Username: ");
            String name = in.nextLine();
            if(findUserByUsername(name)==null){

            
                System.out.println("----------------------------------------------------------------------------------------------------");
                if (choice == 1) { //1. BASIC GUEST USER - ONLY HAS USERNAME
                    currentUser = new User(name);
                    successful = true; //Breaks the while loop once the User object is created with user input data
                } else if (choice == 2) {//2. VERIFIED USER HAS EMAIL TO LOGIN HIS USER ACCOUNT AND USERNAME
                    System.out.print("Email: ");
                    String mail = in.nextLine();
                    currentUser = new VerifiedUser(name, mail);
                    successful = true;//Breaks the while loop once the User object is created with user input data
                } else if (choice == 3) { //3. A CRITIC USER HAS AN EMAIL TO LOG INTO THEIR ACCOUNT AND A UNIQUE CODE TO DISTINGUISH IT AS A PROFESSIONAL ACCOUNT
                    System.out.print("Email: ");
                    String mail = in.nextLine();
                    System.out.print("Code: ");
                    String code = in.nextLine();
                    currentUser = new Critic(name, mail, code);
                    successful = true;//BREAKS WHILE LOOP ONCE USER OBJECT IS CREATED WITH USER INPUT DATA
                }
            }else{
                System.out.println("User with this name already exists");
            }
        }
    }

//------------------------------------------------------------------------------------------------------------    
    public boolean search() {//SEARCH MOVIE, MAIN MENU OPTION. BOOLEAN METHOD
        System.out.println("Searching...\nWith Movie Title (TYPE 1)\nWith Director Name (TYPE 2)"); //OPTION 1: SEARCH BY TITLE, OPTION 2: SEARCH BY DIRECTOR NAME
        
		System.out.print("Choose (1-2): ");
        int ans = Integer.parseInt(in.nextLine());
        boolean found = false; //BOOLEAN FLAG SET TO FALSE IF MOVIE IS NOT FOUND
        if (ans == 1) {
            System.out.print("Give Movie Title:  ");
            String name = in.nextLine();
            System.out.println("Results for: " + name);
            for (Movie obj : movies) {
                if (obj.getTitle().toLowerCase().contains(name.toLowerCase())) { // THE TITLE CONTAINS THE USER INPUT STRING
                    System.out.println("(Index: " + movies.indexOf(obj) + ") " + obj); //movie display
                    found = true;//MOVIE FOUND
                }
            }
        } else {
            System.out.print("Give Director Name:  ");
            String name = in.nextLine();
            System.out.println("Results for: " + name);
            for (Movie obj : movies) {
                if (obj.getDirector().toLowerCase().contains(name.toLowerCase())) {// THE TITLE CONTAINS THE USER INPUT STRING
                    System.out.println("(Index: " + movies.indexOf(obj) + ") " + obj); //movie display
                    found = true;//MOVIE FOUND
                }
            }
        }
        if(!found){
            System.out.println("No Movies Found"); //NOT FOUND
        }
        return found;//RETURN TRUE/FALSE WHETHER THE MOVIE WAS FOUND OR NOT
    }

//---------------------------------------------------------------------------------------------------------
    //specificMovie(int ...) method displays/adds reviews, displays related movies
    public void specificMovie(int index){ 

        movies.get(index).printDetails(); //DISPLAY DETAILS OF THE MOVIE REQUESTED BY THE USER, BASED ON THE INDEX OF THE MOVIE LIST

        System.out.println("1.Movie Reviews\n2.Show Related Movies\n3.Exit"); 
        System.out.print("Choose (1-3): ");
        int ans = Integer.parseInt(in.nextLine());

        if(ans==1){ // OPTION 1: DISPLAY/ ADD REVIEWS
             System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("\n----- Movie Reviews -----");
            movies.get(index).printReviews();//DISPLAY REVIEWS
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.print("Add Review (YES/NO): ");
            String ans2 = in.nextLine();
            if(ans2.equalsIgnoreCase("yes")){
                addReview(index); //ADDS REVIEW, CALLS THE addReview METHOD, IMPLEMENTED BELOW:
            }
        }else if(ans==2){ //OPTION 2: DISPLAY RELATED MOVIES
            movies.get(index).printRelatedMovies(movies); //DISPLAY RELATED MOVIES, BASED ON THE PLATFORM'S MOVIE LIST
            System.out.println("----------------------------------------------------------------------------------------------------");
        }else{}//exit
    }


//-----------------------------------------------------------------------------------------------------------------
    //addReview(int ...) method ADDS A REVIEW TO THE MOVIE'S REVIEW LIST AND TO THE PLATFORM USER'S REVIEW LIST
    public void addReview(int index){
            System.out.print("Give Your Review (1-10): ");
            int rating = Integer.parseInt(in.nextLine());

            System.out.print("Add comment or leave blank: ");
            String com = in.nextLine();

            if(currentUser instanceof Critic){//check if the user is a critic
                Review r = new CriticReview(currentUser,rating, com, movies.get(index));//creation of a professional review
                movies.get(index).addReview(r);//addition to the movie of the movie list
                currentUser.addReview(r);//addition to the user

            }else{
                Review r = new BasicReview(currentUser, rating, com, movies.get(index));//creation of a basic review
                movies.get(index).addReview(r);//addition to the movie of the movie list
                currentUser.addReview(r);//addition to the user
            }
    }

//-----------------------------------------------------------------------------------------------------------------
    // Creation of HashMap<Genre, List<Movie>> where key is genre and value is the list of movies belonging to that genre
    public Map<String, List<Movie>> getGenreMap() {
        Map<String, List<Movie>> genreMap = new HashMap<>();
        
        for (Movie m : movies ) {
            for (String genre : m.getGenres()) {//each genre of the movie
                if (!genreMap.containsKey(genre)) {//If the genre is encountered for the first time, adds it
                    genreMap.put(genre, new ArrayList<>());
                }
                genreMap.get(genre).add(m);// each movie is added to the list of its corresponding genre
            }
        }
        return genreMap;
    }


    //displays to the user the movies of the genre provided by the user
    public void specificGenre(String ans,Map<String, List<Movie>> genresMap){
        if (genresMap.containsKey(ans)) {
            List<Movie> templist = genresMap.get(ans);
            System.out.print("Filter by Number of Reviews and Rating? (YES/NO): ");
            String ans2 = in.nextLine();
            System.out.println("----------------------------------------------------------------------------------------------------");
            if(ans2.equalsIgnoreCase("yes")){
                templist = getSortedMovies(templist,comp1);//sorting method is defined below
                displayMoviesWithFilters(templist);//method to display movies based on rating and number of reviews is defined below
            }else if (ans2.equalsIgnoreCase("no")){
                for(Movie obj : templist){
                        System.out.println(obj); 
                    }	
            }
        }
    }


//-----------------------------------------------------------------------------------------------------------------
    //method to display movies based on rating and number of reviews
    public void displayMoviesWithFilters(List<Movie> movielist){
        System.out.print("Give number of reviews: ");
		int n = Integer.parseInt(in.nextLine());
		System.out.print("Give rating: ");
		double x = Double.parseDouble(in.nextLine());
        int i =0;
		for(Movie obj : movielist){
			if(obj.getAverageRating()>x && obj.getNumberOfReviews()>n){ //(with at least n reviews and average rating > x)
				System.out.println(obj); 
				i++;
			}
						
		} 
        if (i==0){
			System.out.println("No Movies Found");
		}

        
    }
//---------------------------------------------------------------------------------------------------------
    public List<Movie> getSortedMovies(List<Movie> templist,Comparator<Movie> comp) {
        Collections.sort(templist, comp); // use of sort(List, Comparator) method to sort the movies in the list using different comparators
        return templist;//returns the list sorted
    }

//-----------------------------------------------------------------------------------------------------------
    public void listMovies(){ //displays all movies in the platform's list
        for(Movie obj : movies){
            System.out.println(obj);
        }
    }
//-----------------------------------------------------------------------------------------------------------
    //profile() method calls the User's printDetails() method to print his profile's data
    public void profile(){
        currentUser.printDetails();
    }

//-----------------------------------------------------------------------------------------------------------
    //getTopMovies() method displays the top 5 movies per genre

    public List<Movie> getTopMovies() {
        Map<String, List<Movie>> genresMap = getGenreMap();
        System.out.println("Available Genres:");
		for (String genre : genresMap.keySet()) {
			System.out.println("- " + genre);
	    }
        System.out.println("Choose a genre: ");
		String genre = in.nextLine();
        List<Movie> topfive = genresMap.get(genre);
        return getSortedMovies(topfive,comp1);
        
    }
//-----------------------------------------------------------------------------------------------------------
//getHighlyLiked returns a list of movies that meet the criteria
    public List<Movie> getHighlyLiked() {
        List<Movie> result = new ArrayList<>();
        for (Movie m : movies) {
            List<Review> reviews = m.getReviews();
            if (reviews.isEmpty()) continue;
            int liked = 0;
            for (Review r : reviews) {
                if (r.getRating() >= 7) {
                    liked++;
                }
            }
            double percentageLiked = (double) liked / reviews.size();
            if (percentageLiked >= 0.8) {
                result.add(m);
            }
            result.sort(comp1); 
        }
        return result;
    }
//-----------------------------------------------------------------------------------------------------------
//AverageRatingPerUser() displays the average rating of each user

    public void AverageRatingPerUser() {
        for (User u : users) {
            List<Review> reviews = u.getReviews();
            if (reviews.isEmpty()) {
                System.out.println(u.getUsername() + " has not submitted any reviews.");
            } else {
                int total = 0;
                for (Review r : reviews) {
                    total += r.getRating();
                }
                double avg = (double) total / reviews.size();
                System.out.printf("%s Average Rating: %.1f%n", u.getUsername(), avg);
            }
        }
    }


//-----------------------------------------------------------------------------------------------------------
//getters

    //getter for the current user
    public User getCurrentUser(){
        return currentUser;
    }


    //gets the list of movies that platform uses
    public List<Movie> getMovies(){
        return movies;
    }

    //gets the list of users that platform uses
    public List<User> getUsers(){
        return users;
    }


}