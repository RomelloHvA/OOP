package practicumopdracht.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import practicumopdracht.MainApplication;
import practicumopdracht.models.Anime;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class AnimeSelectorController extends Controller{

    private  Button reviewsButton;
    private  Button newButton;
    private  Button saveButton;
    private  Button deleteButton;
    private final AnimeSelectorView animeSelectorView;
    private Alert invalidInputAlert;
    private String errorMessage;
    private String confirmNewAnimeText;
    private Alert confirmNewAnimeAlert;
    private String animeName;
    private LocalDate animeReleaseDate;
    private boolean watchedValue;
    private boolean downloadedValue;
    private int episodeCount;
    private String synopsis;
    private TextField animeNameTextField;
    private DatePicker releaseDatePicker;
    private TextField episodeCountTextField;
    private TextArea synopsisTextArea;
    private CheckBox watchedCheckBox;
    private CheckBox downloadedCheckBox;
    private ListView<Anime> animeListView;
    private ArrayList<Anime> animes;

    public AnimeSelectorController() {
        this.animeSelectorView = new AnimeSelectorView();

        reviewsButton = this.animeSelectorView.getReviewsButton();
        releaseDatePicker = animeSelectorView.getReleaseDatePicker();
        releaseDatePicker.setEditable(false);
        animeNameTextField = animeSelectorView.getAnimeNameTextField();
        newButton = this.animeSelectorView.getNewButton();
        watchedCheckBox = animeSelectorView.getWatchedCheckBox();
        downloadedCheckBox = animeSelectorView.getDownloadedCheckBox();
        synopsisTextArea = animeSelectorView.getSynopsisTextArea();
        episodeCountTextField = animeSelectorView.getEpisodeCountTextField();
        saveButton = animeSelectorView.getSaveButton();
        deleteButton = animeSelectorView.getDeleteButton();
        animes = animeSelectorView.getAnimes();
        animeListView = animeSelectorView.getAnimeList();



        errorMessage = "Please enter valid\n";
        confirmNewAnimeText = "Confirm new Anime/Save Changes?\n";

        this.invalidInputAlert = new Alert(Alert.AlertType.WARNING);
        this.confirmNewAnimeAlert = new Alert(Alert.AlertType.CONFIRMATION);

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
        animeListView.getSelectionModel().select(null);
        emptyAllInputFields();
        setAllFieldBorderDefaults();
    }

    private void handleSaveNewAnimeChanges(){
        boolean emptyAnimeName = isEmptyAnimeName();
        boolean validReleaseDate = isValidReleaseDate();
        boolean isValidEpisodeCount = isValidEpisodeCount();
        boolean isValidSynopis = isValidSynopsis();
        Anime animeFromListView = animeSelectorView.getAnimeList().getSelectionModel().getSelectedItem();

            // Checks all the fields for valid input before proceeding.
        if (emptyAnimeName || !validReleaseDate || !isValidEpisodeCount || !isValidSynopis){
            invalidInputAlert.setContentText(errorMessage);
            invalidInputAlert.show();
            errorMessage = "Please enter valid:\n";

            // new Anime will be added if none is selected.
        } else if (animeFromListView == null) {
            getCheckBoxesValue();
            Anime anime = new Anime(animeName,animeReleaseDate,episodeCount,synopsis, downloadedValue, watchedValue);
            confirmNewAnimeText += anime.toStringConfirmMessage();
            confirmNewAnimeAlert.setContentText(confirmNewAnimeText);
            Optional<ButtonType> buttonResult = confirmNewAnimeAlert.showAndWait();

            if (buttonResult.isEmpty()){
                System.out.println("Niks geklikt");
            } else if (buttonResult.get() == ButtonType.OK) {

                animeListView.getItems().add(anime);
                animeListView.refresh();
                animeListView.getSelectionModel().selectLast();

            } else if (buttonResult.get() == ButtonType.CANCEL) {
                System.out.println("Cancel geklikt");
            }

            // Changes will be saved for the selected Anime.
        } else {
            Anime selectedAnime = animeListView.getSelectionModel().getSelectedItem();
            selectedAnime.setName(animeName);
            selectedAnime.setDownloaded(downloadedValue);
            selectedAnime.setEpisodes(episodeCount);
            selectedAnime.setWatched(watchedValue);
            selectedAnime.setReleaseDate(animeReleaseDate);
            selectedAnime.setSynopsis(synopsis);
            animeListView.refresh();
            confirmNewAnimeAlert.setContentText(confirmNewAnimeText);
            confirmNewAnimeAlert.show();

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

        watchedValue = watchedCheckBox.isSelected();
        downloadedValue = downloadedCheckBox.isSelected();
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
