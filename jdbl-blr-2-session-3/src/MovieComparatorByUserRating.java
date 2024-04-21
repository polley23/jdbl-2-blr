import java.util.Comparator;

public class MovieComparatorByUserRating implements Comparator<MovieReviewWithComparator> {
    @Override
    public int compare(MovieReviewWithComparator o1, MovieReviewWithComparator o2) {
        return o1.userRating-o2.userRating;
    }
}
