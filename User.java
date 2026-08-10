import java.util.*;
public class User implements Printable { //The User class implements the Printable interface: represents a standard user of the company's web application without a linked account
	protected String username;
	protected List<Review> reviews;

	public User(String username) {
		this.username = username;
		this.reviews = new ArrayList<Review>(); //Creates a list of the user's reviews
	}  //constructor of User Class

	public User() {
		this.reviews = new ArrayList<Review>(); //creates a list with the user's reviews
	} 

	public void addReview(Review r) {
		reviews.add(r);
	} //adds the User's review to their list


	public String getUsername() {
		return username;
	} //getter of class User, returns the user's name

	public void setUsername(String username){
		this.username= username;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}

	public void printDetails() {
		System.out.println("[Guest] User: " + username);
		System.out.println("Reviews submitted: " + reviews.size());
	} //displays the User's name and the number of reviews they have made



	 @Override
 public boolean equals(Object obj){
	if (this== obj) return true;
	if(!(obj instanceof User))return false;

	User other = (User) obj;
	return this.username.equals(other.username);
 }
 
 @Override 
 public int hashCode() {
	return Objects.hash(username);
 }




}


