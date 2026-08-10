import java.util.*;

public class Recommender{           //Movie Recommender System. 
    public Recommender(){

    }// default Constructor

    public List<Movie> userMovies(User u){
        List<Movie> watched = new ArrayList<Movie>(); 
        for(Review r :u.getReviews()){
            watched.add(r.getMovie());
        }
        return watched;
    }

//-------------------------------------------------------------------------------------------------------------------
//The Recommender class provides methods for finding each user's preferences


//Collaborative Filtering (User-Based)
//Returns a list of recommended movies for a user based on the ratings of others with a similar profile
    public List<Movie> recommendByUserSimilarity(User currentUser, Platform platform){ 

        List<User> recommendedUsers = new ArrayList<User>();    //similar users
        List<Movie> recommendedMovies = new ArrayList<Movie>(); //similar movies recommended

        for (User otheruser : platform.getUsers()){ //comparison of all users with our user
            if(!currentUser.equals(otheruser)){ // for each user in the platform's user list who is different from the current user

            Map<Movie, int[]> userReviewsMap = new HashMap<Movie, int[]>(); //HashMap with keys being the movies rated by both users. Value is an int array [2] containing their respective ratings.

            for(Review myReview : currentUser.getReviews()){// comparing each review of our user with each review of the other user

                for(Review hisReview : otheruser.getReviews()){
                    if ( (myReview.getMovie()).equals(hisReview.getMovie()) ){ //if a movie is found that both users have rated

                      int[] reviewcouple = {myReview.getRating(), hisReview.getRating()}; //first position: rating of our user's movie. second position: rating of the other user's movie
                      userReviewsMap.put(myReview.getMovie(),reviewcouple); //movie, ratings array
                    } //if
                }//for

            }//for

            
            double MSE; //(mean squared error) average rating difference
            int sum = 0;

            for(Movie m : userReviewsMap.keySet()){
                int diff = (userReviewsMap.get(m)[0] - userReviewsMap.get(m)[1]);// calculation of difference
                sum = sum + (diff*diff); //sum of squared differences
            }

            MSE = (double) sum / userReviewsMap.size();
            if(MSE< 4.0){ //User similarity. They like similar movies. (mean squared error) < 4
                recommendedUsers.add(otheruser); //addition to similar users
            }

            

            }//if

        }//for


        for(User u : recommendedUsers){ //for each similar user
                
            u.getReviews().sort(Comparator.comparing(Review::getRating).reversed()); //descending sort of each user's review list based on rating
        }//this is how we find the top-rated movies of each similar user

            
        for(User otherUser : recommendedUsers){
            for(Review r1 : otherUser.getReviews()){//each review of the other user in the review list
                if(!this.userMovies(currentUser).contains(r1.getMovie()) &&  (r1.getRating()>=7)){ //if our user has NOT watched the movie and the similar user has rated it high
                            recommendedMovies.add(r1.getMovie());// the movie is added to the recommended movies
                }

            }
            
        }

        


        return recommendedMovies;


    }//recommendByUserSimilarity





//-------------------------------------------------------------------------------------------------------------------
//Content-Based Filtering 
//Returns a list of recommended movies for a user based on their previous ratings

    public List<Movie> recommendByContent(User currentUser, Platform platform){

        Map<String, List<Movie>> genresMap = platform.getGenreMap(); //Platform's HashMap containing <genre, list of movies in this genre>
    
        Map<String, Double> userAverageGenres = new HashMap<>(); //User's HashMap containing <genre, user's average rating for movies in this genre>
        for(String genre : genresMap.keySet()){
            userAverageGenres.put(genre ,0.0);
        }//initialize. Average rating for each genre is initially 0


        //------------------------------------------

        Map<String, List<Review>> userGenreReview = new HashMap<>(); //HashMap containing <genre, List of user's Reviews for movies in this genre>
        for(String genre : genresMap.keySet()){ //for each default genre
            for(Review myReview : currentUser.getReviews()){ //each review of the user
                if( myReview.getMovie().getGenres().contains(genre)){ 
                    if(!userGenreReview.containsKey(genre)){
                        userGenreReview.put(genre, new ArrayList<>()); //if the genre entry does not exist, we add it
                    }//nested if
                    userGenreReview.get(genre).add(myReview);
                }//outside if

            }//nested for

        }//outside for


        //------------------------------------------

        for(String myGenre : userGenreReview.keySet()){ //for each genre in which they have rated movies, calculate the AVERAGE RATING PER GENRE
            int sum = 0;
            for(Review myReview : userGenreReview.get(myGenre)){

                sum = sum + myReview.getRating(); //calculation of sum
            }

            double genreAvg = (double) sum / userGenreReview.get(myGenre).size() ; //calculation of average rating for this genre

            userAverageGenres.put(myGenre, genreAvg); // we override the previous entry that was 0, inserting the average
        }


        //------------------------------------------

        List<Movie> userMovies = new ArrayList<Movie>(); //movies rated and watched by our user
        for(Review myReview : currentUser.getReviews()){
            userMovies.add(myReview.getMovie());
        }


        List<Movie> similiarMovies = new ArrayList<Movie>();    //similar movies recommended to the user
        for(String myGenre : userAverageGenres.keySet()){ // each Genre rated by the user with an average above
            for(Movie m : genresMap.get(myGenre)){ // each default movie in the List that is the value of the platform's HashMap, belonging to the genre rated by the user
                if(   !userMovies.contains(m) && (userAverageGenres.get(myGenre)>=7.0)  && (m.getAverageRating()>=7.0 )){  //the user HAS NOT WATCHED the movie and its genre is highly rated
                    similiarMovies.add(m); //addition to similar recommended movies
                }
            }
        }
       
        for(Movie m : this.userMovies(currentUser)){
            if(similiarMovies.contains(m)){
                similiarMovies.remove(m);
            }
        }

        return similiarMovies;


    }//recommendedByContent

}//Recommender