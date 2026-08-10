public class Critic extends VerifiedUser {//Subclass of the User class. Represents a verified user (is-a User relationship), which includes an email address and a passcode/credential identifying them as a professional worker
	protected String code;
    public Critic(String username, String email, String code) {
        super(username, email);
		this.code = code;
 	} //Critic constructor. Utilizes polymorphism/inheritance for username and email as they are superclass properties

    public Critic(){

    }

    public void setCode(String code){
        this.code= code;
    }

    public void printDetails() {
        System.out.println("[Critic] User: " + username + " (email: " + email + ")");
        System.out.println("Critic Code: " + code);
        System.out.println("Reviews submitted: " + reviews.size());
    }//Overrides the printDetails method of User to display that the user is a Critic

}