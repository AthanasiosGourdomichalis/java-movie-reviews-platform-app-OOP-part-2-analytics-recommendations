import java.util.Comparator; //The class implements Java's Comparator<T> interface based on each movie's release year (using <Movie> to handle instances of the Movie class)

public class MovieAverageRatingComparator implements Comparator<Movie> {
    public int compare(Movie m1, Movie m2) {
        if ( m1.getAverageRating() == (m2.getAverageRating())) { //Calls getAverageRating()
            return 0;
        } else if (m1.getAverageRating() > (m2.getAverageRating()) ) {
            return -1; //Descending sort based on average rating (double)
        } else {
            return 1;
        }
    }
} 