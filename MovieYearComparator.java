import java.util.Comparator;

public class MovieYearComparator implements Comparator<Movie> { //Implements Java's Comparator<T> interface based on release year (using <Movie> to handle Movie class instances)
    public int compare(Movie m1, Movie m2) {
        if ( m1.getYear() == (m2.getYear())) { //Calls getYear() method
            return 0;
        } else if (m1.getYear() > (m2.getYear()) ) { 
            return -1; //Descending sort based on year (int)
        } else {
            return 1;
        }
    }
} 