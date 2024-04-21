public class MovieReview implements Comparable<MovieReview>{
    Integer userRating;
    Integer editorRating;

    MovieReview(Integer userRating, Integer editorRating){
        this.userRating = userRating;
        this.editorRating = editorRating;
    }

    @Override
    public int compareTo(MovieReview o) {
        return this.editorRating - o.editorRating;
    }
}
