package practicumopdracht.models;

import java.time.LocalDate;

public class Review {
    private Anime belongsTo;
    private String writtenBy;
    private LocalDate writeDate;
    private double rating;
    private String review;
    private boolean recommended;
}
