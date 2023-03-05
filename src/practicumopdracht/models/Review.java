package practicumopdracht.models;

import java.time.LocalDate;

public class Review {
    private Anime belongsTo;
    private String writtenBy;
    private LocalDate writeDate;
    private double rating;
    private String review;
    private boolean recommended;



    public Review(Anime belongsTo, String writtenBy, LocalDate writeDate, double rating, String review, boolean recommended) {
        this.belongsTo = belongsTo;
        this.writtenBy = writtenBy;
        this.writeDate = writeDate;
        this.rating = rating;
        this.review = review;
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return "For anime: " + belongsTo.getName() +
                "\nWritten by: " + writtenBy +
                "\nWrite date: " + writeDate +
                "\nRating: " + rating +
                "\nReview: " + review +
                "\nRecommended=" + recommended;
    }
}
