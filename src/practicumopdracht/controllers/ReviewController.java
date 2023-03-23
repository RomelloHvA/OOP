/**
 * Class for controlling the review View.
 * @author Romello ten Broeke
 */
package practicumopdracht.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import practicumopdracht.MainApplication;
import practicumopdracht.comparators.NameComparator;
import practicumopdracht.comparators.RatingWriteDateComparator;
import practicumopdracht.comparators.WrittenByComparator;
import practicumopdracht.data.ReviewDAO;
import practicumopdracht.models.Anime;
import practicumopdracht.models.Review;
import practicumopdracht.views.ReviewView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

public class ReviewController extends Controller{

    private final Button returnButton;
    private final Button deleteButton;
    private ObservableList<Review> reviewObservableList;
    private TextArea reviewTextArea;
    private final Button newReviewButton;
    private final Button saveReviewButton;
    private CheckBox recommendedCheckBox;
    private TextField reviewRating;
    private DatePicker writeDatePicker;
    private TextField writtenByTextField;
    private ReviewView reviewView;
    private AnimeSelectorController animeSelectorController;
    private Alert saveErrorAlert;
    private Alert saveSuccesAlert;
    private String saveErrorMessage;
    private String writtenBy;
    private LocalDate writeDateInput;
    private double reviewRatingValue;
    private String reviewText;
    private boolean recommendedCheckBoxValue;
    private ListView<Review> reviewListView;
    private ReviewDAO reviewDAO;
    private ComboBox<Anime> animeComboBox;
    private Anime selectedAnime;

