package practicumopdracht.data;

import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;

import java.util.ArrayList;
import java.util.List;

public abstract class ReviewDAO implements DAO<Review> {

    private ArrayList<Review> reviewArrayList;

    public ReviewDAO(){
        reviewArrayList = new ArrayList<>();
    }

    public List<Review> getAllFor(Anime anime){
        List<Review> reviewList = new ArrayList<>();

        for (Review review : reviewArrayList) {
            if (review.getBelongsTo() == anime) {
                reviewList.add(review);
            }
        }
        return reviewList;
    }
    @Override
    public List<Review> getAll() {
       return reviewArrayList;
    }

    @Override
    public void addOrUpdate(Review review) {
        if (!reviewArrayList.contains(review)){
            reviewArrayList.add(review);
        }
    }

    @Override
    public void delete(Review review) {
        reviewArrayList.remove(review);
    }

    @Override
    public abstract boolean save();

    @Override
    public abstract boolean load();
}
