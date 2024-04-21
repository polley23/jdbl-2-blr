import java.util.Comparator;

public class MovieComparatorByEditorRating implements Comparator<MovieReviewWithComparator> {
    @Override
    public int compare(MovieReviewWithComparator o1, MovieReviewWithComparator o2) {
        return o2.editorRating-o1.editorRating;
    }
}