    /**
     * Constructor for the ReviewController
     * @param selectedAnime is the anime selected in the animeSelectorView. This is to correctly show which anime was
     *                      selected in the previous and show the correct information for the selectedAnime.
     */
    public ReviewController(Anime selectedAnime){
        this.selectedAnime = selectedAnime;
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
        animeComboBox = reviewView.getAnimeComboBox();
        this.animeSelectorController = new AnimeSelectorController();
        animeComboBox.getSelectionModel().select(selectedAnime);
        setAnimeComboBox();
        FXCollections.sort(animeComboBox.getItems(), Collections.reverseOrder(new NameComparator()));

        reviewDAO = MainApplication.getReviewDAO();

        reviewObservableList = FXCollections.observableArrayList(reviewDAO.getAllFor(selectedAnime));
        reviewListView = reviewView.getReviewListView();
        reviewListView.setItems(reviewObservableList);


        returnButton.setOnMouseClicked(mouseEvent -> handleReturnButtonClick());
        deleteButton.setOnMouseClicked(mouseEvent -> handleDeleteButtonClick());
        newReviewButton.setOnMouseClicked(mouseEvent -> handleNewReviewButtonClick());
        saveReviewButton.setOnMouseClicked(mouseEvent -> handleSaveReviewButtonClick());
        reviewView.getSortRatingAsc().setOnAction(actionEvent -> handleRatingAscClick());
        reviewView.getSortRatingDes().setOnAction(actionEvent -> handleRatingDesClick());
        reviewView.getSortWrittenByAsc().setOnAction(actionEvent -> handleWriteDateAscClick());
        reviewView.getSortWrittenByDes().setOnAction(actionEvent -> handleWriteDateDesClick());

        // Fills in all the fields
        reviewListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldReview, newReview) ->{
            if (newReview != null){
                writtenByTextField.setText(newReview.getWrittenBy());
                writeDatePicker.setValue(newReview.getWriteDate());
                recommendedCheckBox.setSelected(newReview.isRecommended());
                reviewTextArea.setText(newReview.getReview());
                reviewRating.setText(String.valueOf(newReview.getRating()));
            }
        });

        animeComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldAnime, newAnime) -> {
            reviewObservableList = FXCollections.observableArrayList(reviewDAO.getAllFor(newAnime));
            reviewListView.setItems(reviewObservableList);
        });
    }

    private void handleWriteDateDesClick() {
        reviewView.getSortRatingAsc().setSelected(false);
        reviewView.getSortRatingDes().setSelected(false);
        reviewView.getSortWrittenByAsc().setSelected(false);
        reviewView.getSortWrittenByDes().setSelected(true);
        sortWriteDateDes();
    }

    private void sortWriteDateDes() {
        FXCollections.sort(reviewListView.getItems(), new WrittenByComparator());
    }

    private void handleWriteDateAscClick() {
        reviewView.getSortRatingAsc().setSelected(false);
        reviewView.getSortRatingDes().setSelected(false);
        reviewView.getSortWrittenByAsc().setSelected(true);
        reviewView.getSortWrittenByDes().setSelected(false);
        sortWriteDateAsc();
    }

    private void sortWriteDateAsc() {
        FXCollections.sort(reviewListView.getItems(), Collections.reverseOrder(new WrittenByComparator()));
    }

    private void handleRatingAscClick() {
        reviewView.getSortRatingAsc().setSelected(true);
        reviewView.getSortRatingDes().setSelected(false);
        reviewView.getSortWrittenByAsc().setSelected(false);
        reviewView.getSortWrittenByDes().setSelected(false);
        sortRatingAsc();
    }
    private void handleRatingDesClick(){
        reviewView.getSortRatingAsc().setSelected(false);
        reviewView.getSortRatingDes().setSelected(true);
        reviewView.getSortWrittenByAsc().setSelected(false);
        reviewView.getSortWrittenByDes().setSelected(false);
        sortRatingDes();
    }

    private void sortRatingAsc() {
        FXCollections.sort(reviewListView.getItems(), Collections.reverseOrder(new RatingWriteDateComparator()));
    }

    private void sortRatingDes(){
        FXCollections.sort(reviewListView.getItems(), new RatingWriteDateComparator());
    }

    public View getView() {
        return this.reviewView;
    }

    /**
     * handles the return button click and switches the view.
     */
    private void handleReturnButtonClick(){
        MainApplication.switchController(animeSelectorController);

    }

    /**
     * Method for deleting the selected review.
     */
    private void handleDeleteButtonClick(){
        Review selectedReview = reviewListView.getSelectionModel().getSelectedItem();
        try {
            Alert deleteButtonAlert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected review: " + selectedReview.toStringConfirmMessage());
            Optional<ButtonType> buttonResult = deleteButtonAlert.showAndWait();
            if (buttonResult.isEmpty()) {
                System.out.println("Niks geklikt");

            } else if (buttonResult.get() == ButtonType.OK) {

               reviewListView.getItems().remove(selectedReview);
                reviewDAO.delete(selectedReview);
                reviewListView.refresh();


            } else if (buttonResult.get() == ButtonType.CANCEL) {
                System.out.println("Cancel geklikt");
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nothing deleted");
            alert.show();
            return;
        }
        emptyAllInputFields();
        setAllFieldBorderDefaults();
        reviewListView.getSelectionModel().clearSelection();
        sortRatingDes();

    }

    /**
     * Method for handling the new button click. Clears all fields and selection. Sets the border back to defaultcolor.
     */
    private void handleNewReviewButtonClick(){

        reviewListView.getSelectionModel().clearSelection();
        emptyAllInputFields();
        setAllFieldBorderDefaults();
    }

    /**
     * Method for handling on saving a new review. Checks if the inputs are valid first before saving. Saves a new anime
     * if there is none selected. Save changes if a new anime is selected.
     */

    private void handleSaveReviewButtonClick(){

        boolean emptyWrittenBy = isEmptywrittenBy();
        boolean isValidWriteDate = isValidWriteDate();
        boolean isValidRating = isValidRating();
        boolean isValidReview = isValidReview();
        Review reviewFromListView = reviewListView.getSelectionModel().getSelectedItem();
        saveSuccesAlert = new Alert(Alert.AlertType.CONFIRMATION);


        if (emptyWrittenBy || !isValidWriteDate || !isValidRating || !isValidReview){
            saveErrorAlert.setContentText(saveErrorMessage);
            saveErrorAlert.show();
            saveErrorMessage = "Please check:\n";

        } else if (reviewFromListView == null){
            getRecommendedCheckBoxValue();
            Review review = new Review(selectedAnime, writtenBy,writeDateInput, reviewRatingValue,reviewText,
                    recommendedCheckBoxValue);

            String saveSuccesMessage = "Confirm new review/Changes?\n" + review.toStringConfirmMessage();
            saveSuccesAlert.setContentText(saveSuccesMessage);

            Optional<ButtonType> buttonTypeOptional = saveSuccesAlert.showAndWait();
            if (buttonTypeOptional.isEmpty()){
                System.out.println("Niks geklikt");
            } else if (buttonTypeOptional.get() == ButtonType.OK) {
                reviewObservableList.add(review);
                reviewDAO.addOrUpdate(review);
                emptyAllInputFields();
                setAllFieldBorderDefaults();
            }

            reviewListView.refresh();


        } else {
            String changedFieldsMessage = "Author: " + writtenBy +
                    "\nWrite date: " + writeDateInput +
                    "\nRecommended: " + recommendedCheckBoxValue +
                    "\nRating: " + reviewRatingValue +
                    "Review: " + reviewText;


            saveSuccesAlert.setContentText(changedFieldsMessage);
            Optional<ButtonType> buttonTypeOptional = saveSuccesAlert.showAndWait();

            if (buttonTypeOptional.isEmpty()){
                System.out.println("Niks geklikt neef");
            } else if (buttonTypeOptional.get() == ButtonType.OK) {
                reviewFromListView.setWrittenBy(writtenBy);
                reviewFromListView.setWriteDate(writeDateInput);
                reviewFromListView.setRecommended(recommendedCheckBoxValue);
                reviewFromListView.setRating(reviewRatingValue);
                reviewFromListView.setReview(reviewText);
                reviewDAO.addOrUpdate(reviewFromListView);
            }

            sortRatingDes();
            reviewListView.refresh();

        }
    }

    /**
     * sets the combobox with all the available anime.
     */
    private void setAnimeComboBox(){
        animeComboBox.setItems(this.animeSelectorController.getAnimeObservableList());
    }

    /**
     *
     * @return true if the writtenby is empty or only spaces.
     */
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

    /**
     *
     * @return true if the writedate is valid.
     */
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

    /**
     *
     * @return true if the rating is valid according to the given paramets in the method.
     */
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

    /**
     *
     * @return true
     */
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
