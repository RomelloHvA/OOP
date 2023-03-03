package practicumopdracht.controllers;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import practicumopdracht.MainApplication;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

import java.time.LocalDate;

public class AnimeSelectorController extends Controller{

    private AnimeSelectorView animeSelectorView;
    private ReviewController reviewController;
    private Alert alert;
    private String errorMessage;
    private Alert confirmNewAnimeAlert;

    public AnimeSelectorController() {
        this.animeSelectorView = new AnimeSelectorView();
        this.alert = new Alert(Alert.AlertType.WARNING);
        this.confirmNewAnimeAlert = new Alert(Alert.AlertType.CONFIRMATION,"Confirm new Anime?");
        errorMessage = "please enter valid\n";

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
        boolean emptyAnimeName = isEmptyAnimeName();
        boolean validReleaseDate = isValidReleaseDate();

        if (emptyAnimeName || validReleaseDate == false){
            alert.setContentText(errorMessage);
            alert.show();
            errorMessage = "Please enter valid:\n";
        } else {
            confirmNewAnimeAlert.show();
        }


        }

    private boolean isEmptyAnimeName(){
        boolean isEmpty = isEmptyTextField(animeSelectorView.getAnimeNameTextField());
        if (isEmpty){
            addErrorMessageAnimeName();
        }
        return isEmpty;
    }
    private boolean isValidReleaseDate(){
        LocalDate releaseDateInput = animeSelectorView.getReleaseDatePicker().getValue();
        boolean isValidReleaseDate = false;

        if (releaseDateInput == null){
            addErrorMessageReleaseDate();
            return false;
        }

        if (!releaseDateInput.isBefore(MIN_ANIME_RELEASEDATE) || !releaseDateInput.isAfter(CURRENT_DATE)){
            isValidReleaseDate = true;
        }


        if (!isValidReleaseDate){
            addErrorMessageReleaseDate();
        }

        return isValidReleaseDate;
    }

    private void addErrorMessageReleaseDate(){
        errorMessage += "Release date\n";
        animeSelectorView.getReleaseDatePicker().setBorder(Border.stroke(Color.ORANGE));
    }
    private void addErrorMessageAnimeName(){
        errorMessage += "Anime name\n";
        animeSelectorView.getAnimeNameTextField().setBorder(Border.stroke(Color.ORANGE));
    }
}
