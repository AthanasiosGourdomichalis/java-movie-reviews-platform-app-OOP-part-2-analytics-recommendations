public class VerifiedUser extends User { //Subclass of the User class. A verified User is-a User with a linked web app account and a registered email
	protected String email;

	public VerifiedUser (String username, String email) {
		super(username);
		this.email = email;
	}//Constructor: calls the superclass User constructor for the username
	
	public VerifiedUser(){

	}
    
	public void setEmail(String email){
		this.email=email;
	}
	public void printDetails() {
        System.out.println("[Verified] User: " + username + " (email: " + email + ")");
		System.out.println("Reviews submitted: " + reviews.size());
    }//Overrides the printDetails method of the superclass User

}
