package practicumopdracht.controllers;

import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;
import practicumopdracht.views.ReviewView;
import practicumopdracht.views.View;

import java.time.LocalDate;

public class ReviewController extends Controller{

    private final Button returnButton;
    private final Button deleteButton;
    private TextArea reviewTextArea;
    private final Button newReviewButton;
    private final Button saveReviewButton;
    private CheckBox recommendedCheckBox;
    private TextField reviewRating;
    private DatePicker writeDatePicker;
    private TextField writtenByTextField;
    private ReviewView reviewView;
    private Controller animeSelectorController;
    private Alert deleteAlert;
    private Alert saveErrorAlert;
    private Alert saveSuccesAlert;
    private String saveErrorMessage;
    private String writtenBy;
    private LocalDate writeDateInput;
    private double reviewRatingValue;
    private String reviewText;
    private boolean recommendedCheckBoxValue;



    public ReviewController(){
        this.reviewView = new ReviewView();

        returnButton = this.reviewView.getReturnButton();
        deleteButton = this.reviewView.getDeleteReviewButton();
        writtenByTextField = this.reviewView.getWrittenByTextField();
        writeDatePicker = this.reviewView.getWriteDatePicker();
        writeDatePicker.setEditable(false);
        recommendedCheckBox = this.reviewView.getRecommendedCheckBox();
        reviewRating = this.reviewView.getReviewRating();
        reviewTextArea = this.reviewView.getReviewTextArea();
        newReviewButton = this.reviewView.getNewReviewButton();
        saveReviewButton = this.reviewView.getSaveReviewButton();
        saveErrorMessage = "Please check:\n";
        saveErrorAlert = new Alert(Alert.AlertType.ERROR);

        returnButton.setOnMouseClicked(mouseEvent -> handleReturnButtonClick());
        deleteButton.setOnMouseClicked(mouseEvent -> handleDeleteButtonClick());
        newReviewButton.setOnMouseClicked(mouseEvent -> handleNewReviewButtonClick());
        saveReviewButton.setOnMouseClicked(mouseEvent -> handleSaveReviewButtonClick());
    }
    public View getView() {
        return this.reviewView;
    }

    private void handleReturnButtonClick(){
        this.animeSelectorController = new AnimeSelectorController();
        MainApplication.switchController(animeSelectorController);

    }
    private void handleDeleteButtonClick(){
        deleteAlert = new Alert(Alert.AlertType.CONFIRMATION,"Delete review?");
        emptyAllInputFields();
        setAllFieldBorderDefaults();
        deleteAlert.showAndWait();
    }
    private void handleNewReviewButtonClick(){
        emptyAllInputFields();
        setAllFieldBorderDefaults();
    }

    private void handleSaveReviewButtonClick(){
        boolean emptyWrittenBy = isEmptywrittenBy();
        boolean isValidWriteDate = isValidWriteDate();
        boolean isValidRating = isValidRating();
        boolean isValidReview = isValidReview();

        if (emptyWrittenBy || !isValidWriteDate || !isValidRating || !isValidReview){
            saveErrorAlert.setContentText(saveErrorMessage);
            saveErrorAlert.show();
            saveErrorMessage = "Please check:\n";
        } else {

            getRecommendedCheckBoxValue();
            Anime testAnime = new Anime("test", CURRENT_DATE, 5, "", true, true);
            Review review = new Review(testAnime, writtenBy,writeDateInput, reviewRatingValue,reviewText,
                    recommendedCheckBoxValue);

            String saveSuccesMessage = "Confirm new review/Changes?\n" + review.toString();
            saveSuccesAlert = new Alert(Alert.AlertType.CONFIRMATION, saveSuccesMessage);
            saveSuccesAlert.show();
            emptyAllInputFields();
            setAllFieldBorderDefaults();
        }
    }

    private boolean isEmptywrittenBy(){
        boolean isEmpty = isEmptyTextField(writtenByTextField);
        if (isEmpty){
            addErrorMessageWrittenBy();
        } else {
            writtenByTextField.setBorder(Border.stroke(Color.LIGHTGREEN));
            writtenBy = writtenByTextField.getText();
        }
        return isEmpty;
    }
    private boolean isValidWriteDate(){
        LocalDate writeDateInput = writeDatePicker.getValue();
        boolean isValidWriteDate = false;

        if (writeDateInput == null){
            addErrorMessageWriteDate();
            return false;
        }

        if (!writeDateInput.isBefore(FIRST_ANIME_RELEASEDATE) || !writeDateInput.isAfter(CURRENT_DATE)){
            isValidWriteDate = true;
            writeDatePicker.setBorder(Border.stroke(Color.LIGHTGREEN));
            this.writeDateInput = writeDateInput;
        }else {
            addErrorMessageWriteDate();
        }
        return isValidWriteDate;
    }
    private boolean isValidRating(){
        boolean isValid = false;
        double reviewRating;

        try {

            reviewRating = new Double(this.reviewRating.getText());

            if (reviewRating < MIN_RATING || reviewRating > MAX_RATING){
                addErrorMessageRating();
            } else {

                reviewRatingValue = reviewRating;
                this.reviewRating.setBorder(Border.stroke(Color.LIGHTGREEN));
                isValid = true;
            }
        }catch (Exception e){
            addErrorMessageRating();
        }
        return isValid;
    }
    private boolean isValidReview(){

        if (isEmptyTextArea(reviewTextArea)){
            addErrorMessageReview();
            return false;
        } else {
            reviewText = reviewTextArea.getText();
            reviewTextArea.setBorder(Border.stroke(Color.LIGHTGREEN));
            return true;
        }

    }

    @Override
    protected void emptyAllInputFields(){
        try {
            writtenByTextField.setText("");
            writeDatePicker.setValue(null);
            reviewRating.setText("");
            reviewTextArea.setText("");
        }catch (Exception e){
            writtenByTextField = new TextField("");
            writeDatePicker = new DatePicker(null);
            reviewRating = new TextField("");
            reviewTextArea = new TextArea("");
        }
        recommendedCheckBox.setSelected(false);
    }

    @Override
    protected void setAllFieldBorderDefaults() {
        writtenByTextField.setBorder(Border.stroke(Color.LIGHTGRAY));
        writeDatePicker.setBorder(Border.stroke(Color.LIGHTGRAY));
        reviewRating.setBorder(Border.stroke(Color.LIGHTGRAY));
        reviewTextArea.setBorder(Border.stroke(Color.LIGHTGRAY));
    }

    private void getRecommendedCheckBoxValue(){
        recommendedCheckBoxValue = recommendedCheckBox.isSelected();
    }
    private void addErrorMessageWrittenBy(){
        saveErrorMessage += "Written by: " + CANT_BE_EMPTY_MESSAGE + "\n";
        writtenByTextField.setBorder(Border.stroke(Color.ORANGE));
    }
    private void addErrorMessageWriteDate(){
        saveErrorMessage += "Write date: must be between "+ FIRST_ANIME_RELEASEDATE + " and " + CURRENT_DATE + ".\n";
        writeDatePicker.setBorder(Border.stroke(Color.ORANGE));
    }
    private void addErrorMessageRating(){
        saveErrorMessage += "Rating: must have one decimal and be between " + MIN_RATING + " and " + MAX_RATING + ".\n";
        reviewRating.setBorder(Border.stroke(Color.ORANGE));
    }
    private void addErrorMessageReview(){
        saveErrorMessage += "Review: " + CANT_BE_EMPTY_MESSAGE + "\n";
        reviewTextArea.setBorder(Border.stroke(Color.ORANGE));
    }
}
