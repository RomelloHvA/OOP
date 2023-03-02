package practicumopdracht.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import practicumopdracht.MainApplication;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

public class AnimeSelectorController extends Controller{

    private AnimeSelectorView animeSelectorView;
    private ReviewController reviewController;

    public AnimeSelectorController() {
        this.animeSelectorView = new AnimeSelectorView();
        this.animeSelectorView.getReviewsButton().setOnMouseClicked(mouseEvent -> handleReviewButtonClick());
        this.animeSelectorView.getNewButton().setOnMouseClicked(mouseEvent -> handleAddNewAnime());
    }

    @Override
    public View getView() {
        return this.animeSelectorView;
    }

    private void handleReviewButtonClick(){
        this.reviewController = new ReviewController();
        MainApplication.switchController(reviewController);
    }

    private void handleAddNewAnime(){
        String errorMessage = "Please enter: \n";
        if (isEmptyAnimeName()){
            errorMessage += "Anime name";
            Alert nameEmpty = new Alert(Alert.AlertType.ERROR, errorMessage);
            animeSelectorView.getAnimeNameTextField().setBorder(Border.stroke(Color.ORANGE));
            nameEmpty.show();
        }
        else {
            Alert nameGood = new Alert(Alert.AlertType.CONFIRMATION,"Confirm new Anime?");
            nameGood.show();
        }

    }
    private boolean isEmptyAnimeName(){
        return isEmptyTextField(animeSelectorView.getAnimeNameTextField());
    }
}
