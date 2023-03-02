package practicumopdracht.controllers;

import practicumopdracht.MainApplication;
import practicumopdracht.views.ReviewView;
import practicumopdracht.views.View;

public class ReviewController extends Controller{

    private ReviewView reviewView;
    private Controller animeSelectorController;



    public ReviewController(){
        this.reviewView = new ReviewView();
        this.reviewView.getReturnButton().setOnMouseClicked(mouseEvent -> handleReturnButtonClick());
    }
    public View getView() {
        return this.reviewView;
    }

    private void handleReturnButtonClick(){
        this.animeSelectorController = new AnimeSelectorController();
        MainApplication.switchController(animeSelectorController);

    }



}
