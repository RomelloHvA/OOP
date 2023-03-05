package practicumopdracht.controllers;

import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

import java.time.LocalDate;

public class AnimeSelectorController extends Controller{

    private  Button reviewsButton;
    private  Button newButton;
    private  Button saveButton;
    private  Button deleteButton;
    private final AnimeSelectorView animeSelectorView;
    private Alert alert;
    private String errorMessage;
    private String confirmNewAnimeText;
    private Alert confirmNewAnimeAlert;
    private String animeName;
    private LocalDate animeReleaseDate;
    private boolean watchedCheckbox;
    private boolean downloadedCheckbox;
    private int episodeCount;
    private String synopsis;
    private TextField animeNameTextField;
    private DatePicker releaseDatePicker;
    private TextField episodeCountTextField;
    private TextArea synopsisTextArea;
    private CheckBox watchedCheckBox;
    private CheckBox downloadedCheckBox;

    public AnimeSelectorController() {
        this.animeSelectorView = new AnimeSelectorView();

        reviewsButton = this.animeSelectorView.getReviewsButton();
        releaseDatePicker = animeSelectorView.getReleaseDatePicker();
        animeNameTextField = animeSelectorView.getAnimeNameTextField();
        newButton = this.animeSelectorView.getNewButton();
        watchedCheckBox = animeSelectorView.getWatchedCheckBox();
        downloadedCheckBox = animeSelectorView.getDownloadedCheckBox();
        synopsisTextArea = animeSelectorView.getSynopsisTextArea();
        episodeCountTextField = animeSelectorView.getEpisodeCountTextField();
        saveButton = animeSelectorView.getSaveButton();
        deleteButton = animeSelectorView.getDeleteButton();

        errorMessage = "please enter valid\n";
        confirmNewAnimeText = "Confirm new Anime/Save Changes?\n";

        this.alert = new Alert(Alert.AlertType.WARNING);
        this.confirmNewAnimeAlert = new Alert(Alert.AlertType.CONFIRMATION,confirmNewAnimeText);

        reviewsButton.setOnMouseClicked(mouseEvent -> handleReviewButtonClick());
        newButton.setOnMouseClicked(mouseEvent -> handleNewAnimeButtonClick());
        saveButton.setOnMouseClicked(mouseEvent -> handleSaveNewAnimeChanges());
        deleteButton.setOnMouseClicked(mouseEvent -> handleDeleteButtonClick());

    }

    @Override
    public View getView() {
        return this.animeSelectorView;
    }

    private void handleReviewButtonClick(){
        ReviewController reviewController = new ReviewController();
        MainApplication.switchController(reviewController);
    }

    private void handleNewAnimeButtonClick(){
        emptyAllInputFields();
        setAllFieldBorderDefaults();
    }

    private void handleSaveNewAnimeChanges(){
        boolean emptyAnimeName = isEmptyAnimeName();
        boolean validReleaseDate = isValidReleaseDate();
        boolean isValidEpisodeCount = isValidEpisodeCount();
        boolean isValidSynopis = isValidSynopsis();

        if (emptyAnimeName || !validReleaseDate || !isValidEpisodeCount || !isValidSynopis){
            alert.setContentText(errorMessage);
            alert.show();
            errorMessage = "Please enter valid:\n";
        } else {

            getCheckBoxesValue();
            Anime anime = new Anime(animeName,animeReleaseDate,episodeCount,synopsis,downloadedCheckbox,watchedCheckbox);
            confirmNewAnimeText += anime.toString();

            confirmNewAnimeAlert.setContentText(confirmNewAnimeText);
            confirmNewAnimeAlert.show();
            emptyAllInputFields();
            setAllFieldBorderDefaults();
        }


        }

        private void handleDeleteButtonClick(){
        Alert deleteButtonAlert = new Alert(Alert.AlertType.INFORMATION, "Delete button clicked");
        deleteButtonAlert.show();
        }

