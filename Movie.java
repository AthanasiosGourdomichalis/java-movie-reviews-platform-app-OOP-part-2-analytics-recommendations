import java.util.*;
public class Movie implements Printable {
	//Implements the Printable interface

	private String title;
	private int year;
	private String director;
	private List<String> genres;
	private List<Review> reviews;

	public Movie(){
		this.reviews = new ArrayList< Review >();
	}

	public Movie(String title, int year, List<String> genres, String director) {
		this.title = title;
		this.year = year;
		this.genres = new ArrayList<>(genres);
		this.director = director;
		this.reviews = new ArrayList< Review >();

	}//Public constructor for the public class Movie 


//-----------------------------------------------------------------------------------------------------------------
//Add review list elements


	//Add review instance element in movie's List<Review> reviews
	public void addReview(Review rev) {
		reviews.add(rev);
	}

//-----------------------------------------------------------------------------------------------------------------
//Getters

	//get movie's title for alphabetical (String) sorting
	public String getTitle(){
		return title;	
	}

	//get movie's year for arithmetic (int) sorting
	public int getYear(){
		return year;
	}

	//get movie's director for listing, searching, filtering the director's movies and finding Related Movies,
	public String getDirector(){
		return director;
	}

	//get movie's genres for Related Movies
	public List<String> getGenres() {
		return genres;
	}

	public List<Review> getReviews() {
    	return reviews;
	}	

	//get movie's average rating for arithmetic (double) sorting. Rounds to one decimal place for better comparator comparison and cleaner rating display
	public double getAverageRating() {
		if (reviews.isEmpty()){ 
			return 0; //no ratings.
		}
			int sum = 0; 

			for (Review rev : reviews) {
				sum += rev.getWeightedRating(); //Calculates the sum
			}
			
		double d1 = (double) sum / reviews.size(); //Casts to double to prevent precision loss and calculates the average 
		String s = String.format("%.1f", d1); //Formats the double value to a String with one decimal place
		double d2 = Double.parseDouble(s); //Indirect rounding to one decimal place via double -> String -> double conversion
		return (d2>10.0? 10.0 : d2); //Ternary operator
	}


	//get movie's number of reviews
	public int getNumberOfReviews(){
		return reviews.size();
	}


//-----------------------------------------------------------------------------------------------------------------
//Setters
	void setTitle(String title){
		this.title=title;
	}
	void setYear(int year){
		this.year=year;
	}
	void setGenres(List<String> genres){
		this.genres = new ArrayList<>(genres);
	}
	void setDirector(String director){
		this.director=director;
	}


//-----------------------------------------------------------------------------------------------------------------
//toString(), printReviews(), printRelatedMovies() and printDetails()

	//Implements toString to print key properties of Movie instances using System.out.println
	public String toString(){
		return "Title: " + this.title + " (" + this.year + ") directed by: " + this.director + " - " + this.getAverageRating();
	}

	//printReviews method, used to display existing reviews before the user submits a new one
	public void printReviews(){
		for (Review rev : reviews) {
			rev.printDetails(); 
		}
	} 

	//printRelatedMovies method, used to display related movies during a movie search
	public void printRelatedMovies(List<Movie> allMovies) {
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("Related Movies:");
		boolean found = false;

		for (Movie m : allMovies) { //Iterates through every movie in the platform's movie list
			if (!m.equals(this)) {
				for (String genre : genres) {
					if (m.getGenres().contains(genre) || m.getDirector().toLowerCase().contains(director.toLowerCase())) { //Checks if the movie's genre is included in the platform's list of genres 
						System.out.println("- " + m.toString());														   //Or checks if the user-provided director name is contained in the full director name (case-insensitive)
						found = true;
						break; //Matches any movie that shares at least one genre or has the same director
					}
				}
			}
		}

		if (!found) {
			System.out.println("No related movies found.");
		}
	}



	//Implements the public printDetails() method defined in the Printable interface for detailed display of all properties.
	public void printDetails() {
			System.out.println("Title: " + title);
			System.out.println("Year: " + year);
			System.out.println("Director: " + director);
			System.out.println("Genres: " + genres);
			System.out.println("Average Rating: " + getAverageRating());
			System.out.println("----------------------------------------------------------------------------------------------------");
	}


//-----------------------------------------------------------------------------------------------------------------
// Implementation of equals(...) and hashCode(), for comparing movies based on title and release year, for use in structures e.g. HashSet<Movie> / Map<Movie,...>
 @Override
 public boolean equals(Object obj){
	if (this== obj) return true;
	if(!(obj instanceof Movie))return false;

	Movie other = (Movie) obj;
	return this.title.equals(other.title) && (this.year == other.year);
 }
 
 @Override 
 public int hashCode() {
	return Objects.hash(title,year);
 }



 }//Movie