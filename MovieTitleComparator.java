import java.util.Comparator;

public class MovieTitleComparator implements Comparator<Movie> { //Implements Java's Comparator<T> interface based on each movie's release year (using <Movie> to handle Movie class instances)
    public int compare(Movie m1, Movie m2) {
        if ( m1.getTitle().compareTo(m2.getTitle()) == 0) { //Uses String compareTo with getTitle for lexicographical comparison of movie titles. Utilizes the getTitle() method.
            return 0;
        } else if (m1.getTitle().compareTo(m2.getTitle()) > 0) {
            return 1; //Alphabetical sort (ascending String sort)
        } else {
            return -1;
        }
    }
}  