    private boolean isEmptyAnimeName(){

        boolean isEmpty = isEmptyTextField(animeNameTextField);
        if (isEmpty){
            addErrorMessageAnimeName();
        } else {
            animeNameTextField.setBorder(Border.stroke(Color.LIGHTGREEN));
            animeName = animeSelectorView.getAnimeNameTextField().getText();
        }
        return isEmpty;
    }
    private boolean isValidReleaseDate(){

        LocalDate releaseDateInput = releaseDatePicker.getValue();
        boolean isValidReleaseDate = false;

        if (releaseDateInput == null){
            addErrorMessageReleaseDate();
            return false;
        }

        if (!releaseDateInput.isBefore(FIRST_ANIME_RELEASEDATE) || !releaseDateInput.isAfter(CURRENT_DATE)){
            isValidReleaseDate = true;
            releaseDatePicker.setBorder(Border.stroke(Color.LIGHTGREEN));
            animeReleaseDate = releaseDateInput;
        } else {
            addErrorMessageReleaseDate();
        }

        return isValidReleaseDate;
    }

    private void getCheckBoxesValue(){

        watchedCheckbox = watchedCheckBox.isSelected();
        downloadedCheckbox = downloadedCheckBox.isSelected();
    }

    private boolean isValidEpisodeCount(){
        boolean isValid = false;
        int episodeCount;
        try {

            episodeCountTextField = animeSelectorView.getEpisodeCountTextField();
            episodeCount = Integer.parseInt(episodeCountTextField.getText());
            if (episodeCount < MIN_EPISODES){
                addErrorMessageEpisodeCount();
            } else {
                this.episodeCount = episodeCount;
                isValid = true;
                episodeCountTextField.setBorder(Border.stroke(Color.LIGHTGREEN));
            }

        } catch (Exception exception){
            addErrorMessageEpisodeCount();
        }

        return isValid;
    }

    private boolean isValidSynopsis(){
        boolean isValid = false;
        synopsisTextArea = animeSelectorView.getSynopsisTextArea();
        TextArea synopsisArea = synopsisTextArea;

        if (isEmptyTextArea(synopsisArea)){
            addErrorMessageEmptySynopsis();
        } else {
            isValid = true;
            synopsisArea.setBorder(Border.stroke(Color.LIGHTGREEN));
            synopsis = synopsisArea.getText();
        }
        return isValid;
    }

    @Override
    protected void emptyAllInputFields(){

        try {
            animeNameTextField.setText("");
            releaseDatePicker.setValue(null);
            episodeCountTextField.setText("");
            synopsisTextArea.setText("");
        }catch (Exception exception){
            animeNameTextField = new TextField("");
            this.releaseDatePicker = new DatePicker(null);
            episodeCountTextField = new TextField("");
            synopsisTextArea = new TextArea();
        }

        watchedCheckBox.setSelected(false);
        downloadedCheckBox.setSelected(false);

    }

    @Override
    protected void setAllFieldBorderDefaults(){
        animeNameTextField.setBorder(Border.stroke(Color.LIGHTGRAY));
        releaseDatePicker.setBorder(Border.stroke(Color.LIGHTGRAY));
        episodeCountTextField.setBorder(Border.stroke(Color.LIGHTGRAY));
        synopsisTextArea.setBorder(Border.stroke(Color.LIGHTGRAY));
    }

    private void addErrorMessageReleaseDate(){
        errorMessage += "Release date: must be between "+ FIRST_ANIME_RELEASEDATE + " and " + CURRENT_DATE + ".\n";
        animeSelectorView.getReleaseDatePicker().setBorder(Border.stroke(Color.ORANGE));
    }
    private void addErrorMessageAnimeName(){
        errorMessage += "Anime name: "+ CANT_BE_EMPTY_MESSAGE + "\n";
        animeSelectorView.getAnimeNameTextField().setBorder(Border.stroke(Color.ORANGE));
    }

    private void addErrorMessageEpisodeCount(){
        errorMessage += "Episode Count: Must be higher than " + MIN_EPISODES + ".\n";
        animeSelectorView.getEpisodeCountTextField().setBorder(Border.stroke(Color.ORANGE));
    }
    private void addErrorMessageEmptySynopsis(){
        errorMessage += "Synopsis: "+ CANT_BE_EMPTY_MESSAGE;
        animeSelectorView.getSynopsisTextArea().setBorder(Border.stroke(Color.ORANGE));
    }
}
