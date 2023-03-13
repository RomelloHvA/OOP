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
        return String.format("Author: %s\nWrite Date: %s\nRating: %.1f\n",writtenBy,writeDate,rating);
    }

    public String toStringConfirmMessage() {
        return "For anime: " + belongsTo.getName() +
                "\nWritten by: " + writtenBy +
                "\nWrite date: " + writeDate +
                "\nRating: " + rating +
                "\nReview: " + review +
                "\nRecommended=" + recommended;
    }

    public void setBelongsTo(Anime belongsTo) {
        this.belongsTo = belongsTo;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public void setWriteDate(LocalDate writeDate) {
        this.writeDate = writeDate;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public Anime getBelongsTo() {
        return belongsTo;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public LocalDate getWriteDate() {
        return writeDate;
    }

    public double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public boolean isRecommended() {
        return recommended;
    }
}
