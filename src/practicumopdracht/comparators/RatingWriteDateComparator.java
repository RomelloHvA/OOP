package practicumopdracht.comparators;

import practicumopdracht.models.Review;

import java.util.Comparator;

public class RatingWriteDateComparator implements Comparator<Review> {
    @Override
    public int compare(Review o1, Review o2) {
        int ratingCompare = Integer.compare((int) o2.getRating(), (int) o1.getRating());
        if (ratingCompare == 0){
            return o2.getWriteDate().compareTo(o1.getWriteDate());
        }
        return ratingCompare;
    }
}
