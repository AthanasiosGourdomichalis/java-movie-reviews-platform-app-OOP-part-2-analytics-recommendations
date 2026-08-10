public class BasicReview extends Review { //Subclass of Review. Implements the getWeightedReview method defined in the abstract class Review

	public BasicReview(User user, int rating, String comment, Movie movie) {
		super(user, rating, comment, movie); 
	}//Inheritance-based Constructor. Invokes the superclass Constructor (Review) using the super() keyword
	public int getWeightedRating() {
		return rating;
	}//Getter method
}