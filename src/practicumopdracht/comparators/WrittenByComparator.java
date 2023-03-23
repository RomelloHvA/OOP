package practicumopdracht.comparators;

import practicumopdracht.models.Review;

import java.util.Comparator;

public class WrittenByComparator implements Comparator<Review> {

    @Override
    public int compare(Review o1, Review o2) {
        return o2.getWrittenBy().compareTo(o1.getWrittenBy());
    }
}
