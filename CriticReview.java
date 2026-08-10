public class CriticReview extends Review {
	public CriticReview (User user, int rating, String comment, Movie movie) {
		super(user, rating, comment, movie); 
	}//Subclass Constructor utilizing inheritance; invokes the superclass (Review) Constructor using the super() keyword

	public int getWeightedRating() {
		return (int) Math.round(rating * 1.2); //Higher weight logic
	}//Overrides the getWeightedRating method from the superclass Review to implement specialized behavior. Reviews submitted by Verified and Critic users are assigned a higher weight factor
}
