package practicumopdracht.controllers;

import javafx.scene.Scene;
import practicumopdracht.MainApplication;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

public class AnimeSelectorController extends Controller{

    private AnimeSelectorView animeSelectorView;
    private ReviewController reviewController;

    public AnimeSelectorController() {
        this.animeSelectorView = new AnimeSelectorView();
        this.animeSelectorView.getReviewsButton().setOnMouseClicked(mouseEvent -> handleReviewButtonClick());
    }

    @Override
    public View getView() {
        return this.animeSelectorView;
    }

    private void handleReviewButtonClick(){
        reviewController = new ReviewController();
        MainApplication.switchController(reviewController);
    }
}
