import java.util.*;
import java.io.*;
public class DataLoader{
    Platform platform;

    public DataLoader(Platform platform) {//takes as an argument the platform processed by main.java
        this.platform = platform;
    }
    private Movie m;
    private User u;
    private Review r;
	void loadFile(String data){
		
		BufferedReader reader= null;

		try{
			
			reader = new BufferedReader (new FileReader(data));
			String line = reader.readLine();
			
			while (line!=null){
				StringTokenizer st = new StringTokenizer(line,",");
				String token = st.nextToken();
				if (token.equals("MOVIE")){
					// MOVIE#Iron Man#2008#Jon Favreaus#Action#Sci-Fi
					m = new Movie();
                    m.setTitle(st.nextToken());
                    m.setYear(Integer.parseInt(st.nextToken()));
                    m.setDirector(st.nextToken());
                    
                    
                    List<String> genres = new ArrayList<>();
                    while (st.hasMoreTokens()) {//If genres exist, creates a list and sets them to the movie
                        genres.add(st.nextToken());
                    }
                    m.setGenres(genres);
                    
                    platform.addMovie(m); // Add the movie to the platform
				}
				else if (token.equals("USER")){

                    String token2 = st.nextToken();
                    if(token2.equals("User")){// USER#User#name
                        u = new User();
                        u.setUsername(st.nextToken());
                        platform.addUser(u);


                    }
                    else if(token2.equals("VerifiedUser")){// USER#VerifiedUser#name#mail
                        u = new VerifiedUser();
                        u.setUsername(st.nextToken());
                        ((VerifiedUser)u).setEmail(st.nextToken());
                        platform.addUser(u);

                    }
                    else if(token2.equals("Critic")){// USER#CriticUser#name#mail#code
                        u = new Critic();
                        u.setUsername(st.nextToken());
                        ((VerifiedUser)u).setEmail(st.nextToken());
                        ((Critic)u).setCode(st.nextToken());
                        platform.addUser(u);

                    }
					
				}
                else if (token.equals("REVIEW")){
                    String token2 = st.nextToken();
                    String username = st.nextToken();
                    String movieTitle = st.nextToken();
                    int rating = Integer.parseInt(st.nextToken());
                    String comment = st.hasMoreTokens() ? st.nextToken() : ""; // checks if a comment exists
                
                // finds the corresponding user and movie of the review
                    User user = platform.findUserByUsername(username);
                    Movie movie = platform.findMovieByTitle(movieTitle);
                
                if (user != null && movie != null) {
                    if(token2.equals("BasicReview")){
                        r = new BasicReview(user, rating, comment, movie);
                        user.addReview(r); // Adds the review to the user's review list
                        movie.addReview(r);// Adds the review to the movie's review list
                    }
                    else if(token2.equals("CriticReview")){
                        r = new CriticReview(user, rating, comment, movie);
                        user.addReview(r); 
                        movie.addReview(r);
                    }
                } else {
                    System.err.println("Could not create review - user or movie not found");
                    
                }

				
				
            }
            line = reader.readLine();
			} // while
			
			reader.close();
		} // try
		
		catch (IOException e){
			System.err.println("Error Reading File...");
		}
		
	} 
}