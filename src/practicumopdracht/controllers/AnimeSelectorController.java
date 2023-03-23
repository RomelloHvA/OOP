/**
 * extends from Controllerclass
 * handles the controls for the animeselectorview
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
import practicumopdracht.data.AnimeDAO;
import practicumopdracht.data.DAO;
import practicumopdracht.models.Anime;
import practicumopdracht.views.AnimeSelectorView;
import practicumopdracht.views.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class AnimeSelectorController extends Controller {

    private ObservableList<Anime> animeObservableList;
    private AnimeDAO animeDAO;
    private Button reviewsButton;
    private Button newButton;
    private Button saveButton;
    private Button deleteButton;
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

    /**
     * Constructor for the controller. Takes no values.
     */
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

        animeDAO = MainApplication.getAnimeDAO();

        animeObservableList = FXCollections.observableArrayList(animeDAO.getAll());
        animeListView = animeSelectorView.getAnimeList();
        animeListView.setItems(animeObservableList);


        errorMessage = "Please enter valid\n";
        confirmNewAnimeText = "Confirm new Anime/Save Changes?\n";

        this.invalidInputAlert = new Alert(Alert.AlertType.WARNING);
        this.confirmNewAnimeAlert = new Alert(Alert.AlertType.CONFIRMATION);

        reviewsButton.setOnMouseClicked(mouseEvent -> handleReviewButtonClick());
        newButton.setOnMouseClicked(mouseEvent -> handleNewAnimeButtonClick());
        saveButton.setOnMouseClicked(mouseEvent -> handleSaveNewAnimeChanges());
        deleteButton.setOnMouseClicked(mouseEvent -> handleDeleteButtonClick());
        animeSelectorView.getSave().setOnAction(actionEvent -> handleSaveAll());
        animeSelectorView.getLoad().setOnAction(actionEvent -> handleLoadAll());
        animeSelectorView.getExit().setOnAction(actionEvent -> handleExit());
        animeSelectorView.getAscendingMenuItem().setOnAction(actionEvent -> handleSortAscending());
        animeSelectorView.getDescendingMenuItem().setOnAction(actionEvent -> handleSortDescending());


        animeListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldAnime, newAnime) -> {
            if (newAnime != null) {
                animeNameTextField.setText(newAnime.getName());
                releaseDatePicker.setValue(newAnime.getReleaseDate());
                watchedCheckBox.setSelected(newAnime.isWatched());
                downloadedCheckBox.setSelected(newAnime.isDownloaded());
                episodeCountTextField.setText(String.valueOf(newAnime.getEpisodes()));
                synopsisTextArea.setText(newAnime.getSynopsis());
            }

        });

    }

    private void handleSortDescending() {
        FXCollections.sort(animeListView.getItems(), new NameComparator());
    }

    private void handleSortAscending() {
        FXCollections.sort(animeListView.getItems(), Collections.reverseOrder(new NameComparator()));
    }

    private void handleExit() {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Do you wish to save and exit?");
        Optional<ButtonType> buttonTypeOptional = exitAlert.showAndWait();
        if (buttonTypeOptional.isEmpty()){
            System.out.println("Noppes geklikt");
        } else if (buttonTypeOptional.get() == ButtonType.OK) {
            handleSaveAll();
            System.exit(0);
        }
    }

    private void handleLoadAll() {
        Alert loadAlert = new Alert(Alert.AlertType.CONFIRMATION, "Load all Data?");
        Optional<ButtonType> buttonTypeOptional = loadAlert.showAndWait();
        if (buttonTypeOptional.isEmpty()){
            System.out.println("Niks geklikt");
        } else if (buttonTypeOptional.get() == ButtonType.OK) {
            Alert loadingAlert = new Alert(Alert.AlertType.INFORMATION, "Data Loaded");
            if (!MainApplication.getAnimeDAO().load() || !MainApplication.getReviewDAO().load()){
                loadingAlert.setContentText("Data not loaded");
            }
            loadingAlert.show();
            animeObservableList = FXCollections.observableArrayList(animeDAO.getAll());
            animeListView = animeSelectorView.getAnimeList();
            animeListView.setItems(animeObservableList);
            animeListView.refresh();
            handleSortAscending();
        }

    }

    @Override
    public View getView() {
        return this.animeSelectorView;
    }

    /**
     * handles the switch to the other view.
     */
    private void handleReviewButtonClick() {
        Anime selectedAnime = animeListView.getSelectionModel().getSelectedItem();

        if (selectedAnime != null) {
            ReviewController reviewController = new ReviewController(selectedAnime);
            MainApplication.switchController(reviewController);
        } else {
            System.out.println("Nothing selectedddd");
        }

    }

    /**
     * method for handling when the new anime button is clicked. Empties all the fields and selections.
     * Also clears the contextText for the new anime alert if it was unused.
     */
    private void handleNewAnimeButtonClick() {
        animeListView.getSelectionModel().clearSelection();
        emptyAllInputFields();
        setAllFieldBorderDefaults();
        confirmNewAnimeText = "Confirm new Anime/Save Changes?\n";
        confirmNewAnimeAlert.setContentText(confirmNewAnimeText);
    }

    /**
     * method for handling when the save button is clicked. Saves a new anime if none is selected and changes values if
     * there is an anime selected. Also calls upon methods to validate the inputs before saving. Rejects invalid inputs.
     */

    private void handleSaveNewAnimeChanges() {
        boolean emptyAnimeName = isEmptyAnimeName();
        boolean validReleaseDate = isValidReleaseDate();
        boolean isValidEpisodeCount = isValidEpisodeCount();
        boolean isValidSynopis = isValidSynopsis();
        Anime animeFromListView = animeSelectorView.getAnimeList().getSelectionModel().getSelectedItem();

        // Checks all the fields for valid input before proceeding.
        if (emptyAnimeName || !validReleaseDate || !isValidEpisodeCount || !isValidSynopis) {
            invalidInputAlert.setContentText(errorMessage);
            invalidInputAlert.show();
            errorMessage = "Please enter valid:\n";

            // new Anime will be added if none is selected.
        } else if (animeFromListView == null) {

            getCheckBoxesValue();
            Anime anime = new Anime(animeName, animeReleaseDate, episodeCount, synopsis, downloadedValue, watchedValue);
            confirmNewAnimeText += anime.toStringConfirmMessage();
            confirmNewAnimeAlert.setContentText(confirmNewAnimeText);

            Optional<ButtonType> buttonResult = confirmNewAnimeAlert.showAndWait();
            if (buttonResult.isEmpty()) {
                System.out.println("Niks geklikt");
            } else if (buttonResult.get() == ButtonType.OK) {


                animeObservableList.add(anime);
                animeDAO.addOrUpdate(anime);
                animeListView.refresh();
                animeListView.getSelectionModel().selectLast();


            } else if (buttonResult.get() == ButtonType.CANCEL) {
                System.out.println("Cancel geklikt");
            }

            // Changes will be saved for the selected Anime.
        } else {

            String changedFieldsMessage = "name: " + animeName + '\n' +
                    "releaseDate: " + animeReleaseDate + "\n" +
                    "episodes: " + episodeCount + "\n" +
                    "downloaded: " + downloadedValue + "\n" +
                    "watched: " + watchedValue;

            Anime selectedAnime = animeListView.getSelectionModel().getSelectedItem();
            selectedAnime.setName(animeName);
            selectedAnime.setDownloaded(downloadedValue);
            selectedAnime.setEpisodes(episodeCount);
            selectedAnime.setWatched(watchedValue);
            selectedAnime.setReleaseDate(animeReleaseDate);
            selectedAnime.setSynopsis(synopsis);
            animeDAO.addOrUpdate(selectedAnime);
            animeListView.refresh();

            confirmNewAnimeAlert.setContentText(changedFieldsMessage);
            confirmNewAnimeAlert.show();


        }
        handleSortAscending();


    }

    /**
     * Method for handling when the delete button is clicked. Gets the selected anime and deletes it. Deletes nothing
     * if there is no anime selected and shows a message notifying the user that nothing is deleted.
     */
    private void handleDeleteButtonClick() {
        Anime selectedAnime = animeListView.getSelectionModel().getSelectedItem();
        try {
            Alert deleteButtonAlert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected anime: " + selectedAnime.getName());
            Optional<ButtonType> buttonResult = deleteButtonAlert.showAndWait();
            if (buttonResult.isEmpty()) {
                System.out.println("Niks geklikt");

            } else if (buttonResult.get() == ButtonType.OK) {

                MainApplication.getReviewDAO().deleteAllFor(selectedAnime);
                animeListView.getItems().remove(selectedAnime);
                animeDAO.delete(selectedAnime);
                animeDAO.getAll().remove(selectedAnime);
                animeListView.refresh();



            } else if (buttonResult.get() == ButtonType.CANCEL) {
                System.out.println("Cancel geklikt");
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nothing deleted");
            alert.show();
        }
        handleSortAscending();

    }

    private void handleSaveAll() {
        Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION, "Save all Data?");
        Optional<ButtonType> buttonTypeOptional = saveAlert.showAndWait();
        if (buttonTypeOptional.isEmpty()) {
            System.out.println("Niks geklikt");
        } else if (buttonTypeOptional.get() == ButtonType.OK) {
            Alert savingAlert = new Alert(Alert.AlertType.INFORMATION, "Data saved");
            if (!MainApplication.getAnimeDAO().save() || !MainApplication.getReviewDAO().save()) {
                savingAlert.setContentText("Data not saved");
            }
            savingAlert.show();

        }
//        MainApplication.getAnimeDAO().save();
    }

    /**
     * @return true if a textfield is empty or only contains spaces.
     * Also makes the border red to show which textfield it is.
     */
    private boolean isEmptyAnimeName() {

        boolean isEmpty = isEmptyTextField(animeNameTextField);
        if (isEmpty) {
            addErrorMessageAnimeName();
        } else {
            animeNameTextField.setBorder(Border.stroke(Color.LIGHTGREEN));
            animeName = animeSelectorView.getAnimeNameTextField().getText();
        }
        return isEmpty;
    }

    /**
     * @return true if the given release date is a valid one else return false.
     * Makes release date border red if the release date is invalid.
     */
    private boolean isValidReleaseDate() {

        LocalDate releaseDateInput = releaseDatePicker.getValue();
        boolean isValidReleaseDate = false;

        if (releaseDateInput == null) {
            addErrorMessageReleaseDate();
            return false;
        }

        if (!releaseDateInput.isBefore(FIRST_ANIME_RELEASEDATE) || !releaseDateInput.isAfter(CURRENT_DATE)) {
            isValidReleaseDate = true;
            releaseDatePicker.setBorder(Border.stroke(Color.LIGHTGREEN));
            animeReleaseDate = releaseDateInput;
        } else {
            addErrorMessageReleaseDate();
        }

        return isValidReleaseDate;
    }

    /**
     * method for getting the boolean values of the checkboxes.
     */
    private void getCheckBoxesValue() {

        watchedValue = watchedCheckBox.isSelected();
        downloadedValue = downloadedCheckBox.isSelected();
    }

    /**
     * Checks if the episode count given is an integer.
     * @return true if the episode count falls within the parameters.
     */
    private boolean isValidEpisodeCount() {
        boolean isValid = false;
        int episodeCount;
        try {

            episodeCountTextField = animeSelectorView.getEpisodeCountTextField();
            episodeCount = Integer.parseInt(episodeCountTextField.getText());
            if (episodeCount < MIN_EPISODES) {
                addErrorMessageEpisodeCount();
            } else {
                this.episodeCount = episodeCount;
                isValid = true;
                episodeCountTextField.setBorder(Border.stroke(Color.LIGHTGREEN));
            }

        } catch (Exception exception) {
            addErrorMessageEpisodeCount();
        }

        return isValid;
    }

    /**
     * @return true if a textarea is empty or only contains spaces.
     * Also makes the border red to show which textarea it is.
     */
    private boolean isValidSynopsis() {
        boolean isValid = false;
        synopsisTextArea = animeSelectorView.getSynopsisTextArea();
        TextArea synopsisArea = synopsisTextArea;

        if (isEmptyTextArea(synopsisArea)) {
            addErrorMessageEmptySynopsis();
        } else {
            isValid = true;
            synopsisArea.setBorder(Border.stroke(Color.LIGHTGREEN));
            synopsis = synopsisArea.getText();
        }
        return isValid;
    }


    @Override
    protected void emptyAllInputFields() {

        try {
            animeNameTextField.setText("");
            releaseDatePicker.setValue(null);
            episodeCountTextField.setText("");
            synopsisTextArea.setText("");
        } catch (Exception exception) {
            animeNameTextField = new TextField("");
            this.releaseDatePicker = new DatePicker(null);
            episodeCountTextField = new TextField("");
            synopsisTextArea = new TextArea();
        }

        watchedCheckBox.setSelected(false);
        downloadedCheckBox.setSelected(false);

    }

    @Override
    protected void setAllFieldBorderDefaults() {
        animeNameTextField.setBorder(Border.stroke(Color.LIGHTGRAY));
        releaseDatePicker.setBorder(Border.stroke(Color.LIGHTGRAY));
        episodeCountTextField.setBorder(Border.stroke(Color.LIGHTGRAY));
        synopsisTextArea.setBorder(Border.stroke(Color.LIGHTGRAY));
    }

    private void addErrorMessageReleaseDate() {
        errorMessage += "Release date: must be between " + FIRST_ANIME_RELEASEDATE + " and " + CURRENT_DATE + ".\n";
        animeSelectorView.getReleaseDatePicker().setBorder(Border.stroke(Color.ORANGE));
    }

    private void addErrorMessageAnimeName() {
        errorMessage += "Anime name: " + CANT_BE_EMPTY_MESSAGE + "\n";
        animeSelectorView.getAnimeNameTextField().setBorder(Border.stroke(Color.ORANGE));
    }

    private void addErrorMessageEpisodeCount() {
        errorMessage += "Episode Count: Must be higher than " + MIN_EPISODES + ".\n";
        animeSelectorView.getEpisodeCountTextField().setBorder(Border.stroke(Color.ORANGE));
    }

    private void addErrorMessageEmptySynopsis() {
        errorMessage += "Synopsis: " + CANT_BE_EMPTY_MESSAGE;
        animeSelectorView.getSynopsisTextArea().setBorder(Border.stroke(Color.ORANGE));
    }

    public ObservableList<Anime> getAnimeObservableList() {
        return animeObservableList;
    }
}
