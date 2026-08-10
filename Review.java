import java.util.List;

public abstract class Review implements Printable { //The abstract Review class implements the Printable interface. It is abstract due to the abstract getWeightedRating method
	protected User user;
	protected int rating;
	protected String comment;
	protected Movie movie;

	public Review(User user, int rating, String comment, Movie movie) {
		this.user = user;
		this.rating = rating;
		this.comment = comment;
		this.movie = movie;
	}//Constructor

	public abstract int getWeightedRating(); //Abstract method: declared here, but implemented in subclasses

	public void printDetails() { //Implements the printDetails method defined in the Printable interface
		System.out.println(user.getUsername() + " rated " + movie.getTitle() + " with " + rating + "/10");
		if (comment != null && !comment.isEmpty())
			System.out.println("Comment: " + comment);
	}

	public Movie getMovie(){
		return movie;
	}
	
	public int getRating() {
    	return rating;
	}
}

